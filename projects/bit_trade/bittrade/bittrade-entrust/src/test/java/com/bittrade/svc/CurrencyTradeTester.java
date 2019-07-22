package com.bittrade.svc;

import org.junit.Test;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bittrade.currency.api.service.ITCurrencyTradeService;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.svc.base.BaseTester;

public class CurrencyTradeTester extends BaseTester {

	@Reference
	ITCurrencyTradeService currencyTradeService;

	@Test
	public void get() {
		TCurrencyTrade currencyTrade = currencyTradeService.getByPK(2);
		System.out.println( "currencyTrade=" + currencyTrade );
	}

}
