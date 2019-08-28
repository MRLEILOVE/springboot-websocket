/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.SysRoleDeptDTO;
import com.bittrade.pojo.vo.SysRoleDeptVO;
import com.bittrade.pojo.model.SysRoleDept;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultSysRoleDeptDAO extends IBaseDAO<SysRoleDept, SysRoleDeptDTO, SysRoleDeptVO> {
	
	/**
	 * 
	 * @param sysRoleDept
	 * @return
	 */
	public int add(SysRoleDept sysRoleDept);
	
	/**
	 * 
	 * @param sysRoleDept
	 * @return
	 */
	public int addWithSelective(SysRoleDept sysRoleDept);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysRoleDept
	 * @return
	 */
	public int removeBy(SysRoleDept sysRoleDept);
	
	/**
	 * 
	 * @param sysRoleDept
	 * @return
	 */
	public int modifyByPK(SysRoleDept sysRoleDept);
	
	/**
	 * 
	 * @param sysRoleDept
	 * @return
	 */
	public int modifyWithSelectiveByPK(SysRoleDept sysRoleDept);
	
	/**
	 * 
	 * @param sysRoleDept
	 * @param condiSysRoleDept
	 * @return
	 */
	public int modifyBy(@Param(value="sysRoleDept") SysRoleDept sysRoleDept, @Param(value="condiSysRoleDept") SysRoleDept condiSysRoleDept);
	
	/**
	 * 
	 * @param sysRoleDept
	 * @param condiSysRoleDept
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="sysRoleDept") SysRoleDept sysRoleDept, @Param(value="condiSysRoleDept") SysRoleDept condiSysRoleDept);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public SysRoleDept getByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysRoleDept
	 * @return
	 */
	public SysRoleDept getBy(SysRoleDept sysRoleDept);
	
	/**
	 * 
	 * @return
	 */
	public SysRoleDept get();
	
	/**
	 * 
	 * @param sysRoleDept
	 * @return
	 */
	public List<SysRoleDept> getsBy(SysRoleDept sysRoleDept);
	
	/**
	 * 
	 * @return
	 */
	public List<SysRoleDept> gets();
	
	/**
	 * 
	 * @param sysRoleDept
	 * @return
	 */
	public int getCntBy(@Param(value="sysRoleDept") SysRoleDept sysRoleDept);
	
	/**
	 * 
	 * @param sysRoleDept
	 * @param page
	 * @param size
	 * @return
	 */
	public List<SysRoleDept> getsByPage(@Param(value="sysRoleDept") SysRoleDept sysRoleDept, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param sysRoleDeptDTO
	 * @return
	 */
	public SysRoleDeptDTO getDTOBy(SysRoleDeptDTO sysRoleDeptDTO);
	
	/**
	 * 
	 * @param sysRoleDeptDTO
	 * @return
	 */
	public List<SysRoleDeptDTO> getsDTOBy(SysRoleDeptDTO sysRoleDeptDTO);
	
	/**
	 * 
	 * @param sysRoleDeptDTO
	 * @param pageDTO
	 * @return
	 */
	public List<SysRoleDeptDTO> getsDTOBy(@Param(value="sysRoleDept") SysRoleDeptDTO sysRoleDeptDTO, PageDTO<SysRoleDeptDTO> pageDTO);
	
}
