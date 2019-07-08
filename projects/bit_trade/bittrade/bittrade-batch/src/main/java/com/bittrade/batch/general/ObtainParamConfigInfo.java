package com.bittrade.batch.general;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.api.service.ITParamConfigService;
import com.bittrade.batch.dao.ITParamConfigDAO;
import com.bittrade.batch.enumer.ParamConfigEnum.ParamStatus;
import com.bittrade.pojo.model.TParamConfig;

public class ObtainParamConfigInfo {

	public static TParamConfig obtainRate(ITParamConfigService<ITParamConfigDAO> paramConfigService, String key) throws Exception {
		QueryWrapper<TParamConfig> queryWrapper = new QueryWrapper<TParamConfig>();
		queryWrapper.eq( TParamConfig.FieldNames.PARAM_KEY, key );
		queryWrapper.eq( TParamConfig.FieldNames.PARAM_STATUS, ParamStatus.ENABLE.getKey() );
		TParamConfig paramConfig = paramConfigService.getOne( queryWrapper );
		if (null == paramConfig) {
			throw new Exception( "key：" + key + "未配置" );
		}
		return paramConfig;
	}

}
