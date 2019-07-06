/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.api.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TEntrustRecordDTO;
import com.bittrade.pojo.vo.TEntrustRecordVO;
import com.bittrade.pojo.model.TEntrustRecord;
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
	public int remove(TEntrustRecord tEntrustRecord);
	
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
	public int modify(@Param(value="tEntrustRecord") TEntrustRecord tEntrustRecord, @Param(value="condiTEntrustRecord") TEntrustRecord condiTEntrustRecord);
	
	/**
	 * 
	 * @param tEntrustRecord
	 * @param condiTEntrustRecord
	 * @return
	 */
	public int modifyWithSelective(@Param(value="tEntrustRecord") TEntrustRecord tEntrustRecord, @Param(value="condiTEntrustRecord") TEntrustRecord condiTEntrustRecord);
	
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
	public List<TEntrustRecordDTO> get(TEntrustRecord tEntrustRecord);
	
	/**
	 * 
	 * @return
	 */
	public List<TEntrustRecordDTO> gets();
	
	/**
	 * 
	 * @param tEntrustRecord
	 * @return
	 */
	public int getCntWithPage(@Param(value="tEntrustRecord") TEntrustRecord tEntrustRecord);
	
	/**
	 * 
	 * @param tEntrustRecord
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TEntrustRecordDTO> getsWithPage(@Param(value="tEntrustRecord") TEntrustRecord tEntrustRecord, @Param(value="page") int page, @Param(value="size") int size);
	
}
