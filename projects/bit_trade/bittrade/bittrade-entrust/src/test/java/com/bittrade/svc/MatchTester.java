package com.bittrade.svc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bittrade.currency.api.service.ITCurrencyTradeService;
import com.bittrade.entrust.machine.Robot;
import com.bittrade.entrust.service.impl.MakeAMatchServiceImpl;
import com.bittrade.svc.base.BaseTester;

public class MatchTester extends BaseTester {

	@Autowired
	private Robot robot;
	@Reference
	private ITCurrencyTradeService currencyTradeService;
	@Autowired
	MakeAMatchServiceImpl ms;
	
	@Test
	public void test() {
//		makeAMatchService.test();
		robot.test();
//		TCurrencyTrade currencyTrade = currencyTradeService.getByPK( 1 );
//		System.out.println( "currencyTrade=" + currencyTrade );
		
//		QueryWrapper<TEntrust> qw = new QueryWrapper<TEntrust>();
//		qw.eq("ab", "bc");
//		while (true) {
//			try {
//				String str_ret = es.testPrm(qw);
//				System.out.println("str_ret=" + str_ret);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			try {
//				System.out.println("input>>");
//				System.out.println("System.in.read()=" + System.in.read());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	@Test
	public void testMQ() {
		ms.testMQ();
	}

}
