package com.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;

import com.bittrade.api.__default.service.IDefaultWCoinConfigService;
import com.bittrade.pojo.dto.WCoinConfigDTO;
import com.bittrade.pojo.vo.WCoinConfigVO;
import com.bittrade.pojo.model.WCoinConfig;

/**
 * 
 * @author Administrator
 *
 */
public interface IWCoinConfigService<DAO extends IBaseDAO<WCoinConfig, WCoinConfigDTO, WCoinConfigVO>> extends IDefaultWCoinConfigService<WCoinConfig, WCoinConfigDTO, WCoinConfigVO, DAO> {
	
}
