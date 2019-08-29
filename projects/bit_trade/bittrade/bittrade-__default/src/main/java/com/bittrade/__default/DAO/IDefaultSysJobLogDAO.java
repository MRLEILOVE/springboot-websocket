/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.SysJobLogDTO;
import com.bittrade.pojo.vo.SysJobLogVO;
import com.bittrade.pojo.model.SysJobLog;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultSysJobLogDAO extends IBaseDAO<SysJobLog, SysJobLogDTO, SysJobLogVO> {
	
	/**
	 * 
	 * @param sysJobLog
	 * @return
	 */
	public int add(SysJobLog sysJobLog);
	
	/**
	 * 
	 * @param sysJobLog
	 * @return
	 */
	public int addWithSelective(SysJobLog sysJobLog);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysJobLog
	 * @return
	 */
	public int removeBy(SysJobLog sysJobLog);
	
	/**
	 * 
	 * @param sysJobLog
	 * @return
	 */
	public int modifyByPK(SysJobLog sysJobLog);
	
	/**
	 * 
	 * @param sysJobLog
	 * @return
	 */
	public int modifyWithSelectiveByPK(SysJobLog sysJobLog);
	
	/**
	 * 
	 * @param sysJobLog
	 * @param condiSysJobLog
	 * @return
	 */
	public int modifyBy(@Param(value="sysJobLog") SysJobLog sysJobLog, @Param(value="condiSysJobLog") SysJobLog condiSysJobLog);
	
	/**
	 * 
	 * @param sysJobLog
	 * @param condiSysJobLog
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="sysJobLog") SysJobLog sysJobLog, @Param(value="condiSysJobLog") SysJobLog condiSysJobLog);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public SysJobLog getByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysJobLog
	 * @return
	 */
	public SysJobLog getBy(SysJobLog sysJobLog);
	
	/**
	 * 
	 * @return
	 */
	public SysJobLog get();
	
	/**
	 * 
	 * @param sysJobLog
	 * @return
	 */
	public List<SysJobLog> getsBy(SysJobLog sysJobLog);
	
	/**
	 * 
	 * @return
	 */
	public List<SysJobLog> gets();
	
	/**
	 * 
	 * @param sysJobLog
	 * @return
	 */
	public int getCntBy(@Param(value="sysJobLog") SysJobLog sysJobLog);
	
	/**
	 * 
	 * @param sysJobLog
	 * @param page
	 * @param size
	 * @return
	 */
	public List<SysJobLog> getsByPage(@Param(value="sysJobLog") SysJobLog sysJobLog, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param sysJobLogDTO
	 * @return
	 */
	public SysJobLogDTO getDTOBy(SysJobLogDTO sysJobLogDTO);
	
	/**
	 * 
	 * @param sysJobLogDTO
	 * @return
	 */
	public List<SysJobLogDTO> getsDTOBy(SysJobLogDTO sysJobLogDTO);
	
	/**
	 * 
	 * @param sysJobLogDTO
	 * @param pageDTO
	 * @return
	 */
	public List<SysJobLogDTO> getsDTOBy(@Param(value="sysJobLog") SysJobLogDTO sysJobLogDTO, PageDTO<SysJobLogDTO> pageDTO);
	
}
