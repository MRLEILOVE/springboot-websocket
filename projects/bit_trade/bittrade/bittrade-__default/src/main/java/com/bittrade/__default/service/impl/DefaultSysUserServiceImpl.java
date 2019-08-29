/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.SysUser;
import com.bittrade.pojo.dto.SysUserDTO;
import com.bittrade.pojo.vo.SysUserVO;
import com.bittrade.__default.service.IDefaultSysUserService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultSysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserDTO, SysUserVO, ISysUserDAO> implements IDefaultSysUserService {
public abstract class DefaultSysUserServiceImpl<DAO extends IBaseDAO<SysUser, SysUserDTO, SysUserVO>> extends BaseServiceImpl<SysUser, SysUserDTO, SysUserVO, DAO> implements IDefaultSysUserService {
	
}
