/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TChatRecordLogDTO;
import com.bittrade.pojo.vo.TChatRecordLogVO;
import com.bittrade.pojo.model.TChatRecordLog;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTChatRecordLogDAO extends IBaseDAO<TChatRecordLog, TChatRecordLogDTO, TChatRecordLogVO> {
	
	/**
	 * 
	 * @param tChatRecordLog
	 * @return
	 */
	public int add(TChatRecordLog tChatRecordLog);
	
	/**
	 * 
	 * @param tChatRecordLog
	 * @return
	 */
	public int addWithSelective(TChatRecordLog tChatRecordLog);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tChatRecordLog
	 * @return
	 */
	public int removeBy(TChatRecordLog tChatRecordLog);
	
	/**
	 * 
	 * @param tChatRecordLog
	 * @return
	 */
	public int modifyByPK(TChatRecordLog tChatRecordLog);
	
	/**
	 * 
	 * @param tChatRecordLog
	 * @return
	 */
	public int modifyWithSelectiveByPK(TChatRecordLog tChatRecordLog);
	
	/**
	 * 
	 * @param tChatRecordLog
	 * @param condiTChatRecordLog
	 * @return
	 */
	public int modifyBy(@Param(value="tChatRecordLog") TChatRecordLog tChatRecordLog, @Param(value="condiTChatRecordLog") TChatRecordLog condiTChatRecordLog);
	
	/**
	 * 
	 * @param tChatRecordLog
	 * @param condiTChatRecordLog
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tChatRecordLog") TChatRecordLog tChatRecordLog, @Param(value="condiTChatRecordLog") TChatRecordLog condiTChatRecordLog);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TChatRecordLog getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tChatRecordLog
	 * @return
	 */
	public TChatRecordLog getBy(TChatRecordLog tChatRecordLog);
	
	/**
	 * 
	 * @return
	 */
	public TChatRecordLog get();
	
	/**
	 * 
	 * @param tChatRecordLog
	 * @return
	 */
	public List<TChatRecordLog> getsBy(TChatRecordLog tChatRecordLog);
	
	/**
	 * 
	 * @return
	 */
	public List<TChatRecordLog> gets();
	
	/**
	 * 
	 * @param tChatRecordLog
	 * @return
	 */
	public int getCntBy(@Param(value="tChatRecordLog") TChatRecordLog tChatRecordLog);
	
	/**
	 * 
	 * @param tChatRecordLog
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TChatRecordLog> getsByPage(@Param(value="tChatRecordLog") TChatRecordLog tChatRecordLog, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tChatRecordLogDTO
	 * @return
	 */
	public TChatRecordLogDTO getDTOBy(TChatRecordLogDTO tChatRecordLogDTO);
	
	/**
	 * 
	 * @param tChatRecordLogDTO
	 * @return
	 */
	public List<TChatRecordLogDTO> getsDTOBy(TChatRecordLogDTO tChatRecordLogDTO);
	
	/**
	 * 
	 * @param tChatRecordLogDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TChatRecordLogDTO> getsDTOBy(TChatRecordLogDTO tChatRecordLogDTO, PageDTO<TChatRecordLogDTO> pageDTO);
	
}
