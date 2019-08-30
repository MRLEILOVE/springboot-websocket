/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.TUser;
import com.bittrade.pojo.dto.TUserDTO;
import com.bittrade.pojo.vo.TUserVO;
import com.bittrade.__default.service.IDefaultTUserService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultTUserServiceImpl extends BaseServiceImpl<TUser, TUserDTO, TUserVO, ITUserDAO> implements IDefaultTUserService {
public abstract class DefaultTUserServiceImpl<DAO extends IBaseDAO<TUser, TUserDTO, TUserVO>> extends BaseServiceImpl<TUser, TUserDTO, TUserVO, DAO> implements IDefaultTUserService {
	
}
