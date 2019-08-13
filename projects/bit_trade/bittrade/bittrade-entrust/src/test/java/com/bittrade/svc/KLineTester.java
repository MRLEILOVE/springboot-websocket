package com.bittrade.svc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bittrade.common.enums.KLineGranularityEnumer;
import com.bittrade.entrust.api.service.ITKlineService;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.pojo.model.TKline;
import com.bittrade.svc.base.BaseTester;
import com.core.tool.DateTimeUtil;

public class KLineTester extends BaseTester {

	@Autowired
	private ITKlineService klineService;

	@Test
	public void test() {
		TKline kline = new TKline();
		
//		kline.setSymbol( "ETH-USDT" );
//		kline.setLow( new BigDecimal("210.5562786920") );
//		kline.setHigh( new BigDecimal("210.5562786920") );
//		kline.setOpen( new BigDecimal("210.5562786920") );
//		kline.setClose( new BigDecimal("210.5562786920") );
//		kline.setVolume( new BigDecimal("2.33002813") );
//		kline.setGranularity( 60 );
//		kline.setTime( LocalDateTime.now() );
//		kline.setCreateTime( LocalDateTime.now() );
//		kline.setUpdateTime( LocalDateTime.now() );
		
		System.out.println( "1==" + kline.getId() );
		klineService.add( kline );
		System.out.println( "2==" + kline.getId() );
	}

	@Test
	public void modifyKLine() {
		@SuppressWarnings("serial")
		TEntrustRecord er = new TEntrustRecord() {
			{
				setCurrencyTradeId(1);
				setCount(new BigDecimal("12.3"));
				setCreateTime(LocalDateTime.now());
			}
		};
		BigDecimal dp = new BigDecimal("123.4");
		klineService.modifyKLine( er, dp );
	}
	
	@Test
	public void gets() {
		TKline tKlineQuery = new TKline();
		tKlineQuery.field( "id", "high", "low", "close", "volume" );
		LocalDateTime dt_now = LocalDateTime.now();
		int i_code = KLineGranularityEnumer.ONE_MINUTE.getCode();
		LocalDateTime dt_granularity = DateTimeUtil.getMinuteBegin( dt_now, i_code / 60 );
		tKlineQuery.setSymbol( "ETH-USDT" );
		tKlineQuery.setGranularity(i_code);
		tKlineQuery.setTime( dt_granularity );
		List<TKline> list_ = klineService.getsBy( tKlineQuery );
		System.out.println( list_ );
	}

}
