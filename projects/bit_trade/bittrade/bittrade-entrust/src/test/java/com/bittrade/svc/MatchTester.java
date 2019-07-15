package com.bittrade.svc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bittrade.entrust.api.service.ITEntrustService;
import com.bittrade.entrust.service.impl.MakeAMatchServiceImpl;
import com.bittrade.svc.base.BaseTester;

public class MatchTester extends BaseTester {

	@Autowired
	private MakeAMatchServiceImpl makeAMatchService;
//	@Reference
//	ITEntrustService es;
	
	@Test
	public void test() {
		System.out.println("makeAMatchService ========" + makeAMatchService);
		makeAMatchService.test();
		
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
	
	@Autowired
	ITEntrustService entrustService;

}
