/**
 * This code is generated automatically. Please do not edit it.
 */
package com.test.bittrade.api.__default.service.impl;

import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.model.BaseModel;
import com.core.framework.base.service.impl.BaseServiceImpl;
import com.test.bittrade.api.__default.service.IDefaultTUserService;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultTUserServiceImpl extends BaseServiceImpl<TUser, TUserDTO, TUserVO, ITUserDAO> implements IDefaultTUserService {
public abstract class DefaultTUserServiceImpl<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>> extends BaseServiceImpl<Model, DTO, VO, DAO> implements IDefaultTUserService<Model, DTO, VO> {
	
}
