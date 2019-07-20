package com.bittrade.entrust.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTKlineServiceImpl;
import com.bittrade.common.constant.IQueueConstants;
import com.bittrade.common.enums.KLineGranularityEnumer;
import com.bittrade.entrust.api.service.ITKlineService;
import com.bittrade.entrust.dao.ITKlineDAO;
import com.bittrade.pojo.dto.QueryKLineDto;
import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.pojo.model.TKline;
import com.bittrade.pojo.vo.QueryKLineVO;
import com.bittrade.pojo.vo.TKlineVO;
import com.core.common.constant.ICompareResultConstant;
import com.core.tool.DateTimeUtil;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TKlineServiceImpl extends DefaultTKlineServiceImpl<ITKlineDAO, TKline, TKlineDTO, TKlineVO>
		implements ITKlineService {
	
	private static final Logger LOG = LoggerFactory.getLogger(TKlineServiceImpl.class);

	@Autowired
	private ITKlineDAO klineDAO;
	@Autowired
	private MakeAMatchServiceImpl makeAMatchService;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	private static final ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, TKline>> MAP__KLINE_LAST;
	static {
		KLineGranularityEnumer objArr_enum[] = KLineGranularityEnumer.values();
		if (objArr_enum != null && objArr_enum.length > 0) {
			MAP__KLINE_LAST = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, TKline>>(objArr_enum.length);
			for (int i = 0; i < objArr_enum.length; i++) {
				MAP__KLINE_LAST.put( objArr_enum[i].getCode(), new ConcurrentHashMap<Integer, TKline>() );
			}
		} else {
			MAP__KLINE_LAST = null;
		}
	};
	
	
	/**
	 * <p>
	 *   初始化K线最后的数据。
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
	private void initializeKLineLast() {
	}
	
	@PostConstruct
	private void initialize() {
		initializeKLineLast();
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
	
	private LocalDateTime getDateTimeBegin(LocalDateTime dt, int granularity) {
		LocalDateTime ldt = null;
		
		// 先这样判断吧， 要不然呢？
		if (granularity <= KLineGranularityEnumer.THIRTY_MINUTE.getCode()) {
			ldt = DateTimeUtil.getMinuteBegin( dt, granularity );
		} else if (granularity <= KLineGranularityEnumer.TWELVE_HOUR.getCode()) {
			ldt = DateTimeUtil.getHourBegin( dt, granularity );
		} else {
			ldt = DateTimeUtil.getDayBegin( dt, granularity );
		}
		
		return ldt;
	}
	
	private String kLineToString(TKline kline) {
		// [symbol,time,open,high,low,close,volume]
		// ["BTC-USDT","2019-07-18 17:00:00.00",9783.2,9787.9,9778.3,9782,3.40246089]
		return new StringBuilder("[")
				.append('\"').append(kline.getSymbol()).append("\",")
				.append('\"').append(kline.getTime()).append("\",")
				.append(kline.getOpen()).append(',')
				.append(kline.getHigh()).append(',')
				.append(kline.getLow()).append(',')
				.append(kline.getClose()).append(',')
				.append(kline.getVolume())
				.append(']')
				.toString()
				;
	}
	
	@Override
	public void modifyKLine(TEntrustRecord entrustRecord, BigDecimal dealPrice) {
		KLineGranularityEnumer objArr_enum[] = KLineGranularityEnumer.values();
		for (int i = 0; i < objArr_enum.length; i++) {
			int i_code = objArr_enum[i].getCode();
			
			TKline kLine = getKLine( MAP__KLINE_LAST.get( i_code ), entrustRecord.getCurrencyTradeId() );
			LocalDateTime dt_updateTime = getDateTimeBegin( entrustRecord.getCreateTime(), i_code );
			if (kLine.getTime() == null || kLine.getTime().compareTo(dt_updateTime) == ICompareResultConstant.LESS_THAN) {
				kLine.setSymbol( makeAMatchService.getSymbol( entrustRecord.getCurrencyTradeId() ) );
				kLine.setTime( dt_updateTime );
				kLine.setOpen( dealPrice );
				kLine.setHigh( dealPrice );
				kLine.setLow( dealPrice );
				kLine.setClose( dealPrice );
				kLine.setVolume( entrustRecord.getCount() );
				klineDAO.add( kLine );
				LOG.info( "kLine.getId()=" + kLine.getId() );
			} else /*if (kLine.getTime().compareTo(dt_updateTime) == 0) */{
				if (dealPrice.compareTo(kLine.getHigh()) == ICompareResultConstant.GREATER_THAN) {
					kLine.setHigh( dealPrice );
				}
				if (dealPrice.compareTo(kLine.getLow()) == ICompareResultConstant.LESS_THAN) {
					kLine.setLow( dealPrice );
				}
				kLine.setClose( dealPrice );
				kLine.setVolume( kLine.getVolume().add( entrustRecord.getCount() ) );
				klineDAO.modifyByPK( kLine );
			}
			
			rabbitTemplate.convertAndSend(IQueueConstants.EXCHANGE_TOPIC, IQueueConstants.ROUTE_KEY__KLINE + i_code, kLineToString(kLine));
		}
	}

	/**
	 * k线查询
	 */
	@Override
	public List<QueryKLineVO> queryKLine(QueryKLineDto queryKLineDto) {
		return klineDAO.queryKLine(queryKLineDto);
	}

}
