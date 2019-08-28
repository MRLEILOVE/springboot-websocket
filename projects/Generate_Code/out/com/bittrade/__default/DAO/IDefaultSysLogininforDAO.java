/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.SysLogininforDTO;
import com.bittrade.pojo.vo.SysLogininforVO;
import com.bittrade.pojo.model.SysLogininfor;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultSysLogininforDAO extends IBaseDAO<SysLogininfor, SysLogininforDTO, SysLogininforVO> {
	
	/**
	 * 
	 * @param sysLogininfor
	 * @return
	 */
	public int add(SysLogininfor sysLogininfor);
	
	/**
	 * 
	 * @param sysLogininfor
	 * @return
	 */
	public int addWithSelective(SysLogininfor sysLogininfor);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysLogininfor
	 * @return
	 */
	public int removeBy(SysLogininfor sysLogininfor);
	
	/**
	 * 
	 * @param sysLogininfor
	 * @return
	 */
	public int modifyByPK(SysLogininfor sysLogininfor);
	
	/**
	 * 
	 * @param sysLogininfor
	 * @return
	 */
	public int modifyWithSelectiveByPK(SysLogininfor sysLogininfor);
	
	/**
	 * 
	 * @param sysLogininfor
	 * @param condiSysLogininfor
	 * @return
	 */
	public int modifyBy(@Param(value="sysLogininfor") SysLogininfor sysLogininfor, @Param(value="condiSysLogininfor") SysLogininfor condiSysLogininfor);
	
	/**
	 * 
	 * @param sysLogininfor
	 * @param condiSysLogininfor
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="sysLogininfor") SysLogininfor sysLogininfor, @Param(value="condiSysLogininfor") SysLogininfor condiSysLogininfor);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public SysLogininfor getByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysLogininfor
	 * @return
	 */
	public SysLogininfor getBy(SysLogininfor sysLogininfor);
	
	/**
	 * 
	 * @return
	 */
	public SysLogininfor get();
	
	/**
	 * 
	 * @param sysLogininfor
	 * @return
	 */
	public List<SysLogininfor> getsBy(SysLogininfor sysLogininfor);
	
	/**
	 * 
	 * @return
	 */
	public List<SysLogininfor> gets();
	
	/**
	 * 
	 * @param sysLogininfor
	 * @return
	 */
	public int getCntBy(@Param(value="sysLogininfor") SysLogininfor sysLogininfor);
	
	/**
	 * 
	 * @param sysLogininfor
	 * @param page
	 * @param size
	 * @return
	 */
	public List<SysLogininfor> getsByPage(@Param(value="sysLogininfor") SysLogininfor sysLogininfor, @Param(value="page") int page, @Param(value="size") int size);
	
}
