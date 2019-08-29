/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.TUserAuthentication;
import com.bittrade.pojo.dto.TUserAuthenticationDTO;
import com.bittrade.pojo.vo.TUserAuthenticationVO;
import com.bittrade.__default.service.IDefaultTUserAuthenticationService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultTUserAuthenticationServiceImpl extends BaseServiceImpl<TUserAuthentication, TUserAuthenticationDTO, TUserAuthenticationVO, ITUserAuthenticationDAO> implements IDefaultTUserAuthenticationService {
public abstract class DefaultTUserAuthenticationServiceImpl<DAO extends IBaseDAO<TUserAuthentication, TUserAuthenticationDTO, TUserAuthenticationVO>> extends BaseServiceImpl<TUserAuthentication, TUserAuthenticationDTO, TUserAuthenticationVO, DAO> implements IDefaultTUserAuthenticationService {
	
}
