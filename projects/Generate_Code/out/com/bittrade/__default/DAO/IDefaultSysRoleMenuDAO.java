/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.SysRoleMenuDTO;
import com.bittrade.pojo.vo.SysRoleMenuVO;
import com.bittrade.pojo.model.SysRoleMenu;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultSysRoleMenuDAO extends IBaseDAO<SysRoleMenu, SysRoleMenuDTO, SysRoleMenuVO> {
	
	/**
	 * 
	 * @param sysRoleMenu
	 * @return
	 */
	public int add(SysRoleMenu sysRoleMenu);
	
	/**
	 * 
	 * @param sysRoleMenu
	 * @return
	 */
	public int addWithSelective(SysRoleMenu sysRoleMenu);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysRoleMenu
	 * @return
	 */
	public int removeBy(SysRoleMenu sysRoleMenu);
	
	/**
	 * 
	 * @param sysRoleMenu
	 * @return
	 */
	public int modifyByPK(SysRoleMenu sysRoleMenu);
	
	/**
	 * 
	 * @param sysRoleMenu
	 * @return
	 */
	public int modifyWithSelectiveByPK(SysRoleMenu sysRoleMenu);
	
	/**
	 * 
	 * @param sysRoleMenu
	 * @param condiSysRoleMenu
	 * @return
	 */
	public int modifyBy(@Param(value="sysRoleMenu") SysRoleMenu sysRoleMenu, @Param(value="condiSysRoleMenu") SysRoleMenu condiSysRoleMenu);
	
	/**
	 * 
	 * @param sysRoleMenu
	 * @param condiSysRoleMenu
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="sysRoleMenu") SysRoleMenu sysRoleMenu, @Param(value="condiSysRoleMenu") SysRoleMenu condiSysRoleMenu);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public SysRoleMenu getByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysRoleMenu
	 * @return
	 */
	public SysRoleMenu getBy(SysRoleMenu sysRoleMenu);
	
	/**
	 * 
	 * @return
	 */
	public SysRoleMenu get();
	
	/**
	 * 
	 * @param sysRoleMenu
	 * @return
	 */
	public List<SysRoleMenu> getsBy(SysRoleMenu sysRoleMenu);
	
	/**
	 * 
	 * @return
	 */
	public List<SysRoleMenu> gets();
	
	/**
	 * 
	 * @param sysRoleMenu
	 * @return
	 */
	public int getCntBy(@Param(value="sysRoleMenu") SysRoleMenu sysRoleMenu);
	
	/**
	 * 
	 * @param sysRoleMenu
	 * @param page
	 * @param size
	 * @return
	 */
	public List<SysRoleMenu> getsByPage(@Param(value="sysRoleMenu") SysRoleMenu sysRoleMenu, @Param(value="page") int page, @Param(value="size") int size);
	
}
