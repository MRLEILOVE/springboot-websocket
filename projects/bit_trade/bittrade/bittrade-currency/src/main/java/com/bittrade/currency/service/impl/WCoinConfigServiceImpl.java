package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.service.impl.DefaultWCoinConfigServiceImpl;
import com.bittrade.currency.dao.IWCoinConfigDAO;
import com.bittrade.pojo.dto.WCoinConfigDTO;
import com.bittrade.pojo.vo.WCoinConfigVO;
import com.bittrade.pojo.model.WCoinConfig;
import com.bittrade.api.service.IWCoinConfigService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WCoinConfigServiceImpl extends DefaultWCoinConfigServiceImpl<IWCoinConfigDAO, WCoinConfig, WCoinConfigDTO, WCoinConfigVO> implements IWCoinConfigService<IWCoinConfigDAO> {
	
}
