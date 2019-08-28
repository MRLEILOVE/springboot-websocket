/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TLegalCurrencyRecordDTO;
import com.bittrade.pojo.vo.TLegalCurrencyRecordVO;
import com.bittrade.pojo.model.TLegalCurrencyRecord;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTLegalCurrencyRecordDAO extends IBaseDAO<TLegalCurrencyRecord, TLegalCurrencyRecordDTO, TLegalCurrencyRecordVO> {
	
	/**
	 * 
	 * @param tLegalCurrencyRecord
	 * @return
	 */
	public int add(TLegalCurrencyRecord tLegalCurrencyRecord);
	
	/**
	 * 
	 * @param tLegalCurrencyRecord
	 * @return
	 */
	public int addWithSelective(TLegalCurrencyRecord tLegalCurrencyRecord);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tLegalCurrencyRecord
	 * @return
	 */
	public int removeBy(TLegalCurrencyRecord tLegalCurrencyRecord);
	
	/**
	 * 
	 * @param tLegalCurrencyRecord
	 * @return
	 */
	public int modifyByPK(TLegalCurrencyRecord tLegalCurrencyRecord);
	
	/**
	 * 
	 * @param tLegalCurrencyRecord
	 * @return
	 */
	public int modifyWithSelectiveByPK(TLegalCurrencyRecord tLegalCurrencyRecord);
	
	/**
	 * 
	 * @param tLegalCurrencyRecord
	 * @param condiTLegalCurrencyRecord
	 * @return
	 */
	public int modifyBy(@Param(value="tLegalCurrencyRecord") TLegalCurrencyRecord tLegalCurrencyRecord, @Param(value="condiTLegalCurrencyRecord") TLegalCurrencyRecord condiTLegalCurrencyRecord);
	
	/**
	 * 
	 * @param tLegalCurrencyRecord
	 * @param condiTLegalCurrencyRecord
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tLegalCurrencyRecord") TLegalCurrencyRecord tLegalCurrencyRecord, @Param(value="condiTLegalCurrencyRecord") TLegalCurrencyRecord condiTLegalCurrencyRecord);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TLegalCurrencyRecord getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tLegalCurrencyRecord
	 * @return
	 */
	public TLegalCurrencyRecord getBy(TLegalCurrencyRecord tLegalCurrencyRecord);
	
	/**
	 * 
	 * @return
	 */
	public TLegalCurrencyRecord get();
	
	/**
	 * 
	 * @param tLegalCurrencyRecord
	 * @return
	 */
	public List<TLegalCurrencyRecord> getsBy(TLegalCurrencyRecord tLegalCurrencyRecord);
	
	/**
	 * 
	 * @return
	 */
	public List<TLegalCurrencyRecord> gets();
	
	/**
	 * 
	 * @param tLegalCurrencyRecord
	 * @return
	 */
	public int getCntBy(@Param(value="tLegalCurrencyRecord") TLegalCurrencyRecord tLegalCurrencyRecord);
	
	/**
	 * 
	 * @param tLegalCurrencyRecord
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TLegalCurrencyRecord> getsByPage(@Param(value="tLegalCurrencyRecord") TLegalCurrencyRecord tLegalCurrencyRecord, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tLegalCurrencyRecordDTO
	 * @return
	 */
	public TLegalCurrencyRecordDTO getDTOBy(TLegalCurrencyRecordDTO tLegalCurrencyRecordDTO);
	
	/**
	 * 
	 * @param tLegalCurrencyRecordDTO
	 * @return
	 */
	public List<TLegalCurrencyRecordDTO> getsDTOBy(TLegalCurrencyRecordDTO tLegalCurrencyRecordDTO);
	
	/**
	 * 
	 * @param tLegalCurrencyRecordDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TLegalCurrencyRecordDTO> getsDTOBy(@Param(value="tLegalCurrencyRecord") TLegalCurrencyRecordDTO tLegalCurrencyRecordDTO, PageDTO<TLegalCurrencyRecordDTO> pageDTO);
	
}
