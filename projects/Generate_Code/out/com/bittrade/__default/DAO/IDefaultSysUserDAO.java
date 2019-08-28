/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.SysUserDTO;
import com.bittrade.pojo.vo.SysUserVO;
import com.bittrade.pojo.model.SysUser;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultSysUserDAO extends IBaseDAO<SysUser, SysUserDTO, SysUserVO> {
	
	/**
	 * 
	 * @param sysUser
	 * @return
	 */
	public int add(SysUser sysUser);
	
	/**
	 * 
	 * @param sysUser
	 * @return
	 */
	public int addWithSelective(SysUser sysUser);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysUser
	 * @return
	 */
	public int removeBy(SysUser sysUser);
	
	/**
	 * 
	 * @param sysUser
	 * @return
	 */
	public int modifyByPK(SysUser sysUser);
	
	/**
	 * 
	 * @param sysUser
	 * @return
	 */
	public int modifyWithSelectiveByPK(SysUser sysUser);
	
	/**
	 * 
	 * @param sysUser
	 * @param condiSysUser
	 * @return
	 */
	public int modifyBy(@Param(value="sysUser") SysUser sysUser, @Param(value="condiSysUser") SysUser condiSysUser);
	
	/**
	 * 
	 * @param sysUser
	 * @param condiSysUser
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="sysUser") SysUser sysUser, @Param(value="condiSysUser") SysUser condiSysUser);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public SysUser getByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysUser
	 * @return
	 */
	public SysUser getBy(SysUser sysUser);
	
	/**
	 * 
	 * @return
	 */
	public SysUser get();
	
	/**
	 * 
	 * @param sysUser
	 * @return
	 */
	public List<SysUser> getsBy(SysUser sysUser);
	
	/**
	 * 
	 * @return
	 */
	public List<SysUser> gets();
	
	/**
	 * 
	 * @param sysUser
	 * @return
	 */
	public int getCntBy(@Param(value="sysUser") SysUser sysUser);
	
	/**
	 * 
	 * @param sysUser
	 * @param page
	 * @param size
	 * @return
	 */
	public List<SysUser> getsByPage(@Param(value="sysUser") SysUser sysUser, @Param(value="page") int page, @Param(value="size") int size);
	
}
