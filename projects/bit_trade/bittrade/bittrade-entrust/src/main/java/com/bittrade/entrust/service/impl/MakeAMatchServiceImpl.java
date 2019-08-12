package com.bittrade.entrust.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bittrade.common.constant.IConstant;
import com.bittrade.common.constant.IQueueConstants;
import com.bittrade.common.enums.EntrustDirectionEnumer;
import com.bittrade.common.enums.EntrustStatusEnumer;
import com.bittrade.common.enums.EntrustTypeEnumer;
import com.bittrade.common.enums.IsActiveEnumer;
import com.bittrade.currency.api.service.ITCurrencyTradeService;
import com.bittrade.entrust.api.service.IMakeAMatchService;
import com.bittrade.entrust.api.service.ITEntrustRecordService;
import com.bittrade.entrust.api.service.ITEntrustService;
import com.bittrade.entrust.api.service.ITKlineService;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.model.TEntrustRecord;
import com.core.common.constant.ICompareResultConstant;
import com.core.tool.BigDecimalUtil;
import com.core.tool.JSONUtil;
import com.core.tool.SnowFlake;

import redis.clients.jedis.JedisCluster;

/**
 * 
 * ClassName: MakeAMatchServiceImpl <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * DateTime: Jul 12, 2019 2:56:04 PM <br />
 *  
 * @author Administrator  
 * @version   
 * @since JDK 1.8
 */
@Component
public class MakeAMatchServiceImpl implements IMakeAMatchService, InitializingBean, BeanFactoryPostProcessor {
	
	private static final Logger LOG = LoggerFactory.getLogger(MakeAMatchServiceImpl.class);
	
	/**
	 * 悲观锁
	 */
	private static final ConcurrentHashMap<Integer, ReentrantLock> MAP__LOCK__BUY_AND_SELL = new ConcurrentHashMap<>();
	
	/**
	 * 市价买
	 */
	private static final ConcurrentHashMap<Integer, ArrayList<TEntrust>> MAP__BUY_MARKET = new ConcurrentHashMap<>();
	
	/**
	 * 限价买
	 */
	private static final ConcurrentHashMap<Integer, ArrayList<TEntrust>> MAP__BUY_LIMIT = new ConcurrentHashMap<>();
	
	/**
	 * 市价卖
	 */
	private static final ConcurrentHashMap<Integer, ArrayList<TEntrust>> MAP__SELL_MARKET = new ConcurrentHashMap<>();
	
	/**
	 * 限价卖
	 */
	private static final ConcurrentHashMap<Integer, ArrayList<TEntrust>> MAP__SELL_LIMIT = new ConcurrentHashMap<>();
	
	/**
	 * 交易对
	 */
	private static final ConcurrentHashMap<Integer, TCurrencyTrade> MAP__CURRENCY_TRADE = new ConcurrentHashMap<>();
	
	/**
	 * 行情价
	 */
	private static final ConcurrentHashMap<Integer, BigDecimal> MAP__LINE_PRICE = new ConcurrentHashMap<>();
	
	private static final SnowFlake SNOW_FLAKE__ENTRUST_RECORD = new SnowFlake(2, 2);
	
	@Autowired
	private ITEntrustService entrustService;
	@Autowired
	private ITEntrustRecordService entrustRecordService;
	@Autowired
	private ITKlineService klineService;
	@Reference
	private ITCurrencyTradeService currencyTradeService;
	
	@Autowired
	private JedisCluster jedisCluster;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/**
	 * 异步调用K线生成。
	 */
	private static final ExecutorService ES_KLINE = Executors.newFixedThreadPool(50);
	
	
	private ReentrantLock getLock(int key) {
		ReentrantLock lock;
		
		ConcurrentHashMap<Integer, ReentrantLock> map = MAP__LOCK__BUY_AND_SELL;
		if (map.containsKey( key )) {
			lock = map.get( key );
		} else {
			map.put( key, lock = new ReentrantLock() );
		}
		
		return lock;
	}
	
	private ArrayList<TEntrust> getList(ConcurrentHashMap<Integer, ArrayList<TEntrust>> map, Integer key) {
		ArrayList<TEntrust> list;
		
		if (map.containsKey( key )) {
			list = map.get( key );
		} else {
			map.put( key, list = new ArrayList<>() );
		}
		
		return list;
	}
	
