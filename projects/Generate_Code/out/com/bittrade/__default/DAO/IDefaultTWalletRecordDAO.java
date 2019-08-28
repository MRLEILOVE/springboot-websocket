/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TWalletRecordDTO;
import com.bittrade.pojo.vo.TWalletRecordVO;
import com.bittrade.pojo.model.TWalletRecord;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTWalletRecordDAO extends IBaseDAO<TWalletRecord, TWalletRecordDTO, TWalletRecordVO> {
	
	/**
	 * 
	 * @param tWalletRecord
	 * @return
	 */
	public int add(TWalletRecord tWalletRecord);
	
	/**
	 * 
	 * @param tWalletRecord
	 * @return
	 */
	public int addWithSelective(TWalletRecord tWalletRecord);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tWalletRecord
	 * @return
	 */
	public int removeBy(TWalletRecord tWalletRecord);
	
	/**
	 * 
	 * @param tWalletRecord
	 * @return
	 */
	public int modifyByPK(TWalletRecord tWalletRecord);
	
	/**
	 * 
	 * @param tWalletRecord
	 * @return
	 */
	public int modifyWithSelectiveByPK(TWalletRecord tWalletRecord);
	
	/**
	 * 
	 * @param tWalletRecord
	 * @param condiTWalletRecord
	 * @return
	 */
	public int modifyBy(@Param(value="tWalletRecord") TWalletRecord tWalletRecord, @Param(value="condiTWalletRecord") TWalletRecord condiTWalletRecord);
	
	/**
	 * 
	 * @param tWalletRecord
	 * @param condiTWalletRecord
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tWalletRecord") TWalletRecord tWalletRecord, @Param(value="condiTWalletRecord") TWalletRecord condiTWalletRecord);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TWalletRecord getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tWalletRecord
	 * @return
	 */
	public TWalletRecord getBy(TWalletRecord tWalletRecord);
	
	/**
	 * 
	 * @return
	 */
	public TWalletRecord get();
	
	/**
	 * 
	 * @param tWalletRecord
	 * @return
	 */
	public List<TWalletRecord> getsBy(TWalletRecord tWalletRecord);
	
	/**
	 * 
	 * @return
	 */
	public List<TWalletRecord> gets();
	
	/**
	 * 
	 * @param tWalletRecord
	 * @return
	 */
	public int getCntBy(@Param(value="tWalletRecord") TWalletRecord tWalletRecord);
	
	/**
	 * 
	 * @param tWalletRecord
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TWalletRecord> getsByPage(@Param(value="tWalletRecord") TWalletRecord tWalletRecord, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tWalletRecordDTO
	 * @return
	 */
	public TWalletRecordDTO getDTOBy(TWalletRecordDTO tWalletRecordDTO);
	
	/**
	 * 
	 * @param tWalletRecordDTO
	 * @return
	 */
	public List<TWalletRecordDTO> getsDTOBy(TWalletRecordDTO tWalletRecordDTO);
	
	/**
	 * 
	 * @param tWalletRecordDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TWalletRecordDTO> getsDTOBy(TWalletRecordDTO tWalletRecordDTO, PageDTO<TWalletRecordDTO> pageDTO);
	
}
