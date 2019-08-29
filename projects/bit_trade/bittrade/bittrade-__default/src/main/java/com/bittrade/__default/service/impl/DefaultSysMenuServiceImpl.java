/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.SysMenu;
import com.bittrade.pojo.dto.SysMenuDTO;
import com.bittrade.pojo.vo.SysMenuVO;
import com.bittrade.__default.service.IDefaultSysMenuService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultSysMenuServiceImpl extends BaseServiceImpl<SysMenu, SysMenuDTO, SysMenuVO, ISysMenuDAO> implements IDefaultSysMenuService {
public abstract class DefaultSysMenuServiceImpl<DAO extends IBaseDAO<SysMenu, SysMenuDTO, SysMenuVO>> extends BaseServiceImpl<SysMenu, SysMenuDTO, SysMenuVO, DAO> implements IDefaultSysMenuService {
	
}
