/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.SysMenuDTO;
import com.bittrade.pojo.vo.SysMenuVO;
import com.bittrade.pojo.model.SysMenu;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultSysMenuDAO extends IBaseDAO<SysMenu, SysMenuDTO, SysMenuVO> {
	
	/**
	 * 
	 * @param sysMenu
	 * @return
	 */
	public int add(SysMenu sysMenu);
	
	/**
	 * 
	 * @param sysMenu
	 * @return
	 */
	public int addWithSelective(SysMenu sysMenu);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysMenu
	 * @return
	 */
	public int removeBy(SysMenu sysMenu);
	
	/**
	 * 
	 * @param sysMenu
	 * @return
	 */
	public int modifyByPK(SysMenu sysMenu);
	
	/**
	 * 
	 * @param sysMenu
	 * @return
	 */
	public int modifyWithSelectiveByPK(SysMenu sysMenu);
	
	/**
	 * 
	 * @param sysMenu
	 * @param condiSysMenu
	 * @return
	 */
	public int modifyBy(@Param(value="sysMenu") SysMenu sysMenu, @Param(value="condiSysMenu") SysMenu condiSysMenu);
	
	/**
	 * 
	 * @param sysMenu
	 * @param condiSysMenu
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="sysMenu") SysMenu sysMenu, @Param(value="condiSysMenu") SysMenu condiSysMenu);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public SysMenu getByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysMenu
	 * @return
	 */
	public SysMenu getBy(SysMenu sysMenu);
	
	/**
	 * 
	 * @return
	 */
	public SysMenu get();
	
	/**
	 * 
	 * @param sysMenu
	 * @return
	 */
	public List<SysMenu> getsBy(SysMenu sysMenu);
	
	/**
	 * 
	 * @return
	 */
	public List<SysMenu> gets();
	
	/**
	 * 
	 * @param sysMenu
	 * @return
	 */
	public int getCntBy(@Param(value="sysMenu") SysMenu sysMenu);
	
	/**
	 * 
	 * @param sysMenu
	 * @param page
	 * @param size
	 * @return
	 */
	public List<SysMenu> getsByPage(@Param(value="sysMenu") SysMenu sysMenu, @Param(value="page") int page, @Param(value="size") int size);
	
}
