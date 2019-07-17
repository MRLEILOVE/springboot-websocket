package com.bittrade.entrust.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bittrade.common.constant.IConstant;
import com.bittrade.common.constant.IQueueConstants;
import com.bittrade.common.enums.EntrustDirectionEnumer;
import com.bittrade.common.enums.EntrustStatusEnumer;
import com.bittrade.common.enums.EntrustTypeEnumer;
import com.bittrade.common.enums.IsActiveEnumer;
import com.bittrade.entrust.api.service.IMakeAMatchService;
import com.bittrade.entrust.api.service.ITEntrustRecordService;
import com.bittrade.entrust.api.service.ITEntrustService;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.model.TEntrustRecord;
import com.core.common.constant.ICompareResultConstant;
import com.core.tool.BigDecimalUtil;
import com.core.tool.SnowFlake;
import com.rabbitmq.client.Channel;

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
public class MakeAMatchServiceImpl implements IMakeAMatchService {
	
	private static final Logger LOG = LoggerFactory.getLogger(MakeAMatchServiceImpl.class);
	
	/**
	 * 市价买
	 */
	private static final ConcurrentHashMap<Integer, ArrayList<TEntrust>> MAP_BUY_MARKET = new ConcurrentHashMap<>();
	
	/**
	 * 限价买
	 */
	private static final ConcurrentHashMap<Integer, ArrayList<TEntrust>> MAP_BUY_LIMIT = new ConcurrentHashMap<>();
	
	/**
	 * 市价卖
	 */
	private static final ConcurrentHashMap<Integer, ArrayList<TEntrust>> MAP_SELL_MARKET = new ConcurrentHashMap<>();
	
	/**
	 * 限价卖
	 */
	private static final ConcurrentHashMap<Integer, ArrayList<TEntrust>> MAP_SELL_LIMIT = new ConcurrentHashMap<>();
	
	private static final SnowFlake SNOW_FLAKE__ENTRUST_RECORD = new SnowFlake(1, 1);
	
	@Autowired
	private ITEntrustService entrustService;
	@Autowired
	private ITEntrustRecordService entrustRecordService;
	
	/**
	 * 行情价
	 */
	public static /* final */BigDecimal LINE_PRICE = new BigDecimal(0);
	
	private static final ConcurrentHashMap<Integer, ReentrantLock> MAP_LOCK__BUY_AND_SELL = new ConcurrentHashMap<>();
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
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
	 * @return
	 */
	private BigDecimal getPrice(BigDecimal price) {
		return BigDecimalUtil.isNullOrZero(price) ? LINE_PRICE : price;
	}
	
	/**
	 * 获取成交价根据行情价。
	 * @param price_buy
	 * @param price_sell
	 * @return
	 */
	private BigDecimal getPriceWithLinePrice(BigDecimal price_buy, BigDecimal price_sell) {
		return price_buy.compareTo(price_sell) >= ICompareResultConstant.EQUAL ?
				price_buy.compareTo(LINE_PRICE) >= ICompareResultConstant.EQUAL && LINE_PRICE.compareTo(price_sell) >= ICompareResultConstant.EQUAL ?
						LINE_PRICE : 
							LINE_PRICE.compareTo(price_buy) == ICompareResultConstant.GREATER_THAN ? price_buy : price_sell
				:
				null
				;
	}
	
	/**
	 * （市市、限限、市限、限市）四种类型  获取成交价。
	 * @param entrust_before
	 * @param entrust_after
	 * @return
	 */
	private BigDecimal getDealPrice(TEntrust entrust_before, TEntrust entrust_after) {
		BigDecimal bd_dealPrice = null;
		
		BigDecimal bd_beforePrice = getPrice(entrust_before.getPrice()), bd_afterPrice = getPrice(entrust_after.getPrice());
		if (
				entrust_before.getEntrustDirection() == EntrustDirectionEnumer.BUY.getCode()
				&& 
				entrust_after.getEntrustDirection() == EntrustDirectionEnumer.SELL.getCode()
				) {
			bd_dealPrice = getPriceWithLinePrice(bd_beforePrice, bd_afterPrice);
		}
		if (
				entrust_before.getEntrustDirection() == EntrustDirectionEnumer.SELL.getCode()
				&& 
				entrust_after.getEntrustDirection() == EntrustDirectionEnumer.BUY.getCode()
				) {
			bd_dealPrice = getPriceWithLinePrice(bd_afterPrice, bd_beforePrice);
		}
		
		return bd_dealPrice;
	}
	
