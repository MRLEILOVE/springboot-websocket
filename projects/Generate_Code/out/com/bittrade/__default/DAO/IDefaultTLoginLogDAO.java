/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TLoginLogDTO;
import com.bittrade.pojo.vo.TLoginLogVO;
import com.bittrade.pojo.model.TLoginLog;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTLoginLogDAO extends IBaseDAO<TLoginLog, TLoginLogDTO, TLoginLogVO> {
	
	/**
	 * 
	 * @param tLoginLog
	 * @return
	 */
	public int add(TLoginLog tLoginLog);
	
	/**
	 * 
	 * @param tLoginLog
	 * @return
	 */
	public int addWithSelective(TLoginLog tLoginLog);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tLoginLog
	 * @return
	 */
	public int removeBy(TLoginLog tLoginLog);
	
	/**
	 * 
	 * @param tLoginLog
	 * @return
	 */
	public int modifyByPK(TLoginLog tLoginLog);
	
	/**
	 * 
	 * @param tLoginLog
	 * @return
	 */
	public int modifyWithSelectiveByPK(TLoginLog tLoginLog);
	
	/**
	 * 
	 * @param tLoginLog
	 * @param condiTLoginLog
	 * @return
	 */
	public int modifyBy(@Param(value="tLoginLog") TLoginLog tLoginLog, @Param(value="condiTLoginLog") TLoginLog condiTLoginLog);
	
	/**
	 * 
	 * @param tLoginLog
	 * @param condiTLoginLog
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tLoginLog") TLoginLog tLoginLog, @Param(value="condiTLoginLog") TLoginLog condiTLoginLog);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TLoginLog getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tLoginLog
	 * @return
	 */
	public TLoginLog getBy(TLoginLog tLoginLog);
	
	/**
	 * 
	 * @return
	 */
	public TLoginLog get();
	
	/**
	 * 
	 * @param tLoginLog
	 * @return
	 */
	public List<TLoginLog> getsBy(TLoginLog tLoginLog);
	
	/**
	 * 
	 * @return
	 */
	public List<TLoginLog> gets();
	
	/**
	 * 
	 * @param tLoginLog
	 * @return
	 */
	public int getCntBy(@Param(value="tLoginLog") TLoginLog tLoginLog);
	
	/**
	 * 
	 * @param tLoginLog
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TLoginLog> getsByPage(@Param(value="tLoginLog") TLoginLog tLoginLog, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tLoginLogDTO
	 * @return
	 */
	public TLoginLogDTO getDTOBy(TLoginLogDTO tLoginLogDTO);
	
	/**
	 * 
	 * @param tLoginLogDTO
	 * @return
	 */
	public List<TLoginLogDTO> getsDTOBy(TLoginLogDTO tLoginLogDTO);
	
	/**
	 * 
	 * @param tLoginLogDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TLoginLogDTO> getsDTOBy(TLoginLogDTO tLoginLogDTO, PageDTO<TLoginLogDTO> pageDTO);
	
}
