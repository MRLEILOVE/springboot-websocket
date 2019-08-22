package com.bittrade.admin;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bittrade.admin.base.BaseTester;
import com.bittrade.admin.service.currency.ITCurrencyService;
import com.bittrade.pojo.model.TCurrency;

public class CurrencyTester extends BaseTester {

	@Autowired
	ITCurrencyService cs;

	@Test
	public void test() {
		System.out.println( "cs=" + cs );
		List<TCurrency> list_data = cs.gets();
		System.out.println( list_data );
		System.out.println( "list_data=" + list_data );
	}

}
