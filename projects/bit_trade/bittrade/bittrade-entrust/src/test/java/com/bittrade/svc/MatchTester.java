package com.bittrade.svc;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.currency.api.service.ITEntrustService;
import com.bittrade.entrust.service.impl.MakeAMatchServiceImpl;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.svc.base.BaseTester;

public class MatchTester extends BaseTester {

//	@Autowired
//	private MakeAMatchServiceImpl makeAMatchService;
	@Reference
	ITEntrustService es;
	
	@Test
	public void test() {
//		System.out.println("makeAMatchService ========" + makeAMatchService);
//		makeAMatchService.test();
		
		QueryWrapper<TEntrust> qw = new QueryWrapper<TEntrust>();
		qw.eq("ab", "bc");
		while (true) {
			try {
				String str_ret = es.testPrm(qw);
				System.out.println("str_ret=" + str_ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				System.out.println("input>>");
				System.out.println("System.in.read()=" + System.in.read());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
