/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.SysRole;
import com.bittrade.pojo.dto.SysRoleDTO;
import com.bittrade.pojo.vo.SysRoleVO;
import com.bittrade.__default.service.IDefaultSysRoleService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultSysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleDTO, SysRoleVO, ISysRoleDAO> implements IDefaultSysRoleService {
public abstract class DefaultSysRoleServiceImpl<DAO extends IBaseDAO<SysRole, SysRoleDTO, SysRoleVO>> extends BaseServiceImpl<SysRole, SysRoleDTO, SysRoleVO, DAO> implements IDefaultSysRoleService {
	
}
