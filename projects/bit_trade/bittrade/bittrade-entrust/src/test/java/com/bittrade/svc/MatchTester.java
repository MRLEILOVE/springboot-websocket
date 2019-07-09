package com.bittrade.svc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bittrade.entrust.service.impl.MakeAMatchServiceImpl;
import com.bittrade.svc.base.BaseTester;

public class MatchTester extends BaseTester {

	@Autowired
	private MakeAMatchServiceImpl makeAMatchService;
	
	@Test
	public void test() {
		System.out.println("makeAMatchService ========" + makeAMatchService);
		makeAMatchService.test();
	}

}