	/**
	 * 获得内存中买和卖的数量再计算差，好让均衡器程序均衡机器人下单。
	 * getSubCount:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param currencyTradeID
	 * @return  
	 * @since JDK 1.8
	 */
	public int[] getSubCount(int currencyTradeID) {
		int iArr_subCount[];
		
		ArrayList<TEntrust> list__buyMarket = getList( MAP__BUY_MARKET, currencyTradeID );
		ArrayList<TEntrust> list__buyLimit = getList( MAP__BUY_LIMIT, currencyTradeID );
		ArrayList<TEntrust> list__sellMarket = getList( MAP__SELL_MARKET, currencyTradeID );
		ArrayList<TEntrust> list__sellLimit = getList( MAP__SELL_LIMIT, currencyTradeID );
		
		iArr_subCount = new int[] {
				list__buyMarket.size() + list__buyLimit.size(), 
				list__sellMarket.size() + list__sellLimit.size()
		};
		
		return iArr_subCount;
	}
	
	/**
	 * <p>
	 *   懒加载（非勤加载）
	 * </p>
	 * getSymbol:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param currencyTradeID 
	 * @return  
	 * @since JDK 1.8
	 */
	public TCurrencyTrade getCurrencyTrade(int currencyTradeID) {
		TCurrencyTrade obj_currencyTrade = null;
		
		if (MAP__CURRENCY_TRADE.containsKey( currencyTradeID )) {
			obj_currencyTrade = MAP__CURRENCY_TRADE.get( currencyTradeID );
		} else {
//			LOG.info("------------ currencyTradeService.getByPK() once , currencyTradeID=" + currencyTradeID);
			TCurrencyTrade currencyTrade = currencyTradeService.getByPK( currencyTradeID );
			if (currencyTrade != null) {
				obj_currencyTrade = currencyTrade;
				MAP__CURRENCY_TRADE.put( currencyTradeID, currencyTrade );
			}
		}
		
		return obj_currencyTrade;
	}
	
	private String getLinePriceRedisKey(int currencyTradeID) {
		TCurrencyTrade obj_currencyTrade = getCurrencyTrade( currencyTradeID );
		if (obj_currencyTrade != null) {
			return IConstant.REDIS_PREFIX__LINE_PRICE + obj_currencyTrade.getSymbol(); // 要步步为营？
		}
		return null;
	}

	private void setLinePrice(int currencyTradeID, BigDecimal linePrice) {
		MAP__LINE_PRICE.put( currencyTradeID, linePrice );
		jedisCluster.set( getLinePriceRedisKey( currencyTradeID ), linePrice.toString() );
		LOG.info("修改行情价为：" + linePrice);
	}
	
	/**
	 * <p>
	 *   懒加载（非勤加载）
	 * </p>
	 * getLinePrice:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param currencyTradeID
	 * @return  
	 * @since JDK 1.8
	 */
	public BigDecimal getLinePrice(int currencyTradeID) {
		BigDecimal bd_linePrice = null;
		
		if (MAP__LINE_PRICE.containsKey( currencyTradeID )) {
			bd_linePrice = MAP__LINE_PRICE.get( currencyTradeID );
		} else {
			String str_linePriceRedisKey = getLinePriceRedisKey( currencyTradeID );
			if (str_linePriceRedisKey != null && str_linePriceRedisKey.length() > 0) {
				String str_linePriceRedisVal = jedisCluster.get( str_linePriceRedisKey );
				if (str_linePriceRedisVal != null && str_linePriceRedisVal.length() > 0) {
					bd_linePrice = new BigDecimal(str_linePriceRedisVal);
					MAP__LINE_PRICE.put( currencyTradeID, bd_linePrice );
				}
			}
		}
		
		return bd_linePrice;
	}
	
	
	/**
	 * <p>
	 *   首次需要从数据库加载是否有未撮合的委托信息。<br />
	 *   剩下未撮合的委托也可以直接入列表。
	 * </p>
	 * initialEntrust:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator    
	 * @since JDK 1.8
	 */
	public void initialEntrust() {
		TEntrust entrustQuery = new TEntrust();
		entrustQuery.in( TEntrust.FieldNames.STATUS, new Object[] { EntrustStatusEnumer.UNFINISH.getCode(), EntrustStatusEnumer.PART_FINISH.getCode() } );
		List<TEntrust> list_ent = entrustService.getsBy( entrustQuery ); // 需要按照时间或者ID升序排序。
		if (list_ent != null && list_ent.size() > 0) {
			for (int i = 0; i < list_ent.size(); i++) {
				makeAMatch( list_ent.get( i ) );
			}
			LOG.info( "加载了" + list_ent.size() + "个委托到内存。" );
		}
	}
	
	/**
	 * <pre>
	 * 买： 时间最早  排序
	 * 卖： 时间最早  排序
	 * </pre>
	 * 市价直接排在最后。
	 * <p>
	 *   0 => end, 新 => 旧。
	 * </p>
	 * @param entrust
	 * @param list
	 * @return
	 */
	private int findIndexFromMarket(TEntrust entrust, List<TEntrust> list) {
		return 0;
	}
	
