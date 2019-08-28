/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.SysDeptDTO;
import com.bittrade.pojo.vo.SysDeptVO;
import com.bittrade.pojo.model.SysDept;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultSysDeptDAO extends IBaseDAO<SysDept, SysDeptDTO, SysDeptVO> {
	
	/**
	 * 
	 * @param sysDept
	 * @return
	 */
	public int add(SysDept sysDept);
	
	/**
	 * 
	 * @param sysDept
	 * @return
	 */
	public int addWithSelective(SysDept sysDept);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysDept
	 * @return
	 */
	public int removeBy(SysDept sysDept);
	
	/**
	 * 
	 * @param sysDept
	 * @return
	 */
	public int modifyByPK(SysDept sysDept);
	
	/**
	 * 
	 * @param sysDept
	 * @return
	 */
	public int modifyWithSelectiveByPK(SysDept sysDept);
	
	/**
	 * 
	 * @param sysDept
	 * @param condiSysDept
	 * @return
	 */
	public int modifyBy(@Param(value="sysDept") SysDept sysDept, @Param(value="condiSysDept") SysDept condiSysDept);
	
	/**
	 * 
	 * @param sysDept
	 * @param condiSysDept
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="sysDept") SysDept sysDept, @Param(value="condiSysDept") SysDept condiSysDept);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public SysDept getByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysDept
	 * @return
	 */
	public SysDept getBy(SysDept sysDept);
	
	/**
	 * 
	 * @return
	 */
	public SysDept get();
	
	/**
	 * 
	 * @param sysDept
	 * @return
	 */
	public List<SysDept> getsBy(SysDept sysDept);
	
	/**
	 * 
	 * @return
	 */
	public List<SysDept> gets();
	
	/**
	 * 
	 * @param sysDept
	 * @return
	 */
	public int getCntBy(@Param(value="sysDept") SysDept sysDept);
	
	/**
	 * 
	 * @param sysDept
	 * @param page
	 * @param size
	 * @return
	 */
	public List<SysDept> getsByPage(@Param(value="sysDept") SysDept sysDept, @Param(value="page") int page, @Param(value="size") int size);
	
}
