package com.bittrade.batch.general;

import java.math.BigDecimal;
import java.util.Date;

import com.bittrade.batch.enumer.ParamConfigEnum.ParamStatus;
import com.bittrade.currency.api.service.ITParamConfigService;
import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.pojo.model.TParamConfig;
import com.bittrade.pojo.model.TWallet;

public class GeneralMethod {

	public static TParamConfig qryParamConfigInfo(ITParamConfigService paramConfigService, String key) throws Exception {
		TParamConfig paramConfigCondi = new TParamConfig();
		paramConfigCondi.setParamKey( key );
		paramConfigCondi.setParamStatus( ParamStatus.ENABLE.getKey() );
		TParamConfig paramConfig = paramConfigService.getBy( paramConfigCondi );
		if (null == paramConfig) {
			throw new Exception( "key：" + key + "未配置" );
		}
		return paramConfig;
	}

	public static TWallet qryUserWallet(ITWalletService walletService, long userId, int currencyId) throws Exception {
		TWallet qryWallet = new TWallet();
		qryWallet.setUserId( userId );
		qryWallet.setCurrencyId( currencyId );
		TWallet result = walletService.getBy( qryWallet );
		if (null == result) { // 用户币币钱包不存在，则给其生成一个钱包
			TWallet wallet = new TWallet();
			wallet.setUserId( userId );
			wallet.setCurrencyId( currencyId );
			wallet.setTotal( BigDecimal.ZERO );
			wallet.setTradeFrozen( BigDecimal.ZERO );
			wallet.setTransferFrozen( BigDecimal.ZERO );
			wallet.setCreateTime( new Date() );
			wallet.setVersion( 0 );// 版本号默认从0开始
			int row = walletService.add( wallet );
			if (row > 0) {
				return wallet;
			} else {
				throw new Exception( "用户Id=" + userId + ",currencyId = " + currencyId + "生成用户钱包异常" );
			}
		}
		return result;
	}

}
