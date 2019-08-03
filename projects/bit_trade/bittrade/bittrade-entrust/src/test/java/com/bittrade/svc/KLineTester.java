package com.bittrade.svc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bittrade.entrust.api.service.ITKlineService;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.pojo.model.TKline;
import com.bittrade.svc.base.BaseTester;

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

}
