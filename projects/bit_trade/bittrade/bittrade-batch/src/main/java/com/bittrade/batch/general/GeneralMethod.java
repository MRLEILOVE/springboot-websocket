package com.bittrade.batch.general;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.batch.enumer.ParamConfigEnum.ParamStatus;
import com.bittrade.currency.api.service.ITParamConfigService;
import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.pojo.model.TParamConfig;
import com.bittrade.pojo.model.TWallet;

public class GeneralMethod {

	public static TParamConfig qryParamConfigInfo(ITParamConfigService paramConfigService, String key) throws Exception {
		QueryWrapper<TParamConfig> queryWrapper = new QueryWrapper<TParamConfig>();
		queryWrapper.eq( TParamConfig.FieldNames.PARAM_KEY, key );
		queryWrapper.eq( TParamConfig.FieldNames.PARAM_STATUS, ParamStatus.ENABLE.getKey() );
		TParamConfig paramConfig = paramConfigService.getOne( queryWrapper );
		if (null == paramConfig) {
			throw new Exception( "key：" + key + "未配置" );
		}
		return paramConfig;
	}

	public static TWallet qryUserWallet(ITWalletService walletService, long userId, int currencyId) throws Exception {
		QueryWrapper<TWallet> queryWrapper = new QueryWrapper<TWallet>();
		queryWrapper.eq( TWallet.FieldNames.USER_ID, userId );
		queryWrapper.eq( TWallet.FieldNames.CURRENCY_ID, currencyId );
		TWallet result = walletService.getOne( queryWrapper );
		if (null == result) { // 用户币币钱包不存在，则给其生成一个钱包
			TWallet wallet = new TWallet();
			wallet.setUserId( userId );
			wallet.setCurrencyId( currencyId );
			wallet.setTotal( BigDecimal.ZERO );
			wallet.setTradeFrozen( BigDecimal.ZERO );
			wallet.setTransferFrozen( BigDecimal.ZERO );
			wallet.setCreateTime( new Date() );
			boolean bool = walletService.save( wallet );
			if (bool) {
				return wallet;
			} else {
				throw new Exception( "用户Id=" + userId + ",currencyId = " + currencyId + "生成用户钱包异常" );
			}
		}
		return result;
	}

}
