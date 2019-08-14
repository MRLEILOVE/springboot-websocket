package com.bittrade.entrust.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bittrade.__default.service.impl.DefaultTKlineServiceImpl;
import com.bittrade.common.constant.IConstant;
import com.bittrade.common.constant.IQueueConstants;
import com.bittrade.common.enums.KLineGranularityEnumer;
import com.bittrade.currency.api.service.ITCurrencyTradeService;
import com.bittrade.entrust.api.service.ITKlineService;
import com.bittrade.entrust.dao.ITKlineDAO;
import com.bittrade.pojo.dto.QueryKLineDto;
import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.pojo.model.TKline;
import com.bittrade.pojo.vo.QueryKLineVO;
import com.bittrade.pojo.vo.TKlineVO;
import com.core.common.constant.ICompareResultConstant;
import com.core.tool.DateTimeUtil;

import redis.clients.jedis.JedisCluster;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TKlineServiceImpl extends DefaultTKlineServiceImpl<ITKlineDAO, TKline, TKlineDTO, TKlineVO> implements ITKlineService {

	private static final Logger															LOG	= LoggerFactory.getLogger( TKlineServiceImpl.class );

	@Autowired
	private ITKlineDAO																	klineDAO;
	@Reference
	private ITCurrencyTradeService														currencyTradeService;
	@Autowired
	private MakeAMatchServiceImpl														makeAMatchService;
	@Autowired
	private RabbitTemplate																rabbitTemplate;
	@Autowired
	private JedisCluster																jedisCluster;

	/**
	 * <pre>
	 * pattern:
	 *   {
	 *     "Granularity_1": { "currencyTradeId_1": Kline, "currencyTradeId_2": Kline }, 
	 *     "Granularity_2": { "currencyTradeId_1": Kline, "currencyTradeId_2": Kline }
	 *   }
	 * example:
	 *   {
	 *     "60": { "1": Kline, "2": Kline }, 
	 *     "180": { "1": Kline, "2": Kline }
	 *   }
	 * </pre>
	 */
	private static final ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, TKline>>	MAP__KLINE_LAST;
	static {
		KLineGranularityEnumer objArr_enum[] = KLineGranularityEnumer.values();
		if (objArr_enum != null && objArr_enum.length > 0) {
			MAP__KLINE_LAST = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, TKline>>( objArr_enum.length );
			for (int i = 0; i < objArr_enum.length; i++) {
				MAP__KLINE_LAST.put( objArr_enum[ i ].getCode(), new ConcurrentHashMap<Integer, TKline>() );
			}
		} else {
			MAP__KLINE_LAST = null;
		}
	};

	/**
	 * <p>
	 * 初始化K线最后的数据。
	 * </p>
	 * initializeKLineLast:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @since JDK 1.8
	 */
	public void initialUnfinishKLine() {
		KLineGranularityEnumer objArr_enum[] = KLineGranularityEnumer.values();

		TCurrencyTrade currencyTradeQuery = new TCurrencyTrade();
		currencyTradeQuery.field( "id", "symbol" );
		List<TCurrencyTrade> list_ct = currencyTradeService.getsBy( currencyTradeQuery );

		if (objArr_enum != null && objArr_enum.length > 0 && list_ct != null && list_ct.size() > 0) {
			LocalDateTime dt_now = LocalDateTime.now();
			TKline tKlineQuery = new TKline();
			tKlineQuery.field( "id", "high", "low", "close", "volume", "time" );

			for (int i = 0; i < objArr_enum.length; i++) {
				KLineGranularityEnumer obj_enum = objArr_enum[ i ];
				int i_code = obj_enum.getCode();
				LocalDateTime dt_granularity = getDateTimeBegin( dt_now, i_code );

				tKlineQuery.setGranularity( i_code );
				tKlineQuery.setTime( dt_granularity );
				ConcurrentHashMap<Integer, TKline> map_kline = MAP__KLINE_LAST.get( i_code );

				for (int j = 0; j < list_ct.size(); j++) {
					TCurrencyTrade currencyTrade = list_ct.get( j );

					tKlineQuery.setSymbol( currencyTrade.getSymbol() );
					TKline kline = klineDAO.getBy( tKlineQuery );
					if (kline != null) {
						map_kline.put( currencyTrade.getId(), kline );
					}
				}
			}
			LOG.info( "加载最新K线数据到内存OK~~" );
		}
	}

	private TKline getKLine(Map<Integer, TKline> map, int key) {
		TKline kLine;

		if (map.containsKey( key )) {
			kLine = map.get( key );
		} else {
			map.put( key, kLine = new TKline() );
		}

		return kLine;
	}

	private static LocalDateTime getDateTimeBegin(LocalDateTime dt, int granularity) {
		LocalDateTime ldt = null;

		// 先这样判断吧， 要不然呢？ 也可以按照 大于等于 来判断。 这样值得意思更清楚点，但是由于 小时间单位 的值会更多，
		// 所以就把判断提到最前， 这样不用每次都判断那么多， 呵呵。
		if (granularity <= KLineGranularityEnumer.THIRTY_MINUTE.getCode()) {
			ldt = DateTimeUtil.getMinuteBegin( dt, granularity /= 60 );
		} else if (granularity <= KLineGranularityEnumer.TWELVE_HOUR.getCode()) {
			ldt = DateTimeUtil.getHourBegin( dt, granularity /= 3600 );
		} else /*
				 * if (granularity <= KLineGranularityEnumer.ONE_WEEK.getCode())
				 */ {
			ldt = DateTimeUtil.getDayBegin( dt, granularity /= 86400 );
		}

		return ldt;
	}

	private String kLineToString(TKline kline) {
		// [symbol,time,open,high,low,close,volume]
		// ["BTC-USDT","2019-07-18
		// 17:00:00.00",9783.2,9787.9,9778.3,9782,3.40246089]
		return new StringBuilder( "[" ).append( '\"' ).append( kline.getSymbol() ).append( "\"," ).append( '\"' )
				.append( DateTimeUtil.toString( kline.getTime() ) ).append( "\"," ).append( kline.getOpen() ).append( ',' ).append( kline.getHigh() )
				.append( ',' ).append( kline.getLow() ).append( ',' ).append( kline.getClose() ).append( ',' ).append( kline.getVolume() )
				.append( ']' ).toString();
	}

	@Override
	public void modifyKLine(TEntrustRecord entrustRecord, BigDecimal dealPrice) {
		System.out.println( System.currentTimeMillis() + ", dealPrice=" + dealPrice );
		KLineGranularityEnumer objArr_enum[] = KLineGranularityEnumer.values();
		for (int i = 0; i < objArr_enum.length; i++) {
			int i_code = objArr_enum[ i ].getCode();

			try {
				TKline kLine = getKLine( MAP__KLINE_LAST.get( i_code ), entrustRecord.getCurrencyTradeId() );
				LocalDateTime dt_updateTime = getDateTimeBegin( entrustRecord.getCreateTime(), i_code );
				if (kLine.getTime() == null || kLine.getTime().compareTo( dt_updateTime ) == ICompareResultConstant.LESS_THAN) {
					kLine.setSymbol( makeAMatchService.getCurrencyTrade( entrustRecord.getCurrencyTradeId() ).getSymbol() );
					kLine.setTime( dt_updateTime );
					kLine.setOpen( dealPrice );
					kLine.setHigh( dealPrice );
					kLine.setLow( dealPrice );
					kLine.setClose( dealPrice );
					kLine.setVolume( entrustRecord.getCount() );
					kLine.setGranularity( i_code );
					kLine.setCreateTime( LocalDateTime.now() );
					klineDAO.add( kLine );
					LOG.info( "kLine.getId()=" + kLine.getId() );
				} else /* if (kLine.getTime().compareTo(dt_updateTime) == 0) */ {
					if (dealPrice.compareTo( kLine.getHigh() ) == ICompareResultConstant.GREATER_THAN) {
						kLine.setHigh( dealPrice );
					}
					if (dealPrice.compareTo( kLine.getLow() ) == ICompareResultConstant.LESS_THAN) {
						kLine.setLow( dealPrice );
					}
					kLine.setClose( dealPrice );
					kLine.setVolume( kLine.getVolume().add( entrustRecord.getCount() ) );
					kLine.setUpdateTime( LocalDateTime.now() );
					klineDAO.modifyByPK( kLine );
				}

				rabbitTemplate.convertAndSend( IQueueConstants.EXCHANGE_TOPIC, IQueueConstants.ROUTE_KEY__KLINE + i_code, kLineToString( kLine ) );
			} catch (Exception e) {
				LOG.error( e.toString() );
			}
		}
	}

	/**
	 * k线查询
	 */
	@Override
	public List<QueryKLineVO> queryKLine(QueryKLineDto queryKLineDto) {
		return klineDAO.queryKLine( queryKLineDto );
	}

	/**
	 * 根据交易对查询最新k线
	 * 
	 * @param currencyTradeId
	 *            交易对id
	 */
	@Override
	public QueryKLineVO queryKLineBySymbol(Integer currencyTradeId) {
		String symbol = makeAMatchService.getCurrencyTrade( currencyTradeId ).getSymbol();
		// LocalDateTime time = getDateTimeBegin(LocalDateTime.now(),
		// KLineGranularityEnumer.ONE_MINUTE.getCode());
		// 2019-08-08 10:38:00
		LocalDateTime time = LocalDateTime.of( 2019, 8, 8, 10, 38, 0 );
		QueryKLineVO vo = klineDAO.queryKLineByCondition( symbol, KLineGranularityEnumer.ONE_MINUTE.getCode(), time );
		// 获取行情价
		BigDecimal price = new BigDecimal( jedisCluster.get( IConstant.REDIS_PREFIX__LINE_PRICE + symbol ) );
		vo.setPrice( price );

		// 计算涨跌幅 24小时涨幅=（最新价-24小时开盘价） / 最新价
		// 获取24小时前的收盘价
		// LocalDateTime yesterdayTime =
		// getDateTimeBegin(LocalDateTime.now().plusDays(-1),
		// KLineGranularityEnumer.ONE_MINUTE.getCode());
		// 2019-08-08 10:55:00
		LocalDateTime yesterdayTime = LocalDateTime.of( 2019, 8, 8, 10, 55, 00 );
		QueryKLineVO yesterdayVo = klineDAO.queryKLineByCondition( symbol, KLineGranularityEnumer.ONE_MINUTE.getCode(), yesterdayTime );
		if (yesterdayVo != null) {
			// 保留两位小数
			BigDecimal chg = (price.subtract( yesterdayVo.getClose() )).divide( price, 2, BigDecimal.ROUND_HALF_UP );
			vo.setChg( chg );
		}
		return vo;
	}

}