	private void addEntrustRecord(TEntrust entrust_before, TEntrust entrust_after, BigDecimal dealPrice) {
		BigDecimal count;
		BigDecimal amount;
		if (entrust_before.getLeftCount().compareTo(entrust_after.getLeftCount()) == ICompareResultConstant.LESS_THAN) {
			count = entrust_before.getLeftCount();
		} else {
			count = entrust_after.getLeftCount();
		}
		amount = dealPrice.multiply(count).setScale(IConstant.AMOUNT_DECIMAL_LENGTH, BigDecimal.ROUND_HALF_DOWN);
		
		// 修改委托
		{
			entrust_before.setLeftCount(entrust_before.getLeftCount().subtract(count).setScale(IConstant.COUNT_DECIMAL_LENGTH, BigDecimal.ROUND_HALF_DOWN));
			entrust_before.setSuccessAmount(entrust_before.getSuccessAmount().add(amount).setScale(IConstant.AMOUNT_DECIMAL_LENGTH, BigDecimal.ROUND_HALF_DOWN));
			entrustService.updateOnMatch(
					entrust_before.getSuccessAmount(), 
					entrust_before.getLeftCount(), 
					BigDecimalUtil.isZero(entrust_before.getLeftCount()) ? EntrustStatusEnumer.FINISH.getCode() : EntrustStatusEnumer.PART_FINISH.getCode(), 
					entrust_before.getId()
					);
//			System.out.println(
//					"entrust_before.getSuccessAmount()=" + entrust_before.getSuccessAmount() + ", " + 
//					"entrust_before.getLeftCount()=" + entrust_before.getLeftCount() + ", " + 
//					(BigDecimalUtil.isZero(entrust_before.getLeftCount()) ? EntrustStatusEnumer.FINISH.getCode() : EntrustStatusEnumer.PART_FINISH.getCode()) + ", " + 
//					entrust_before.getId()
//					);
		}
		{
			entrust_after.setLeftCount(entrust_after.getLeftCount().subtract(count).setScale(IConstant.COUNT_DECIMAL_LENGTH, BigDecimal.ROUND_HALF_DOWN));
			entrust_after.setSuccessAmount(entrust_after.getSuccessAmount().add(amount).setScale(IConstant.AMOUNT_DECIMAL_LENGTH, BigDecimal.ROUND_HALF_DOWN));
			entrustService.updateOnMatch(
					entrust_after.getSuccessAmount(), 
					entrust_after.getLeftCount(), 
					BigDecimalUtil.isZero(entrust_after.getLeftCount()) ? EntrustStatusEnumer.FINISH.getCode() : EntrustStatusEnumer.PART_FINISH.getCode(), 
					entrust_after.getId()
					);
//			System.out.println(
//					"entrust_after.getSuccessAmount()=" + entrust_after.getSuccessAmount() + ", " + 
//					"entrust_after.getLeftCount()=" + entrust_after.getLeftCount() + ", " + 
//					(BigDecimalUtil.isZero(entrust_after.getLeftCount()) ? EntrustStatusEnumer.FINISH.getCode() : EntrustStatusEnumer.PART_FINISH.getCode()) + ", " + 
//					entrust_after.getId()
//					);
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
		entrustRecord_before.setCount(count);
		entrustRecord_before.setAmount(amount);
		entrustRecord_before.setCurrencyTradeId(entrust_before.getCurrencyTradeId());
		entrustRecord_before.setIsActive(IsActiveEnumer.ACTIVE.getCode());
		entrustRecord_before.setEntrustDirection(entrust_before.getEntrustDirection());
		entrustRecordService.add(entrustRecord_before);
//		System.out.println(entrustRecord_before);
		// 被动
		TEntrustRecord entrustRecord_after = new TEntrustRecord();
		entrustRecord_after.setId(SNOW_FLAKE__ENTRUST_RECORD.nextId());
		entrustRecord_after.setUserId(entrust_after.getUserId());
		entrustRecord_after.setRivalUserId(entrust_before.getUserId());
		entrustRecord_after.setEntrustId(entrust_after.getId());
		entrustRecord_after.setRivalEntrustId(entrust_before.getId());
		entrustRecord_after.setPrice(dealPrice);
		entrustRecord_after.setCount(count);
		entrustRecord_after.setAmount(amount);
		entrustRecord_after.setCurrencyTradeId(entrust_after.getCurrencyTradeId());
		entrustRecord_after.setIsActive(IsActiveEnumer.UNACTIVE.getCode());
		entrustRecord_after.setEntrustDirection(entrust_after.getEntrustDirection());
		entrustRecordService.add(entrustRecord_after);
//		System.out.println(entrustRecord_after);
	}
	
	/**
	 * 修改行情价根据成交价
	 * @param dealPrice
	 */
	private void changeLinePrice(BigDecimal dealPrice) {
		if (LINE_PRICE.compareTo(dealPrice) != ICompareResultConstant.EQUAL) {
			LINE_PRICE = dealPrice;
			// 异步通知。
			rabbitTemplate.convertAndSend(IQueueConstants.EXCHANGE, IQueueConstants.ROUTE_KEY__LINE_PRICE, LINE_PRICE.toString());
//			rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//				System.out.println("消息唯一标识：" + correlationData);
//				System.out.println("消息确认结果：" + ack);
//				System.out.println("失败原因：" + cause);
//			});
//			rabbitTemplate.setReturnCallback((Message message, int replyCode, String replyText, String exchange, String routingKey) -> {
//				
//			});
			LOG.info("修改行情价为：" + LINE_PRICE);
		}
	}
	
	@RabbitListener(queues = { IQueueConstants.QUEUE__LINE_PRICE })
	public void processMessage(Channel channel, Message message) {
		System.out.println("MessageConsumer收到消息：" + new String(message.getBody()));
		try {
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 撮合
	 * <p>
	 *   当然程序也可以写成专门针对某种类型的操作， 比如：市市、市限、限限、限市。 <br />
	 *   这样程序会多写一份类似的， 少部分一样， 但是准确从效率来讲会高一些。 不过现在应该这个可以忽略不计。
	 * </p>
	 * @param entrust 市价或者限价类型
	 * @param list
	 */
	private void matchWith(TEntrust entrust, List<TEntrust> list) {
		if (
				entrust.getLeftCount().compareTo(BigDecimal.ZERO) == ICompareResultConstant.GREATER_THAN // 还有未成交数
				&& 
				list.size() > 0
				) {
			for (int i = list.size() - 1; i > -1; i--) {
				TEntrust entrust_ = list.get(i);
				
				BigDecimal bd_dealPrice = getDealPrice(entrust_, entrust);
				if (bd_dealPrice == null) {
					break;
				} else {
					addEntrustRecord(entrust_, entrust, bd_dealPrice);
					changeLinePrice(bd_dealPrice);
					
					if (entrust_.getLeftCount().compareTo(BigDecimal.ZERO) == ICompareResultConstant.EQUAL) {
						list.remove(i);
					}
					if (entrust.getLeftCount().compareTo(BigDecimal.ZERO) == ICompareResultConstant.EQUAL) {
						break;
					}
				}
			}
		}
	}
	
	private void addTo(int idx, TEntrust entrust, List<TEntrust> list_after, List<TEntrust> list_beforeMarket, List<TEntrust> list_beforeLimit) {
		if (idx == list_after.size()) { // isFirst
			matchWith(entrust, list_beforeMarket); // 市价和对手盘（卖）进行撮合。
			matchWith(entrust, list_beforeLimit); // 市价和对手盘（卖）进行撮合。
			if (entrust.getLeftCount().compareTo(BigDecimal.ZERO) == ICompareResultConstant.GREATER_THAN) { // 有剩余的则加入列表。
				list_after.add(entrust);
			}
		} else {
			list_after.add(idx, entrust);
		}
	}
	
	private ReentrantLock getLock(ConcurrentHashMap<Integer, ReentrantLock> map, Integer key) {
		ReentrantLock lock;
		
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
	
	private void makeAMatch(
			TEntrust entrust, 
			int limitCompareVal, 
			ArrayList<TEntrust> list_market_after, 
			ArrayList<TEntrust> list_limit_after, 
			ArrayList<TEntrust> list_market_before, 
			ArrayList<TEntrust> list_limit_before
			) {
		ReentrantLock lock = getLock( MAP_LOCK__BUY_AND_SELL, entrust.getCurrencyTradeId() );
		lock.lock();
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
		} finally {
			lock.unlock();
		}
	}
	
	public void makeAMatch(TEntrust entrust) {
		ArrayList<TEntrust> 
			list_buy_market = getList( MAP_BUY_MARKET, entrust.getCurrencyTradeId() ), 
			list_buy_limit = getList( MAP_BUY_LIMIT, entrust.getCurrencyTradeId() ), 
			list_sell_market = getList( MAP_SELL_MARKET, entrust.getCurrencyTradeId() ), 
			list_sell_limit = getList( MAP_SELL_LIMIT, entrust.getCurrencyTradeId() )
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
		System.out.println("MAP_BUY_MARKET.size()=" + MAP_BUY_MARKET.size());
		System.out.println("MAP_BUY_LIMIT.size()=" + MAP_BUY_LIMIT.size());
		System.out.println("MAP_SELL_MARKET.size()=" + MAP_SELL_MARKET.size());
		System.out.println("MAP_SELL_LIMIT.size()=" + MAP_SELL_LIMIT.size());
		System.out.println("buy ---------------------------------------------");
		print(MAP_BUY_MARKET);
		print(MAP_BUY_LIMIT);
		System.out.println("sell ---------------------------------------------");
		print(MAP_SELL_MARKET);
		print(MAP_SELL_LIMIT);
	}
	
	public void test() {
		TEntrust entrust1 = new TEntrust();
		entrust1.setUserId(101L);
		entrust1.setCurrencyTradeId(102);
		entrust1.setEntrustDirection(EntrustDirectionEnumer.BUY.getCode());
		entrust1.setEntrustType(EntrustTypeEnumer.MARKET.getCode());
		entrust1.setCount(new BigDecimal(50));
		entrustService.add(entrust1);
		makeAMatch(entrust1);
		TEntrust entrust2 = new TEntrust();
		entrust2.setUserId(102L);
		entrust2.setCurrencyTradeId(102);
		entrust2.setEntrustDirection(EntrustDirectionEnumer.BUY.getCode());
		entrust2.setEntrustType(EntrustTypeEnumer.LIMIT.getCode());
		entrust2.setPrice(new BigDecimal(157));
		entrust2.setCount(new BigDecimal(28));
		entrustService.add(entrust2);
		makeAMatch(entrust2);
		TEntrust entrust3 = new TEntrust();
		entrust3.setUserId(103L);
		entrust3.setCurrencyTradeId(102);
		entrust3.setEntrustDirection(EntrustDirectionEnumer.SELL.getCode());
		entrust3.setEntrustType(EntrustTypeEnumer.LIMIT.getCode());
		entrust3.setPrice(new BigDecimal(101));
		entrust3.setCount(new BigDecimal(37));
		entrustService.add(entrust3);
		makeAMatch(entrust3);
		TEntrust entrust4 = new TEntrust();
		entrust4.setUserId(104L);
		entrust4.setCurrencyTradeId(102);
		entrust4.setEntrustDirection(EntrustDirectionEnumer.SELL.getCode());
		entrust4.setEntrustType(EntrustTypeEnumer.MARKET.getCode());
		entrust4.setCount(new BigDecimal(80));
		entrustService.add(entrust4);
		makeAMatch(entrust4);
		TEntrust entrust5 = new TEntrust();
		entrust5.setUserId(105L);
		entrust5.setCurrencyTradeId(102);
		entrust5.setEntrustDirection(EntrustDirectionEnumer.BUY.getCode());
		entrust5.setEntrustType(EntrustTypeEnumer.MARKET.getCode());
		entrust5.setCount(new BigDecimal(83));
		entrustService.add(entrust5);
		makeAMatch(entrust5);
		
		// print .
		print();
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
	}
	
}
