/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.SysUserRoleDTO;
import com.bittrade.pojo.vo.SysUserRoleVO;
import com.bittrade.pojo.model.SysUserRole;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultSysUserRoleDAO extends IBaseDAO<SysUserRole, SysUserRoleDTO, SysUserRoleVO> {
	
	/**
	 * 
	 * @param sysUserRole
	 * @return
	 */
	public int add(SysUserRole sysUserRole);
	
	/**
	 * 
	 * @param sysUserRole
	 * @return
	 */
	public int addWithSelective(SysUserRole sysUserRole);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysUserRole
	 * @return
	 */
	public int removeBy(SysUserRole sysUserRole);
	
	/**
	 * 
	 * @param sysUserRole
	 * @return
	 */
	public int modifyByPK(SysUserRole sysUserRole);
	
	/**
	 * 
	 * @param sysUserRole
	 * @return
	 */
	public int modifyWithSelectiveByPK(SysUserRole sysUserRole);
	
	/**
	 * 
	 * @param sysUserRole
	 * @param condiSysUserRole
	 * @return
	 */
	public int modifyBy(@Param(value="sysUserRole") SysUserRole sysUserRole, @Param(value="condiSysUserRole") SysUserRole condiSysUserRole);
	
	/**
	 * 
	 * @param sysUserRole
	 * @param condiSysUserRole
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="sysUserRole") SysUserRole sysUserRole, @Param(value="condiSysUserRole") SysUserRole condiSysUserRole);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public SysUserRole getByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysUserRole
	 * @return
	 */
	public SysUserRole getBy(SysUserRole sysUserRole);
	
	/**
	 * 
	 * @return
	 */
	public SysUserRole get();
	
	/**
	 * 
	 * @param sysUserRole
	 * @return
	 */
	public List<SysUserRole> getsBy(SysUserRole sysUserRole);
	
	/**
	 * 
	 * @return
	 */
	public List<SysUserRole> gets();
	
	/**
	 * 
	 * @param sysUserRole
	 * @return
	 */
	public int getCntBy(@Param(value="sysUserRole") SysUserRole sysUserRole);
	
	/**
	 * 
	 * @param sysUserRole
	 * @param page
	 * @param size
	 * @return
	 */
	public List<SysUserRole> getsByPage(@Param(value="sysUserRole") SysUserRole sysUserRole, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param sysUserRoleDTO
	 * @return
	 */
	public SysUserRoleDTO getDTOBy(SysUserRoleDTO sysUserRoleDTO);
	
	/**
	 * 
	 * @param sysUserRoleDTO
	 * @return
	 */
	public List<SysUserRoleDTO> getsDTOBy(SysUserRoleDTO sysUserRoleDTO);
	
	/**
	 * 
	 * @param sysUserRoleDTO
	 * @param pageDTO
	 * @return
	 */
	public List<SysUserRoleDTO> getsDTOBy(SysUserRoleDTO sysUserRoleDTO, PageDTO<SysUserRoleDTO> pageDTO);
	
}
