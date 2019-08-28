/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.SysOperLogDTO;
import com.bittrade.pojo.vo.SysOperLogVO;
import com.bittrade.pojo.model.SysOperLog;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultSysOperLogDAO extends IBaseDAO<SysOperLog, SysOperLogDTO, SysOperLogVO> {
	
	/**
	 * 
	 * @param sysOperLog
	 * @return
	 */
	public int add(SysOperLog sysOperLog);
	
	/**
	 * 
	 * @param sysOperLog
	 * @return
	 */
	public int addWithSelective(SysOperLog sysOperLog);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysOperLog
	 * @return
	 */
	public int removeBy(SysOperLog sysOperLog);
	
	/**
	 * 
	 * @param sysOperLog
	 * @return
	 */
	public int modifyByPK(SysOperLog sysOperLog);
	
	/**
	 * 
	 * @param sysOperLog
	 * @return
	 */
	public int modifyWithSelectiveByPK(SysOperLog sysOperLog);
	
	/**
	 * 
	 * @param sysOperLog
	 * @param condiSysOperLog
	 * @return
	 */
	public int modifyBy(@Param(value="sysOperLog") SysOperLog sysOperLog, @Param(value="condiSysOperLog") SysOperLog condiSysOperLog);
	
	/**
	 * 
	 * @param sysOperLog
	 * @param condiSysOperLog
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="sysOperLog") SysOperLog sysOperLog, @Param(value="condiSysOperLog") SysOperLog condiSysOperLog);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public SysOperLog getByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysOperLog
	 * @return
	 */
	public SysOperLog getBy(SysOperLog sysOperLog);
	
	/**
	 * 
	 * @return
	 */
	public SysOperLog get();
	
	/**
	 * 
	 * @param sysOperLog
	 * @return
	 */
	public List<SysOperLog> getsBy(SysOperLog sysOperLog);
	
	/**
	 * 
	 * @return
	 */
	public List<SysOperLog> gets();
	
	/**
	 * 
	 * @param sysOperLog
	 * @return
	 */
	public int getCntBy(@Param(value="sysOperLog") SysOperLog sysOperLog);
	
	/**
	 * 
	 * @param sysOperLog
	 * @param page
	 * @param size
	 * @return
	 */
	public List<SysOperLog> getsByPage(@Param(value="sysOperLog") SysOperLog sysOperLog, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param sysOperLogDTO
	 * @return
	 */
	public SysOperLogDTO getDTOBy(SysOperLogDTO sysOperLogDTO);
	
	/**
	 * 
	 * @param sysOperLogDTO
	 * @return
	 */
	public List<SysOperLogDTO> getsDTOBy(SysOperLogDTO sysOperLogDTO);
	
	/**
	 * 
	 * @param sysOperLogDTO
	 * @param pageDTO
	 * @return
	 */
	public List<SysOperLogDTO> getsDTOBy(SysOperLogDTO sysOperLogDTO, PageDTO<SysOperLogDTO> pageDTO);
	
}
