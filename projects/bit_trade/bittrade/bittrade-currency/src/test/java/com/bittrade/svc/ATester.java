package com.bittrade.svc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.currency.api.service.ITParamConfigService;
import com.bittrade.pojo.model.TParamConfig;
import com.bittrade.svc.base.BaseTester;

public class ATester extends BaseTester {

	@Autowired
	ITParamConfigService paramConfigService;
	
	@Test
	public void test() {
		QueryWrapper<TParamConfig> queryWrapper = new QueryWrapper<TParamConfig>();
		queryWrapper.eq( TParamConfig.FieldNames.PARAM_KEY, "okexKlineUrlKey" );
		queryWrapper.eq( TParamConfig.FieldNames.PARAM_STATUS, 1 );
		TParamConfig paramConfig = paramConfigService.getOne( queryWrapper );
		System.out.println( "paramConfig=" + paramConfig );
	}

}
