/**
 * This code is generated automatically. Please do not edit it.
 */
package com.test.__default.service.impl;

import com.test.__default.service.IDefaultTUserZService;
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
//public abstract class DefaultTUserZServiceImpl extends BaseServiceImpl<TUserZ, TUserZDTO, TUserZVO, ITUserZDAO> implements IDefaultTUserZService {
public abstract class DefaultTUserZServiceImpl<DAO extends IBaseDAO<Model, DTO, VO>, Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>> extends BaseServiceImpl<Model, DTO, VO, DAO> implements IDefaultTUserZService<Model, DTO, VO> {
	
}