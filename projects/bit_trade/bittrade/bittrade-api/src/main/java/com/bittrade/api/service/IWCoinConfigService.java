package com.bittrade.api.service;

import com.bittrade.api.__default.service.IDefaultWCoinConfigService;
import com.bittrade.api.dao.IWCoinConfigDAO;
import com.bittrade.pojo.dto.WCoinConfigDTO;
import com.bittrade.pojo.vo.WCoinConfigVO;
import com.bittrade.pojo.model.WCoinConfig;

/**
 * 
 * @author Administrator
 *
 */
public interface IWCoinConfigService extends IDefaultWCoinConfigService<WCoinConfig, WCoinConfigDTO, WCoinConfigVO, IWCoinConfigDAO> {
	
}
