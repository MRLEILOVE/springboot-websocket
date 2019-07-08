package com.test.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;
import com.test.bittrade.api.__default.service.IDefaultTUserService;
import com.test.bittrade.pojo.dto.TUserDTO;
import com.test.bittrade.pojo.model.TUser;
import com.test.bittrade.pojo.vo.TUserVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITUserService
//<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>>
<DAO extends IBaseDAO<TUser, TUserDTO, TUserVO>>
extends IDefaultTUserService<TUser, TUserDTO, TUserVO, DAO> {
	
}