	/**
	 * 买： 价格最高、时间最早  排序
	 * 卖： 价格最低、时间最早  排序
	 * <p>
	 *   买： 0 => end, 低 => 高。
	 *   卖： 0 => end, 高 => 低。
	 * </p>
	 * <p>
	 *   1、市价， 2、限价5， 3、市价， 4、限价4 => 顺序： 1、2、3、4 。
	 *   1、市价， 2、限价5， 3、市价， 4、限价6 => 顺序： 1、4、2、3 ？
	 * </p>
	 * @param entrust
	 * @param list
	 * @param compareVal
	 * @return
	 */
	private int findIndexFromLimit(TEntrust entrust, List<TEntrust> list, int compareVal) {
		int i_idx = -1;
		
		if (list.size() > 0) {
			for (i_idx = list.size() - 1; i_idx > -1; i_idx--) {
				TEntrust _entrust = list.get(i_idx);
				
				int i_compareTo = _entrust.getPrice().compareTo(entrust.getPrice()); // this : val, -1 = "<", 0 = "==", 1 = ">" .
				if (i_compareTo == compareVal) {
					break;
//				} else if (i_compareTo == 0) { // Price: this == val
//					i_compareTo = _entrust.getCreateTime().compareTo(entrust.getCreateTime());
//					if (i_compareTo == 1) { // CreateTime: this > val
//						
//					}
				}
			}
		}
		return i_idx + 1;
	}
	
	/**
	 * 获取成交价。
	 * @param price
	 * @param linePrice
	 * @return
	 */
	private BigDecimal getPrice(BigDecimal price, BigDecimal linePrice) {
		return BigDecimalUtil.isNullOrZero(price) ? linePrice : price;
	}
	
	/**
	 * 获取成交价根据行情价。
	 * @param price_buy
	 * @param price_sell
	 * @param linePrice
	 * @return
	 */
	private BigDecimal getPriceWithLinePrice(BigDecimal price_buy, BigDecimal price_sell, BigDecimal linePrice) {
		return price_buy.compareTo(price_sell) >= ICompareResultConstant.EQUAL ?
				price_buy.compareTo(linePrice) >= ICompareResultConstant.EQUAL && linePrice.compareTo(price_sell) >= ICompareResultConstant.EQUAL ?
						linePrice : 
							linePrice.compareTo(price_buy) == ICompareResultConstant.GREATER_THAN ? price_buy : price_sell
				:
				null
				;
	}
	
	/**
	 * （市市、限限、市限、限市）四种类型  获取成交价。
	 * @param entrust_before
	 * @param entrust_after
	 * @param linePrice
	 * @return
	 */
	private BigDecimal getDealPrice(TEntrust entrust_before, TEntrust entrust_after, BigDecimal linePrice) {
		BigDecimal bd_dealPrice = null;
		
		BigDecimal bd_beforePrice = getPrice(entrust_before.getPrice(), linePrice), bd_afterPrice = getPrice(entrust_after.getPrice(), linePrice);
		// 这里的买卖判断也可以由外面判断了， 再丢标志进来。
		if (
				entrust_before.getEntrustDirection() == EntrustDirectionEnumer.BUY.getCode()
				&& 
				entrust_after.getEntrustDirection() == EntrustDirectionEnumer.SELL.getCode()
				) {
			bd_dealPrice = getPriceWithLinePrice(bd_beforePrice, bd_afterPrice, linePrice);
		}
		// 这里的买卖判断也可以由外面判断了， 再丢标志进来。
		if (
				entrust_before.getEntrustDirection() == EntrustDirectionEnumer.SELL.getCode()
				&& 
				entrust_after.getEntrustDirection() == EntrustDirectionEnumer.BUY.getCode()
				) {
			bd_dealPrice = getPriceWithLinePrice(bd_afterPrice, bd_beforePrice, linePrice);
		}
		
		return bd_dealPrice;
	}
	
