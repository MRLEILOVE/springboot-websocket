/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TUserCapitalAccountRecordDTO;
import com.bittrade.pojo.vo.TUserCapitalAccountRecordVO;
import com.bittrade.pojo.model.TUserCapitalAccountRecord;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTUserCapitalAccountRecordDAO extends IBaseDAO<TUserCapitalAccountRecord, TUserCapitalAccountRecordDTO, TUserCapitalAccountRecordVO> {
	
	/**
	 * 
	 * @param tUserCapitalAccountRecord
	 * @return
	 */
	public int add(TUserCapitalAccountRecord tUserCapitalAccountRecord);
	
	/**
	 * 
	 * @param tUserCapitalAccountRecord
	 * @return
	 */
	public int addWithSelective(TUserCapitalAccountRecord tUserCapitalAccountRecord);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tUserCapitalAccountRecord
	 * @return
	 */
	public int removeBy(TUserCapitalAccountRecord tUserCapitalAccountRecord);
	
	/**
	 * 
	 * @param tUserCapitalAccountRecord
	 * @return
	 */
	public int modifyByPK(TUserCapitalAccountRecord tUserCapitalAccountRecord);
	
	/**
	 * 
	 * @param tUserCapitalAccountRecord
	 * @return
	 */
	public int modifyWithSelectiveByPK(TUserCapitalAccountRecord tUserCapitalAccountRecord);
	
	/**
	 * 
	 * @param tUserCapitalAccountRecord
	 * @param condiTUserCapitalAccountRecord
	 * @return
	 */
	public int modifyBy(@Param(value="tUserCapitalAccountRecord") TUserCapitalAccountRecord tUserCapitalAccountRecord, @Param(value="condiTUserCapitalAccountRecord") TUserCapitalAccountRecord condiTUserCapitalAccountRecord);
	
	/**
	 * 
	 * @param tUserCapitalAccountRecord
	 * @param condiTUserCapitalAccountRecord
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tUserCapitalAccountRecord") TUserCapitalAccountRecord tUserCapitalAccountRecord, @Param(value="condiTUserCapitalAccountRecord") TUserCapitalAccountRecord condiTUserCapitalAccountRecord);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TUserCapitalAccountRecord getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tUserCapitalAccountRecord
	 * @return
	 */
	public TUserCapitalAccountRecord getBy(TUserCapitalAccountRecord tUserCapitalAccountRecord);
	
	/**
	 * 
	 * @return
	 */
	public TUserCapitalAccountRecord get();
	
	/**
	 * 
	 * @param tUserCapitalAccountRecord
	 * @return
	 */
	public List<TUserCapitalAccountRecord> getsBy(TUserCapitalAccountRecord tUserCapitalAccountRecord);
	
	/**
	 * 
	 * @return
	 */
	public List<TUserCapitalAccountRecord> gets();
	
	/**
	 * 
	 * @param tUserCapitalAccountRecord
	 * @return
	 */
	public int getCntBy(@Param(value="tUserCapitalAccountRecord") TUserCapitalAccountRecord tUserCapitalAccountRecord);
	
	/**
	 * 
	 * @param tUserCapitalAccountRecord
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TUserCapitalAccountRecord> getsByPage(@Param(value="tUserCapitalAccountRecord") TUserCapitalAccountRecord tUserCapitalAccountRecord, @Param(value="page") int page, @Param(value="size") int size);
	
}
