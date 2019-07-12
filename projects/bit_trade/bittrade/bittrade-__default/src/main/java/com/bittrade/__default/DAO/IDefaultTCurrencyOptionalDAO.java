/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TCurrencyOptionalDTO;
import com.bittrade.pojo.vo.TCurrencyOptionalVO;
import com.bittrade.pojo.model.TCurrencyOptional;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTCurrencyOptionalDAO extends IBaseDAO<TCurrencyOptional, TCurrencyOptionalDTO, TCurrencyOptionalVO> {
	
	/**
	 * 
	 * @param tCurrencyOptional
	 * @return
	 */
	public int add(TCurrencyOptional tCurrencyOptional);
	
	/**
	 * 
	 * @param tCurrencyOptional
	 * @return
	 */
	public int addWithSelective(TCurrencyOptional tCurrencyOptional);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tCurrencyOptional
	 * @return
	 */
	public int removeBy(TCurrencyOptional tCurrencyOptional);
	
	/**
	 * 
	 * @param tCurrencyOptional
	 * @return
	 */
	public int modifyByPK(TCurrencyOptional tCurrencyOptional);
	
	/**
	 * 
	 * @param tCurrencyOptional
	 * @return
	 */
	public int modifyWithSelectiveByPK(TCurrencyOptional tCurrencyOptional);
	
	/**
	 * 
	 * @param tCurrencyOptional
	 * @param condiTCurrencyOptional
	 * @return
	 */
	public int modifyBy(@Param(value="tCurrencyOptional") TCurrencyOptional tCurrencyOptional, @Param(value="condiTCurrencyOptional") TCurrencyOptional condiTCurrencyOptional);
	
	/**
	 * 
	 * @param tCurrencyOptional
	 * @param condiTCurrencyOptional
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tCurrencyOptional") TCurrencyOptional tCurrencyOptional, @Param(value="condiTCurrencyOptional") TCurrencyOptional condiTCurrencyOptional);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TCurrencyOptional getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tCurrencyOptional
	 * @return
	 */
	public TCurrencyOptional getBy(TCurrencyOptional tCurrencyOptional);
	
	/**
	 * 
	 * @return
	 */
	public TCurrencyOptional get();
	
	/**
	 * 
	 * @param tCurrencyOptional
	 * @return
	 */
	public List<TCurrencyOptional> getsBy(TCurrencyOptional tCurrencyOptional);
	
	/**
	 * 
	 * @return
	 */
	public List<TCurrencyOptional> gets();
	
	/**
	 * 
	 * @param tCurrencyOptional
	 * @return
	 */
	public int getCntBy(@Param(value="tCurrencyOptional") TCurrencyOptional tCurrencyOptional);
	
	/**
	 * 
	 * @param tCurrencyOptional
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TCurrencyOptional> getsByPage(@Param(value="tCurrencyOptional") TCurrencyOptional tCurrencyOptional, @Param(value="page") int page, @Param(value="size") int size);
	
}