	/**
	 * <p>
	 *   设置委托的撮合状态。 （完成金额数、未完成数量、状态）
	 * </p>
	 * setRecordStatus:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param entrust
	 * @param recordAmount
	 * @param recordCount  
	 * @param currencyTrade 
	 * @since JDK 1.8
	 */
	private void setRecordStatus(TEntrust entrust, BigDecimal recordAmount, BigDecimal recordCount, TCurrencyTrade currencyTrade) {
		entrust.setSuccessAmount(entrust.getSuccessAmount().add(recordAmount).setScale(currencyTrade.getPriceDecimalDigits(), BigDecimal.ROUND_HALF_DOWN)); // IConstant.AMOUNT_DECIMAL_LENGTH
		if (
				entrust.getEntrustType() == EntrustTypeEnumer.LIMIT.getCode()
				|| 
				entrust.getEntrustDirection() == EntrustDirectionEnumer.SELL.getCode()
				) {
			entrust.setLeftCount(entrust.getLeftCount().subtract(recordCount).setScale(currencyTrade.getCountDecimalDigits(), BigDecimal.ROUND_HALF_DOWN)); // IConstant.COUNT_DECIMAL_LENGTH
//			entrust.setStatus(
//					BigDecimalUtil.isZero(entrust.getLeftCount()) ? 
//							EntrustStatusEnumer.FINISH.getCode() : 
//								EntrustStatusEnumer.PART_FINISH.getCode()
//								);
		} else {
//			entrust.setStatus(
//					entrust.getSuccessAmount().compareTo(entrust.getAmount()) >= ICompareResultConstant.EQUAL ? 
//							EntrustStatusEnumer.FINISH.getCode() : 
//								EntrustStatusEnumer.PART_FINISH.getCode()
//								);
		}
	}
	
