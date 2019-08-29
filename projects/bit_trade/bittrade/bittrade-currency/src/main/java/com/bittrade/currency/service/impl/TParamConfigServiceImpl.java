package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTParamConfigServiceImpl;
import com.bittrade.currency.api.service.ITParamConfigService;
import com.bittrade.currency.dao.ITParamConfigDAO;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TParamConfigServiceImpl extends DefaultTParamConfigServiceImpl<ITParamConfigDAO> implements ITParamConfigService {
	
}
