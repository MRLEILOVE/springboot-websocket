package com.wallet.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.pojo.model.TParamConfig;
import com.wallet.biz.api.service.ITParamConfigService;
import com.wallet.biz.dao.ITParamConfigDAO;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TParamConfigServiceImpl extends ServiceImpl<ITParamConfigDAO, TParamConfig> implements ITParamConfigService {
    @Override
    public String getValueByKey(String key) {
        TParamConfig ParamConfig = this.getOne(new QueryWrapper<>(TParamConfig.builder().paramKey(key).build()));
        return ParamConfig.getParamValue();
    }

    @Override
    public String getEnableValueByKey(String key) {
        TParamConfig ParamConfig = this.getOne(new QueryWrapper<>(TParamConfig.builder().paramKey(key).paramStatus(1).build()));
        return ParamConfig.getParamValue();
    }
	
}
