/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.WWalletAccountRecordDTO;
import com.bittrade.pojo.vo.WWalletAccountRecordVO;
import com.bittrade.pojo.model.WWalletAccountRecord;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultWWalletAccountRecordDAO extends IBaseDAO<WWalletAccountRecord, WWalletAccountRecordDTO, WWalletAccountRecordVO> {
	
	/**
	 * 
	 * @param wWalletAccountRecord
	 * @return
	 */
	public int add(WWalletAccountRecord wWalletAccountRecord);
	
	/**
	 * 
	 * @param wWalletAccountRecord
	 * @return
	 */
	public int addWithSelective(WWalletAccountRecord wWalletAccountRecord);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param wWalletAccountRecord
	 * @return
	 */
	public int removeBy(WWalletAccountRecord wWalletAccountRecord);
	
	/**
	 * 
	 * @param wWalletAccountRecord
	 * @return
	 */
	public int modifyByPK(WWalletAccountRecord wWalletAccountRecord);
	
	/**
	 * 
	 * @param wWalletAccountRecord
	 * @return
	 */
	public int modifyWithSelectiveByPK(WWalletAccountRecord wWalletAccountRecord);
	
	/**
	 * 
	 * @param wWalletAccountRecord
	 * @param condiWWalletAccountRecord
	 * @return
	 */
	public int modifyBy(@Param(value="wWalletAccountRecord") WWalletAccountRecord wWalletAccountRecord, @Param(value="condiWWalletAccountRecord") WWalletAccountRecord condiWWalletAccountRecord);
	
	/**
	 * 
	 * @param wWalletAccountRecord
	 * @param condiWWalletAccountRecord
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="wWalletAccountRecord") WWalletAccountRecord wWalletAccountRecord, @Param(value="condiWWalletAccountRecord") WWalletAccountRecord condiWWalletAccountRecord);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public WWalletAccountRecord getByPK(Serializable PK);
	
	/**
	 * 
	 * @param wWalletAccountRecord
	 * @return
	 */
	public WWalletAccountRecord getBy(WWalletAccountRecord wWalletAccountRecord);
	
	/**
	 * 
	 * @return
	 */
	public WWalletAccountRecord get();
	
	/**
	 * 
	 * @param wWalletAccountRecord
	 * @return
	 */
	public List<WWalletAccountRecord> getsBy(WWalletAccountRecord wWalletAccountRecord);
	
	/**
	 * 
	 * @return
	 */
	public List<WWalletAccountRecord> gets();
	
	/**
	 * 
	 * @param wWalletAccountRecord
	 * @return
	 */
	public int getCntBy(@Param(value="wWalletAccountRecord") WWalletAccountRecord wWalletAccountRecord);
	
	/**
	 * 
	 * @param wWalletAccountRecord
	 * @param page
	 * @param size
	 * @return
	 */
	public List<WWalletAccountRecord> getsByPage(@Param(value="wWalletAccountRecord") WWalletAccountRecord wWalletAccountRecord, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param wWalletAccountRecordDTO
	 * @return
	 */
	public WWalletAccountRecordDTO getDTOBy(WWalletAccountRecordDTO wWalletAccountRecordDTO);
	
	/**
	 * 
	 * @param wWalletAccountRecordDTO
	 * @return
	 */
	public List<WWalletAccountRecordDTO> getsDTOBy(WWalletAccountRecordDTO wWalletAccountRecordDTO);
	
	/**
	 * 
	 * @param wWalletAccountRecordDTO
	 * @param pageDTO
	 * @return
	 */
	public List<WWalletAccountRecordDTO> getsDTOBy(WWalletAccountRecordDTO wWalletAccountRecordDTO, PageDTO<WWalletAccountRecordDTO> pageDTO);
	
}