	/**
	 * <p>
	 *   撮合成功， 修改委托信息和新增撮合信息。
	 * </p>
	 * addEntrustRecord:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param entrust_before
	 * @param entrust_after
	 * @param dealPrice  
	 * @return 
	 * @since JDK 1.8
	 */
	public TEntrustRecord addEntrustRecord(TEntrust entrust_before, TEntrust entrust_after, BigDecimal dealPrice) {
		BigDecimal 
			bd_leftCount_before, bd_leftCount_after, 
			bd_leftAmount_before, bd_leftAmount_after
			;
		BigDecimal bd_count, bd_amount;
		TCurrencyTrade currencyTrade = getCurrencyTrade(entrust_before.getCurrencyTradeId());
		
		/**
		 * 还剩余未撮合的数量和金额。
		 */
		if (
				entrust_before.getEntrustType() == EntrustTypeEnumer.MARKET.getCode()
				&&
				entrust_before.getEntrustDirection() == EntrustDirectionEnumer.BUY.getCode()
				) {
			bd_leftAmount_before = entrust_before.getAmount().subtract(entrust_before.getSuccessAmount()).setScale(currencyTrade.getPriceDecimalDigits(), BigDecimal.ROUND_HALF_DOWN);
			bd_leftAmount_after = entrust_after.getLeftCount().multiply(dealPrice).setScale(currencyTrade.getPriceDecimalDigits(), BigDecimal.ROUND_HALF_DOWN);
			
			bd_leftCount_before = bd_leftAmount_before.divide( dealPrice, currencyTrade.getCountDecimalDigits(), BigDecimal.ROUND_HALF_DOWN ); // IConstant.COUNT_DECIMAL_LENGTH
			bd_leftCount_after = entrust_after.getLeftCount();
		} else if (
				entrust_after.getEntrustType() == EntrustTypeEnumer.MARKET.getCode()
				&&
				entrust_after.getEntrustDirection() == EntrustDirectionEnumer.BUY.getCode()
				) {
			bd_leftAmount_before = entrust_before.getLeftCount().multiply(dealPrice).setScale(currencyTrade.getPriceDecimalDigits(), BigDecimal.ROUND_HALF_DOWN);
			bd_leftAmount_after = entrust_after.getAmount().subtract(entrust_after.getSuccessAmount()).setScale(currencyTrade.getPriceDecimalDigits(), BigDecimal.ROUND_HALF_DOWN);
			
			bd_leftCount_before = entrust_before.getLeftCount();
			bd_leftCount_after = bd_leftAmount_after.divide( dealPrice, currencyTrade.getCountDecimalDigits(), BigDecimal.ROUND_HALF_DOWN ); // IConstant.COUNT_DECIMAL_LENGTH
		} else {
			bd_leftAmount_before = entrust_before.getLeftCount().multiply(dealPrice).setScale(currencyTrade.getPriceDecimalDigits(), BigDecimal.ROUND_HALF_DOWN);
			bd_leftAmount_after = entrust_after.getLeftCount().multiply(dealPrice).setScale(currencyTrade.getPriceDecimalDigits(), BigDecimal.ROUND_HALF_DOWN);
			
			bd_leftCount_before = entrust_before.getLeftCount();
			bd_leftCount_after = entrust_after.getLeftCount();
		}
		
		/**
		 * 查看主单或被单哪个被撮合完了。
		 */
		int i_compareTo = bd_leftCount_before.compareTo(bd_leftCount_after);
		if (i_compareTo == ICompareResultConstant.LESS_THAN) { // 主单被撮合完
			bd_count = bd_leftCount_before;
			bd_amount = bd_leftAmount_before;
			
			entrust_before.setStatus(EntrustStatusEnumer.FINISH.getCode());
			entrust_after.setStatus(EntrustStatusEnumer.PART_FINISH.getCode());
		} else if (i_compareTo == ICompareResultConstant.EQUAL) { // 主被单都被撮合完
			bd_count = bd_leftCount_before;
			bd_amount = bd_leftAmount_before;
			
			entrust_before.setStatus(EntrustStatusEnumer.FINISH.getCode());
			entrust_after.setStatus(EntrustStatusEnumer.FINISH.getCode());
		} else /*if (i_compareTo == ICompareResultConstant.GREATER_THAN) */{ // 被单被撮合完
			bd_count = bd_leftCount_after;
			bd_amount = bd_leftAmount_after;
			
			entrust_before.setStatus(EntrustStatusEnumer.PART_FINISH.getCode());
			entrust_after.setStatus(EntrustStatusEnumer.FINISH.getCode());
		}
//		bd_amount = dealPrice.multiply(bd_count).setScale(IConstant.AMOUNT_DECIMAL_LENGTH, BigDecimal.ROUND_HALF_DOWN);
		
		/**
		 * 准备保存到数据库了。
		 */
		LocalDateTime createTime = LocalDateTime.now();
		
		// 修改委托
		{
			setRecordStatus(entrust_before, bd_amount, bd_count, currencyTrade);
			entrust_before.setUpdateTime( createTime );
			entrustService.updateOnMatch(
					entrust_before.getSuccessAmount(), 
					entrust_before.getLeftCount(), 
					entrust_before.getStatus(), 
					entrust_before.getUpdateTime(), 
					entrust_before.getId(), 
					entrust_before.getVersion()
					);
			entrust_before.setVersion( entrust_before.getVersion() + 1 );
		}
		{
			setRecordStatus(entrust_after, bd_amount, bd_count, currencyTrade);
			entrust_after.setUpdateTime( createTime );
			entrustService.updateOnMatch(
					entrust_after.getSuccessAmount(), 
					entrust_after.getLeftCount(), 
					entrust_after.getStatus(), 
					entrust_after.getUpdateTime(), 
					entrust_after.getId(), 
					entrust_after.getVersion()
					);
			entrust_after.setVersion( entrust_after.getVersion() + 1 );
		}
		
		// 新增撮合
		// 主动
		TEntrustRecord entrustRecord_before = new TEntrustRecord();
		entrustRecord_before.setId(SNOW_FLAKE__ENTRUST_RECORD.nextId());
		entrustRecord_before.setUserId(entrust_before.getUserId());
		entrustRecord_before.setRivalUserId(entrust_after.getUserId());
		entrustRecord_before.setEntrustId(entrust_before.getId());
		entrustRecord_before.setRivalEntrustId(entrust_after.getId());
		entrustRecord_before.setPrice(dealPrice);
		entrustRecord_before.setCount(bd_count);
		entrustRecord_before.setAmount(bd_amount);
		entrustRecord_before.setCurrencyTradeId(entrust_before.getCurrencyTradeId());
		entrustRecord_before.setIsActive(IsActiveEnumer.ACTIVE.getCode());
		entrustRecord_before.setEntrustDirection(entrust_before.getEntrustDirection());
		entrustRecord_before.setCreateTime( createTime );
		entrustRecordService.add(entrustRecord_before);
		// 被动
		TEntrustRecord entrustRecord_after = new TEntrustRecord();
		entrustRecord_after.setId(SNOW_FLAKE__ENTRUST_RECORD.nextId());
		entrustRecord_after.setUserId(entrust_after.getUserId());
		entrustRecord_after.setRivalUserId(entrust_before.getUserId());
		entrustRecord_after.setEntrustId(entrust_after.getId());
		entrustRecord_after.setRivalEntrustId(entrust_before.getId());
		entrustRecord_after.setPrice(dealPrice);
		entrustRecord_after.setCount(bd_count);
		entrustRecord_after.setAmount(bd_amount);
		entrustRecord_after.setCurrencyTradeId(entrust_after.getCurrencyTradeId());
		entrustRecord_after.setIsActive(IsActiveEnumer.UNACTIVE.getCode());
		entrustRecord_after.setEntrustDirection(entrust_after.getEntrustDirection());
		entrustRecord_after.setCreateTime( createTime );
		entrustRecordService.add(entrustRecord_after);
		
		return entrustRecord_before;
	}
	
