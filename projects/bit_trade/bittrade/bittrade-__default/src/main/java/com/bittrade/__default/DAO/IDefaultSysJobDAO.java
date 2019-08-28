/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.SysJobDTO;
import com.bittrade.pojo.vo.SysJobVO;
import com.bittrade.pojo.model.SysJob;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultSysJobDAO extends IBaseDAO<SysJob, SysJobDTO, SysJobVO> {
	
	/**
	 * 
	 * @param sysJob
	 * @return
	 */
	public int add(SysJob sysJob);
	
	/**
	 * 
	 * @param sysJob
	 * @return
	 */
	public int addWithSelective(SysJob sysJob);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysJob
	 * @return
	 */
	public int removeBy(SysJob sysJob);
	
	/**
	 * 
	 * @param sysJob
	 * @return
	 */
	public int modifyByPK(SysJob sysJob);
	
	/**
	 * 
	 * @param sysJob
	 * @return
	 */
	public int modifyWithSelectiveByPK(SysJob sysJob);
	
	/**
	 * 
	 * @param sysJob
	 * @param condiSysJob
	 * @return
	 */
	public int modifyBy(@Param(value="sysJob") SysJob sysJob, @Param(value="condiSysJob") SysJob condiSysJob);
	
	/**
	 * 
	 * @param sysJob
	 * @param condiSysJob
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="sysJob") SysJob sysJob, @Param(value="condiSysJob") SysJob condiSysJob);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public SysJob getByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysJob
	 * @return
	 */
	public SysJob getBy(SysJob sysJob);
	
	/**
	 * 
	 * @return
	 */
	public SysJob get();
	
	/**
	 * 
	 * @param sysJob
	 * @return
	 */
	public List<SysJob> getsBy(SysJob sysJob);
	
	/**
	 * 
	 * @return
	 */
	public List<SysJob> gets();
	
	/**
	 * 
	 * @param sysJob
	 * @return
	 */
	public int getCntBy(@Param(value="sysJob") SysJob sysJob);
	
	/**
	 * 
	 * @param sysJob
	 * @param page
	 * @param size
	 * @return
	 */
	public List<SysJob> getsByPage(@Param(value="sysJob") SysJob sysJob, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param sysJobDTO
	 * @return
	 */
	public SysJobDTO getDTOBy(SysJobDTO sysJobDTO);
	
	/**
	 * 
	 * @param sysJobDTO
	 * @return
	 */
	public List<SysJobDTO> getsDTOBy(SysJobDTO sysJobDTO);
	
	/**
	 * 
	 * @param sysJobDTO
	 * @param pageDTO
	 * @return
	 */
	public List<SysJobDTO> getsDTOBy(SysJobDTO sysJobDTO, PageDTO<SysJobDTO> pageDTO);
	
}
