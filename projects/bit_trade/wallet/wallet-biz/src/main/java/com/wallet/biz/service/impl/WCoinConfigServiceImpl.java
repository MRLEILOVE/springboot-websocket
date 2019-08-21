package com.wallet.biz.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.biz.api.service.IWCoinConfigService;
import com.wallet.biz.dao.IWCoinConfigDAO;
import com.wallet.biz.pojo.model.WCoinConfig;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WCoinConfigServiceImpl extends ServiceImpl<IWCoinConfigDAO, WCoinConfig> implements IWCoinConfigService {
	
}
