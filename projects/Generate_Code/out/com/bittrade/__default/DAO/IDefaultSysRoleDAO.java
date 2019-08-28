/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.SysRoleDTO;
import com.bittrade.pojo.vo.SysRoleVO;
import com.bittrade.pojo.model.SysRole;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultSysRoleDAO extends IBaseDAO<SysRole, SysRoleDTO, SysRoleVO> {
	
	/**
	 * 
	 * @param sysRole
	 * @return
	 */
	public int add(SysRole sysRole);
	
	/**
	 * 
	 * @param sysRole
	 * @return
	 */
	public int addWithSelective(SysRole sysRole);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysRole
	 * @return
	 */
	public int removeBy(SysRole sysRole);
	
	/**
	 * 
	 * @param sysRole
	 * @return
	 */
	public int modifyByPK(SysRole sysRole);
	
	/**
	 * 
	 * @param sysRole
	 * @return
	 */
	public int modifyWithSelectiveByPK(SysRole sysRole);
	
	/**
	 * 
	 * @param sysRole
	 * @param condiSysRole
	 * @return
	 */
	public int modifyBy(@Param(value="sysRole") SysRole sysRole, @Param(value="condiSysRole") SysRole condiSysRole);
	
	/**
	 * 
	 * @param sysRole
	 * @param condiSysRole
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="sysRole") SysRole sysRole, @Param(value="condiSysRole") SysRole condiSysRole);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public SysRole getByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysRole
	 * @return
	 */
	public SysRole getBy(SysRole sysRole);
	
	/**
	 * 
	 * @return
	 */
	public SysRole get();
	
	/**
	 * 
	 * @param sysRole
	 * @return
	 */
	public List<SysRole> getsBy(SysRole sysRole);
	
	/**
	 * 
	 * @return
	 */
	public List<SysRole> gets();
	
	/**
	 * 
	 * @param sysRole
	 * @return
	 */
	public int getCntBy(@Param(value="sysRole") SysRole sysRole);
	
	/**
	 * 
	 * @param sysRole
	 * @param page
	 * @param size
	 * @return
	 */
	public List<SysRole> getsByPage(@Param(value="sysRole") SysRole sysRole, @Param(value="page") int page, @Param(value="size") int size);
	
}
