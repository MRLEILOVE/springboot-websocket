/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TEntrustRecordDTO;
import com.bittrade.pojo.vo.TEntrustRecordVO;
import com.bittrade.pojo.model.TEntrustRecord;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTEntrustRecordDAO extends IBaseDAO<TEntrustRecord, TEntrustRecordDTO, TEntrustRecordVO> {
	
	/**
	 * 
	 * @param tEntrustRecord
	 * @return
	 */
	public int add(TEntrustRecord tEntrustRecord);
	
	/**
	 * 
	 * @param tEntrustRecord
	 * @return
	 */
	public int addWithSelective(TEntrustRecord tEntrustRecord);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tEntrustRecord
	 * @return
	 */
	public int removeBy(TEntrustRecord tEntrustRecord);
	
	/**
	 * 
	 * @param tEntrustRecord
	 * @return
	 */
	public int modifyByPK(TEntrustRecord tEntrustRecord);
	
	/**
	 * 
	 * @param tEntrustRecord
	 * @return
	 */
	public int modifyWithSelectiveByPK(TEntrustRecord tEntrustRecord);
	
	/**
	 * 
	 * @param tEntrustRecord
	 * @param condiTEntrustRecord
	 * @return
	 */
	public int modifyBy(@Param(value="tEntrustRecord") TEntrustRecord tEntrustRecord, @Param(value="condiTEntrustRecord") TEntrustRecord condiTEntrustRecord);
	
	/**
	 * 
	 * @param tEntrustRecord
	 * @param condiTEntrustRecord
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tEntrustRecord") TEntrustRecord tEntrustRecord, @Param(value="condiTEntrustRecord") TEntrustRecord condiTEntrustRecord);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TEntrustRecord getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tEntrustRecord
	 * @return
	 */
	public TEntrustRecord getBy(TEntrustRecord tEntrustRecord);
	
	/**
	 * 
	 * @return
	 */
	public TEntrustRecord get();
	
	/**
	 * 
	 * @param tEntrustRecord
	 * @return
	 */
	public List<TEntrustRecord> getsBy(TEntrustRecord tEntrustRecord);
	
	/**
	 * 
	 * @return
	 */
	public List<TEntrustRecord> gets();
	
	/**
	 * 
	 * @param tEntrustRecord
	 * @return
	 */
	public int getCntBy(@Param(value="tEntrustRecord") TEntrustRecord tEntrustRecord);
	
	/**
	 * 
	 * @param tEntrustRecord
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TEntrustRecord> getsByPage(@Param(value="tEntrustRecord") TEntrustRecord tEntrustRecord, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tEntrustRecordDTO
	 * @return
	 */
	public TEntrustRecordDTO getDTOBy(TEntrustRecordDTO tEntrustRecordDTO);
	
	/**
	 * 
	 * @param tEntrustRecordDTO
	 * @return
	 */
	public List<TEntrustRecordDTO> getsDTOBy(TEntrustRecordDTO tEntrustRecordDTO);
	
	/**
	 * 
	 * @param tEntrustRecordDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TEntrustRecordDTO> getsDTOBy(TEntrustRecordDTO tEntrustRecordDTO, PageDTO<TEntrustRecordDTO> pageDTO);
	
}