	/**
	 * 修改行情价根据成交价
	 * @param dealPrice
	 * @param linePrice
	 * @param entrust
	 * @param entrustRecord
	 * @return
	 */
	private BigDecimal onEntrustRecord(BigDecimal dealPrice, BigDecimal linePrice, TEntrust entrust, TEntrustRecord entrustRecord) {
		if (linePrice.compareTo(dealPrice) != ICompareResultConstant.EQUAL) {
			setLinePrice( entrust.getCurrencyTradeId(), dealPrice );
		}
		{
			String str_entrustRecord = JSONUtil.toString(entrustRecord);
			// 异步通知。
			rabbitTemplate.convertAndSend(IQueueConstants.EXCHANGE_TOPIC, IQueueConstants.ROUTE_KEY__ENTRUST_RECORD, str_entrustRecord); // linePrice.toString()
//			rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//				System.out.println("消息唯一标识：" + correlationData);
//				System.out.println("消息确认结果：" + ack);
//				System.out.println("失败原因：" + cause);
//			});
//			rabbitTemplate.setReturnCallback((Message message, int replyCode, String replyText, String exchange, String routingKey) -> {
//				
//			});
			
			ES_KLINE.submit(() -> {
				klineService.modifyKLine( entrustRecord, dealPrice );
				return null;
			});
		}
		return dealPrice;
	}
	
	/**
	 * 撮合
	 * <p>
	 *   当然程序也可以写成专门针对某种类型的操作， 比如：市市、市限、限限、限市。 <br />
	 *   这样程序会多写一份类似的， 少部分一样， 但是准确从效率来讲会高一些。 不过现在应该这个可以忽略不计。
	 * </p>
	 * @param entrust_after 市价或者限价类型
	 * @param list
	 */
	private void matchWith(TEntrust entrust_after, List<TEntrust> list) {
		if (
				( // 还有未成交数 （这里的判断在第一次进来的时候都可以不用判断， 因为是新的一个委托单， 但是后面再进来时就有可能已经被撮合掉了， 所以就需要判断。  当然也可以把判断拿出去， 看你怎么写嘛！~~）
					entrust_after.getStatus() == EntrustStatusEnumer.UNFINISH.getCode()
					||
					entrust_after.getStatus() == EntrustStatusEnumer.PART_FINISH.getCode()
				)
				&& 
				list.size() > 0
				) {
			BigDecimal bd_linePrice = getLinePrice( entrust_after.getCurrencyTradeId() );
			for (int i = list.size() - 1; i > -1; i--) {
				TEntrust entrust_before = list.get(i);
				
				BigDecimal bd_dealPrice = getDealPrice(entrust_before, entrust_after, bd_linePrice);
				if (bd_dealPrice == null) { // 首个都不满足条件的话， 那后面的就更不满足条件了。 因为这里已经是按照价格排好序了的。
					break;
				} else { // 满足条件， 进行撮合。
					TEntrustRecord entrustRecord = addEntrustRecord(entrust_before, entrust_after, bd_dealPrice);
					bd_linePrice = onEntrustRecord(bd_dealPrice, bd_linePrice, entrust_after, entrustRecord);
					
					if (entrust_before.getStatus() == EntrustStatusEnumer.FINISH.getCode()) {
						list.remove(i);
					}
					if (entrust_after.getStatus() == EntrustStatusEnumer.FINISH.getCode()) {
						break;
					}
				}
			}
		}
	}
	
	private void addTo(int idx, TEntrust entrust, List<TEntrust> list_after, List<TEntrust> list_beforeMarket, List<TEntrust> list_beforeLimit) {
		if (idx == list_after.size()) { // isFirst
			matchWith(entrust, list_beforeMarket); // 和对手盘（市价）进行撮合。
			matchWith(entrust, list_beforeLimit); // 和对手盘（限价）进行撮合。
			if (
					entrust.getStatus() == EntrustStatusEnumer.UNFINISH.getCode()
					||
					entrust.getStatus() == EntrustStatusEnumer.PART_FINISH.getCode()
					) { // 有剩余的则加入列表。
				list_after.add(entrust);
			}
		} else {
			list_after.add(idx, entrust);
		}
	}
	
