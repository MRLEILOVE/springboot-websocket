package com.bittrade.entrust.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bittrade.__default.service.impl.DefaultTKlineServiceImpl;
import com.bittrade.common.constant.IQueueConstants;
import com.bittrade.common.enums.KLineGranularityEnumer;
import com.bittrade.currency.api.service.ITCurrencyTradeService;
import com.bittrade.entrust.api.service.ITKlineService;
import com.bittrade.entrust.dao.ITKlineDAO;
import com.bittrade.entrust.dto.KLineDTO;
import com.bittrade.pojo.dto.QueryKLineDto;
import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.model.TEntrust;
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

	@Autowired
	private ITKlineDAO klineDAO;
	@Reference
	private ITCurrencyTradeService currencyTradeService;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	private static final ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, KLineDTO>> MAP__KLINE_LAST;
	private static final ConcurrentHashMap<Integer, String> MAP__SYMBOL = new ConcurrentHashMap<>();
	static {
		KLineGranularityEnumer objArr_enum[] = KLineGranularityEnumer.values();
		if (objArr_enum != null && objArr_enum.length > 0) {
			MAP__KLINE_LAST = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, KLineDTO>>(objArr_enum.length);
			for (int i = 0; i < objArr_enum.length; i++) {
				MAP__KLINE_LAST.put( objArr_enum[i].getCode(), new ConcurrentHashMap<Integer, KLineDTO>() );
			}
		} else {
			MAP__KLINE_LAST = null;
		}
	};
	
	private KLineDTO getKLineDTO(Map<Integer, KLineDTO> map, int key) {
		KLineDTO kLineDTO;
		
		if (map.containsKey( key )) {
			kLineDTO = map.get( key );
		} else {
			map.put( key, kLineDTO = new KLineDTO() );
		}
		
		return kLineDTO;
	}
	
	private String getSymbol(int key) {
		String symbol;
		
		Map<Integer, String> map = MAP__SYMBOL;
		if (map.containsKey( key )) {
			symbol = map.get( key );
		} else {
			map.put( key, symbol = currencyTradeService.getByPK( key ).getSymbol() );
		}
		
		return symbol;
	}
	
	@Override
	public void modifyKLine(TEntrust entrust, BigDecimal dealPrice) {
		KLineGranularityEnumer objArr_enum[] = KLineGranularityEnumer.values();
		for (int i = 0; i < objArr_enum.length; i++) {
			int i_code = objArr_enum[i].getCode();
			
			KLineDTO kLineDTO = getKLineDTO( MAP__KLINE_LAST.get( i_code ), entrust.getCurrencyTradeId() );
			LocalDateTime dt_updateTime = DateTimeUtil.getMinuteBegin( entrust.getUpdateTime() );
			if (kLineDTO.getTime() == null || kLineDTO.getTime().compareTo(dt_updateTime) == -1) {
				kLineDTO.setSymbol( getSymbol( entrust.getCurrencyTradeId() ) );
				kLineDTO.setTime( dt_updateTime );
				kLineDTO.setOpen( dealPrice );
				kLineDTO.setHigh( dealPrice );
				kLineDTO.setLow( dealPrice );
				kLineDTO.setClose( dealPrice );
				kLineDTO.setVolume( 1 );
			} else /*if (kLineDTO.getTime().compareTo(dt_updateTime) == 0) */{
				if (dealPrice.compareTo(kLineDTO.getHigh()) == ICompareResultConstant.GREATER_THAN) {
					kLineDTO.setHigh( dealPrice );
				}
				if (dealPrice.compareTo(kLineDTO.getLow()) == ICompareResultConstant.LESS_THAN) {
					kLineDTO.setLow( dealPrice );
				}
				kLineDTO.setClose( dealPrice );
				kLineDTO.setVolume( kLineDTO.getVolume() + 1 );
			}
			
			rabbitTemplate.convertAndSend(IQueueConstants.EXCHANGE_TOPIC, IQueueConstants.ROUTE_KEY__KLINE, kLineDTO.toString());
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
