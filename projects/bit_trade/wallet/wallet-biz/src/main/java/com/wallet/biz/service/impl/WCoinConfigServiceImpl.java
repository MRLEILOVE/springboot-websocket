package com.wallet.biz.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.pojo.model.WCoinConfig;
import com.wallet.biz.api.service.IWCoinConfigService;
import com.wallet.biz.dao.IWCoinConfigDAO;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Component
public class WCoinConfigServiceImpl extends ServiceImpl<IWCoinConfigDAO, WCoinConfig> implements IWCoinConfigService {
	
}
