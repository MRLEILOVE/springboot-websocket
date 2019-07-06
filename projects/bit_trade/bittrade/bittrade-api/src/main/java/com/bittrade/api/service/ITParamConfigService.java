package com.bittrade.api.service;

import com.bittrade.api.__default.service.IDefaultTParamConfigService;
import com.bittrade.api.dao.ITParamConfigDAO;
import com.bittrade.pojo.dto.TParamConfigDTO;
import com.bittrade.pojo.vo.TParamConfigVO;
import com.bittrade.pojo.model.TParamConfig;

/**
 * 
 * @author Administrator
 *
 */
public interface ITParamConfigService extends IDefaultTParamConfigService<TParamConfig, TParamConfigDTO, TParamConfigVO, ITParamConfigDAO> {
	
}
