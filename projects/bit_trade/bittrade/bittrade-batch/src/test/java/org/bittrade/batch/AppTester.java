package org.bittrade.batch;

import java.util.List;

import org.bittrade.batch.base.BaseTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bittrade.currency.api.service.ITParamConfigService;
import com.bittrade.pojo.dto.TParamConfigDTO;
import com.bittrade.pojo.model.TParamConfig;

public class AppTester extends BaseTester {

	@Autowired
	ITParamConfigService paramConfigService;

	@Test
	public void add() {
		// QueryWrapper<TParamConfig> queryWrapper = new
		// QueryWrapper<TParamConfig>();
		// queryWrapper.eq( "param_key", "usdToCnyRateKey" );
		// TParamConfig paramConfig = paramConfigService.getOne( queryWrapper );
		// System.out.println( paramConfig.getParamValue() );

		TParamConfig paramConfigQuery = new TParamConfig();
		paramConfigQuery.setParamKey( "usdToCnyRateKey" );
		List<TParamConfigDTO> list_data = paramConfigService.get( paramConfigQuery );
		System.out.println( list_data.get( 0 ).getParamValue() );

	}

}