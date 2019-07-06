package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.DAO.IDefaultWCoinConfigDAO;
import com.bittrade.api.__default.service.impl.DefaultWCoinConfigServiceImpl;
import com.bittrade.api.service.IWCoinConfigService;
import com.bittrade.pojo.dto.WCoinConfigDTO;
import com.bittrade.pojo.model.WCoinConfig;
import com.bittrade.pojo.vo.WCoinConfigVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WCoinConfigServiceImpl extends DefaultWCoinConfigServiceImpl<IDefaultWCoinConfigDAO, WCoinConfig, WCoinConfigDTO, WCoinConfigVO> implements IWCoinConfigService {
	
}
