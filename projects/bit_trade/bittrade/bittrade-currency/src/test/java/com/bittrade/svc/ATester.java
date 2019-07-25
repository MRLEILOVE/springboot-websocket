package com.bittrade.svc;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bittrade.currency.api.service.ITCurrencyTradeService;
import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.svc.base.BaseTester;

public class ATester extends BaseTester {

	@Autowired
	private ITCurrencyTradeService currencyTradeService;
//	@Autowired
//	ITParamConfigService paramConfigService;
	@Autowired
	private ITWalletService walletService;
	
	@Test
	public void getCT() {
		TCurrencyTrade currencyTrade = currencyTradeService.getByPK( 2 );
		System.out.println( "currencyTrade=" + currencyTrade );
	}
	
	@Test
	public void test() {
//		QueryWrapper<TParamConfig> queryWrapper = new QueryWrapper<TParamConfig>();
//		queryWrapper.eq( TParamConfig.FieldNames.PARAM_KEY, "okexKlineUrlKey" );
//		queryWrapper.eq( TParamConfig.FieldNames.PARAM_STATUS, 1 );
//		TParamConfig paramConfig = paramConfigService.getOne( queryWrapper );
//		System.out.println( "paramConfig=" + paramConfig );
		
		TWallet updateWallet = new TWallet();// 修改的对象
		updateWallet.setVersion( 10 );
		updateWallet.setTradeFrozen(new BigDecimal("0.00000000"));
//		updateWallet.setTradeFrozen(null);
//		updateWallet.setUpdateTime(new java.util.Date());

		TWallet updateSellMarketIdWallet = new TWallet(); // 条件
		updateSellMarketIdWallet.setId(3L);
//		TWallet updateSellMarketIdWallet = null;

		// 更新钱包
		System.out.println("==" + walletService.modifyBy/* WithSelective */( updateWallet, updateSellMarketIdWallet ));
	}

}