	private void makeAMatch(
			TEntrust entrust, 
			int limitCompareVal, 
			ArrayList<TEntrust> list_market_after, 
			ArrayList<TEntrust> list_limit_after, 
			ArrayList<TEntrust> list_market_before, 
			ArrayList<TEntrust> list_limit_before
			) {
		ReentrantLock lock = getLock( entrust.getCurrencyTradeId() );
		lock.lock();
		
		// 串行化执行？ 为了委托单在数据库和撮合时的顺序一致？
		entrustService.add(entrust);
		
		try {
			int i_idx;
			if (entrust.getEntrustType() == EntrustTypeEnumer.MARKET.getCode()) { // 市价
				i_idx = findIndexFromMarket(entrust, list_market_after);
				addTo(i_idx, entrust, list_market_after, list_market_before, list_limit_before);
			} else /*if (entrust.getEntrustType() == EntrustTypeEnumer.LIMIT.getCode()) */{ // 限价
				i_idx = findIndexFromLimit(entrust, list_limit_after, limitCompareVal);
				addTo(i_idx, entrust, list_limit_after, list_market_before, list_limit_before);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error( e.toString() );
		} finally {
			lock.unlock();
		}
	}
	
	public void makeAMatch(TEntrust entrust) {
		ArrayList<TEntrust> 
			list_buy_market = getList( MAP__BUY_MARKET, entrust.getCurrencyTradeId() ), 
			list_buy_limit = getList( MAP__BUY_LIMIT, entrust.getCurrencyTradeId() ), 
			list_sell_market = getList( MAP__SELL_MARKET, entrust.getCurrencyTradeId() ), 
			list_sell_limit = getList( MAP__SELL_LIMIT, entrust.getCurrencyTradeId() )
			;
		
		if (entrust.getEntrustDirection() == EntrustDirectionEnumer.BUY.getCode()) { // 买
			makeAMatch(
					entrust, 
					ICompareResultConstant.LESS_THAN, 
					list_buy_market, 
					list_buy_limit, 
					list_sell_market, list_sell_limit
					);
		} else /*if (entrust.getEntrustDirection() == EntrustDirectionEnumer.SELL.getCode()) */{ // 卖
			makeAMatch(
					entrust, 
					ICompareResultConstant.GREATER_THAN, 
					list_sell_market, 
					list_sell_limit, 
					list_buy_market, list_buy_limit
					);
		}
	}
	
	private static final void print(ConcurrentHashMap<Integer, ArrayList<TEntrust>> map) {
		for (Iterator<Entry<Integer, ArrayList<TEntrust>>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Entry<Integer, ArrayList<TEntrust>> entry = iterator.next();
			
			ArrayList<TEntrust> list = entry.getValue();
			System.out.println( "entry.getKey()=" + entry.getKey() + ", list.size()=" + list.size() );
			for (int i = 0, len = list.size(); i < len; i++) {
				TEntrust e = list.get(i);
				System.out.println(e.getUserId() + ", " + e.getCurrencyTradeId() + ", " + e.getEntrustDirection() + ", " + e.getEntrustType() + ", " + e.getPrice() + ", " + e.getCount());
			}
		}
	}
	
	private static void print() {
		System.out.println("MAP_BUY_MARKET.size()=" + MAP__BUY_MARKET.size());
		System.out.println("MAP_BUY_LIMIT.size()=" + MAP__BUY_LIMIT.size());
		System.out.println("MAP_SELL_MARKET.size()=" + MAP__SELL_MARKET.size());
		System.out.println("MAP_SELL_LIMIT.size()=" + MAP__SELL_LIMIT.size());
		System.out.println("buy ---------------------------------------------");
		print(MAP__BUY_MARKET);
		print(MAP__BUY_LIMIT);
		System.out.println("sell ---------------------------------------------");
		print(MAP__SELL_MARKET);
		print(MAP__SELL_LIMIT);
	}
	
	public void test() {
		TEntrust entrust1 = new TEntrust();
		entrust1.setUserId(101L);
		entrust1.setCurrencyTradeId(1);
		entrust1.setEntrustType(EntrustTypeEnumer.MARKET.getCode());
		entrust1.setEntrustDirection(EntrustDirectionEnumer.BUY.getCode());
		entrust1.setAmount(new BigDecimal("1768.14"));
//		entrustService.add(entrust1);
		makeAMatch(entrust1);
		
		TEntrust entrust2 = new TEntrust();
		entrust2.setUserId(102L);
		entrust2.setCurrencyTradeId(1);
		entrust2.setEntrustType(EntrustTypeEnumer.MARKET.getCode());
		entrust2.setEntrustDirection(EntrustDirectionEnumer.SELL.getCode());
		entrust2.setCount(new BigDecimal("80.390243"));
//		entrustService.add(entrust2);
		makeAMatch(entrust2);
		
		TEntrust entrust3 = new TEntrust();
		entrust3.setUserId(103L);
		entrust3.setCurrencyTradeId(1);
		entrust3.setEntrustType(EntrustTypeEnumer.MARKET.getCode());
		entrust3.setEntrustDirection(EntrustDirectionEnumer.SELL.getCode());
		entrust3.setCount(new BigDecimal("23.303251"));
//		entrustService.add(entrust3);
		makeAMatch(entrust3);
		
		TEntrust entrust4 = new TEntrust();
		entrust4.setUserId(104L);
		entrust4.setCurrencyTradeId(1);
		entrust4.setEntrustType(EntrustTypeEnumer.LIMIT.getCode());
		entrust4.setEntrustDirection(EntrustDirectionEnumer.BUY.getCode());
		entrust4.setPrice(new BigDecimal("220.49"));
		entrust4.setCount(new BigDecimal("7.056351"));
//		entrustService.add(entrust4);
		makeAMatch(entrust4);
		
		TEntrust entrust5 = new TEntrust();
		entrust5.setUserId(105L);
		entrust5.setCurrencyTradeId(1);
		entrust5.setEntrustType(EntrustTypeEnumer.LIMIT.getCode());
		entrust5.setEntrustDirection(EntrustDirectionEnumer.BUY.getCode());
		entrust5.setPrice(new BigDecimal("241.33"));
		entrust5.setCount(new BigDecimal("89.707095"));
//		entrustService.add(entrust5);
		makeAMatch(entrust5);
		
		
		// print .
//		print();
		
		synchronized (MakeAMatchServiceImpl.class) {
			try {
				MakeAMatchServiceImpl.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@javax.annotation.PostConstruct
	public void pc() {
		System.out.println( "com.bittrade.entrust.service.impl.MakeAMatchServiceImpl.pc()" );
//		initialEntrust();
		System.out.println( entrustService );
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println( "com.bittrade.entrust.service.impl.MakeAMatchServiceImpl.afterPropertiesSet()" );
		System.out.println( entrustService );
//		initialEntrust();
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println( "com.bittrade.entrust.service.impl.MakeAMatchServiceImpl.postProcessBeanFactory(ConfigurableListableBeanFactory)" );
		System.out.println( entrustService );
	}
	
	public static void _main(String[] args) {
//		Tester.test(null);
//		BigDecimal bd = new BigDecimal(123.12345678901);
//		System.out.println(bd);
//		System.out.println(bd.setScale(11, BigDecimal.ROUND_HALF_DOWN));
//		System.out.println(bd);
//		System.out.println(BigDecimal.valueOf(123.12345678901));
//		System.out.println(new BigDecimal(123.12345678901, new MathContext(14, RoundingMode.HALF_DOWN)));
//		System.out.println(new BigDecimal(123.12345678901, new MathContext(20, RoundingMode.HALF_DOWN)));
		
//		BigDecimal bd1 = new BigDecimal(1.3);
//		BigDecimal bd2 = new BigDecimal(1.5);
//		System.out.println(bd1.multiply(bd2));
//		System.out.println(bd1.multiply(bd2, new MathContext(9, RoundingMode.HALF_DOWN)));
//		System.out.println(bd1.multiply(bd2).setScale(8, BigDecimal.ROUND_HALF_DOWN));
//		System.out.println(bd1.multiply(bd2).multiply(BigDecimal.valueOf(2)));
//		System.out.println(bd1.multiply(bd2, new MathContext(9, RoundingMode.HALF_DOWN)).multiply(BigDecimal.valueOf(2)));
//		System.out.println(bd1.multiply(bd2).setScale(8, BigDecimal.ROUND_HALF_DOWN).multiply(BigDecimal.valueOf(2)));
		
		BigDecimal bd1 = new BigDecimal("1.12");
		BigDecimal bd2 = new BigDecimal("2.98");
		System.out.println(bd1.multiply(bd2));
		System.out.println(bd1.multiply(bd2).setScale(2, BigDecimal.ROUND_HALF_DOWN));
	}

//	@RabbitListener(queues = { IQueueConstants.QUEUE__ENTRUST_RECORD })
//	public void processMessage(Channel channel, Message message) {
//		System.out.println("QUEUE__ENTRUST_RECORD -- MessageConsumer收到消息：" + new String(message.getBody()));
//		try {
//			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

//	@RabbitListener(queues = { IQueueConstants.QUEUE__KLINE + 60 })
//	public void processMessage_2(Channel channel, Message message) {
//		System.out.println("QUEUE__KLINE -- MessageConsumer收到所有消息：" + new String(message.getBody()));
//		try {
//			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void testMQ() {
		try {
//			rabbitTemplate.convertAndSend(IQueueConstants.EXCHANGE, IQueueConstants.ROUTE_KEY__ENTRUST_RECORD, "-toString()-");
			rabbitTemplate.convertAndSend(IQueueConstants.EXCHANGE_TOPIC, IQueueConstants.ROUTE_KEY__ENTRUST_RECORD, IQueueConstants.ROUTE_KEY__ENTRUST_RECORD);
			rabbitTemplate.convertAndSend(IQueueConstants.EXCHANGE_TOPIC, IQueueConstants.ROUTE_KEY__KLINE, IQueueConstants.ROUTE_KEY__KLINE);
			rabbitTemplate.convertAndSend(IQueueConstants.EXCHANGE_TOPIC, "route.key..1", "route.key..1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println( "----------- convertAndSend() OK ~~" );
	}
	
}
