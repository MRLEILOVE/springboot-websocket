/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.__default.service.IDefaultTUserAuthenticationService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.model.BaseModel;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultTUserAuthenticationServiceImpl extends BaseServiceImpl<TUserAuthentication, TUserAuthenticationDTO, TUserAuthenticationVO, ITUserAuthenticationDAO> implements IDefaultTUserAuthenticationService {
public abstract class DefaultTUserAuthenticationServiceImpl<DAO extends IBaseDAO<Model, DTO, VO>, Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>> extends BaseServiceImpl<Model, DTO, VO, DAO> implements IDefaultTUserAuthenticationService<Model, DTO, VO> {
	
}
