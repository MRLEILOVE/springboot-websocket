package com.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.model.BaseModel;

import com.bittrade.api.__default.service.IDefaultTParamConfigService;

/**
 * 
 * @author Administrator
 *
 */
public interface ITParamConfigService<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>> extends IDefaultTParamConfigService<Model, DTO, VO, DAO> {
	
}
