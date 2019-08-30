package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTTickerServiceImpl;
import com.bittrade.currency.api.service.ITTickerService;
import com.bittrade.currency.dao.ITTickerDAO;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TTickerServiceImpl extends DefaultTTickerServiceImpl<ITTickerDAO> implements ITTickerService {
	
}
