/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.SysUserOnlineDTO;
import com.bittrade.pojo.vo.SysUserOnlineVO;
import com.bittrade.pojo.model.SysUserOnline;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultSysUserOnlineDAO extends IBaseDAO<SysUserOnline, SysUserOnlineDTO, SysUserOnlineVO> {
	
	/**
	 * 
	 * @param sysUserOnline
	 * @return
	 */
	public int add(SysUserOnline sysUserOnline);
	
	/**
	 * 
	 * @param sysUserOnline
	 * @return
	 */
	public int addWithSelective(SysUserOnline sysUserOnline);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysUserOnline
	 * @return
	 */
	public int removeBy(SysUserOnline sysUserOnline);
	
	/**
	 * 
	 * @param sysUserOnline
	 * @return
	 */
	public int modifyByPK(SysUserOnline sysUserOnline);
	
	/**
	 * 
	 * @param sysUserOnline
	 * @return
	 */
	public int modifyWithSelectiveByPK(SysUserOnline sysUserOnline);
	
	/**
	 * 
	 * @param sysUserOnline
	 * @param condiSysUserOnline
	 * @return
	 */
	public int modifyBy(@Param(value="sysUserOnline") SysUserOnline sysUserOnline, @Param(value="condiSysUserOnline") SysUserOnline condiSysUserOnline);
	
	/**
	 * 
	 * @param sysUserOnline
	 * @param condiSysUserOnline
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="sysUserOnline") SysUserOnline sysUserOnline, @Param(value="condiSysUserOnline") SysUserOnline condiSysUserOnline);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public SysUserOnline getByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysUserOnline
	 * @return
	 */
	public SysUserOnline getBy(SysUserOnline sysUserOnline);
	
	/**
	 * 
	 * @return
	 */
	public SysUserOnline get();
	
	/**
	 * 
	 * @param sysUserOnline
	 * @return
	 */
	public List<SysUserOnline> getsBy(SysUserOnline sysUserOnline);
	
	/**
	 * 
	 * @return
	 */
	public List<SysUserOnline> gets();
	
	/**
	 * 
	 * @param sysUserOnline
	 * @return
	 */
	public int getCntBy(@Param(value="sysUserOnline") SysUserOnline sysUserOnline);
	
	/**
	 * 
	 * @param sysUserOnline
	 * @param page
	 * @param size
	 * @return
	 */
	public List<SysUserOnline> getsByPage(@Param(value="sysUserOnline") SysUserOnline sysUserOnline, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param sysUserOnlineDTO
	 * @return
	 */
	public SysUserOnlineDTO getDTOBy(SysUserOnlineDTO sysUserOnlineDTO);
	
	/**
	 * 
	 * @param sysUserOnlineDTO
	 * @return
	 */
	public List<SysUserOnlineDTO> getsDTOBy(SysUserOnlineDTO sysUserOnlineDTO);
	
	/**
	 * 
	 * @param sysUserOnlineDTO
	 * @param pageDTO
	 * @return
	 */
	public List<SysUserOnlineDTO> getsDTOBy(@Param(value="sysUserOnline") SysUserOnlineDTO sysUserOnlineDTO, PageDTO<SysUserOnlineDTO> pageDTO);
	
}
