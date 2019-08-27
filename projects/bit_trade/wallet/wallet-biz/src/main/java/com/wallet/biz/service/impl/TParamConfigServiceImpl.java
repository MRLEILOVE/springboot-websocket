package com.wallet.biz.service.impl;

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
	
}
