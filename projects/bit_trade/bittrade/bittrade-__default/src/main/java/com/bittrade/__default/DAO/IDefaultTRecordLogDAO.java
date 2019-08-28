/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TRecordLogDTO;
import com.bittrade.pojo.vo.TRecordLogVO;
import com.bittrade.pojo.model.TRecordLog;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTRecordLogDAO extends IBaseDAO<TRecordLog, TRecordLogDTO, TRecordLogVO> {
	
	/**
	 * 
	 * @param tRecordLog
	 * @return
	 */
	public int add(TRecordLog tRecordLog);
	
	/**
	 * 
	 * @param tRecordLog
	 * @return
	 */
	public int addWithSelective(TRecordLog tRecordLog);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tRecordLog
	 * @return
	 */
	public int removeBy(TRecordLog tRecordLog);
	
	/**
	 * 
	 * @param tRecordLog
	 * @return
	 */
	public int modifyByPK(TRecordLog tRecordLog);
	
	/**
	 * 
	 * @param tRecordLog
	 * @return
	 */
	public int modifyWithSelectiveByPK(TRecordLog tRecordLog);
	
	/**
	 * 
	 * @param tRecordLog
	 * @param condiTRecordLog
	 * @return
	 */
	public int modifyBy(@Param(value="tRecordLog") TRecordLog tRecordLog, @Param(value="condiTRecordLog") TRecordLog condiTRecordLog);
	
	/**
	 * 
	 * @param tRecordLog
	 * @param condiTRecordLog
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tRecordLog") TRecordLog tRecordLog, @Param(value="condiTRecordLog") TRecordLog condiTRecordLog);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TRecordLog getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tRecordLog
	 * @return
	 */
	public TRecordLog getBy(TRecordLog tRecordLog);
	
	/**
	 * 
	 * @return
	 */
	public TRecordLog get();
	
	/**
	 * 
	 * @param tRecordLog
	 * @return
	 */
	public List<TRecordLog> getsBy(TRecordLog tRecordLog);
	
	/**
	 * 
	 * @return
	 */
	public List<TRecordLog> gets();
	
	/**
	 * 
	 * @param tRecordLog
	 * @return
	 */
	public int getCntBy(@Param(value="tRecordLog") TRecordLog tRecordLog);
	
	/**
	 * 
	 * @param tRecordLog
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TRecordLog> getsByPage(@Param(value="tRecordLog") TRecordLog tRecordLog, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tRecordLogDTO
	 * @return
	 */
	public TRecordLogDTO getDTOBy(TRecordLogDTO tRecordLogDTO);
	
	/**
	 * 
	 * @param tRecordLogDTO
	 * @return
	 */
	public List<TRecordLogDTO> getsDTOBy(TRecordLogDTO tRecordLogDTO);
	
	/**
	 * 
	 * @param tRecordLogDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TRecordLogDTO> getsDTOBy(@Param(value="tRecordLog") TRecordLogDTO tRecordLogDTO, PageDTO<TRecordLogDTO> pageDTO);
	
}
