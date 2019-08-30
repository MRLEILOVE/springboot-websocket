/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.SysConfigDTO;
import com.bittrade.pojo.vo.SysConfigVO;
import com.bittrade.pojo.model.SysConfig;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultSysConfigDAO extends IBaseDAO<SysConfig, SysConfigDTO, SysConfigVO> {
	
	/**
	 * 
	 * @param sysConfig
	 * @return
	 */
	public int add(SysConfig sysConfig);
	
	/**
	 * 
	 * @param sysConfig
	 * @return
	 */
	public int addWithSelective(SysConfig sysConfig);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysConfig
	 * @return
	 */
	public int removeBy(SysConfig sysConfig);
	
	/**
	 * 
	 * @param sysConfig
	 * @return
	 */
	public int modifyByPK(SysConfig sysConfig);
	
	/**
	 * 
	 * @param sysConfig
	 * @return
	 */
	public int modifyWithSelectiveByPK(SysConfig sysConfig);
	
	/**
	 * 
	 * @param sysConfig
	 * @param condiSysConfig
	 * @return
	 */
	public int modifyBy(@Param(value="sysConfig") SysConfig sysConfig, @Param(value="condiSysConfig") SysConfig condiSysConfig);
	
	/**
	 * 
	 * @param sysConfig
	 * @param condiSysConfig
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="sysConfig") SysConfig sysConfig, @Param(value="condiSysConfig") SysConfig condiSysConfig);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public SysConfig getByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysConfig
	 * @return
	 */
	public SysConfig getBy(SysConfig sysConfig);
	
	/**
	 * 
	 * @return
	 */
	public SysConfig get();
	
	/**
	 * 
	 * @param sysConfig
	 * @return
	 */
	public List<SysConfig> getsBy(SysConfig sysConfig);
	
	/**
	 * 
	 * @return
	 */
	public List<SysConfig> gets();
	
	/**
	 * 
	 * @param sysConfig
	 * @return
	 */
	public int getCntBy(@Param(value="sysConfig") SysConfig sysConfig);
	
	/**
	 * 
	 * @param sysConfig
	 * @param page
	 * @param size
	 * @return
	 */
	public List<SysConfig> getsByPage(@Param(value="sysConfig") SysConfig sysConfig, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param sysConfigDTO
	 * @return
	 */
	public SysConfigDTO getDTOBy(SysConfigDTO sysConfigDTO);
	
	/**
	 * 
	 * @param sysConfigDTO
	 * @return
	 */
	public List<SysConfigDTO> getsDTOBy(SysConfigDTO sysConfigDTO);
	
	/**
	 * 
	 * @param sysConfigDTO
	 * @param pageDTO
	 * @return
	 */
	public List<SysConfigDTO> getsDTOBy(SysConfigDTO sysConfigDTO, PageDTO<SysConfigDTO> pageDTO);
	
}
