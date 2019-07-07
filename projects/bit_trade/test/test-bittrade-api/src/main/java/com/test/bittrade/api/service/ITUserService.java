package com.test.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.model.BaseModel;
import com.test.bittrade.api.__default.service.IDefaultTUserService;

/**
 * 
 * @author Administrator
 *
 */
public interface ITUserService
<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>>
extends IDefaultTUserService<Model, DTO, VO, DAO> {
	
}
