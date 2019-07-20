package com.bittrade.svc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bittrade.entrust.api.service.ITKlineService;
import com.bittrade.pojo.model.TKline;
import com.bittrade.svc.base.BaseTester;

public class KLineTester extends BaseTester {

	@Autowired
	private ITKlineService klineService;

	@Test
	public void test() {
		TKline kline = new TKline();
		System.out.println( "1==" + kline.getId() );
		klineService.add( kline );
		System.out.println( "2==" + kline.getId() );
	}

}
