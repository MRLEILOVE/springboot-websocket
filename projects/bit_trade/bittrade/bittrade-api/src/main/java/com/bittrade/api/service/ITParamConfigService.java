package com.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;

import com.bittrade.api.__default.service.IDefaultTParamConfigService;
import com.bittrade.pojo.dto.TParamConfigDTO;
import com.bittrade.pojo.vo.TParamConfigVO;
import com.bittrade.pojo.model.TParamConfig;

/**
 * 
 * @author Administrator
 *
 */
public interface ITParamConfigService<DAO extends IBaseDAO<TParamConfig, TParamConfigDTO, TParamConfigVO>> extends IDefaultTParamConfigService<TParamConfig, TParamConfigDTO, TParamConfigVO, DAO> {
	
}
