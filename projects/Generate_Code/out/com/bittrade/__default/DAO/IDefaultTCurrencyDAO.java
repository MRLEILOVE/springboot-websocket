/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TCurrencyDTO;
import com.bittrade.pojo.vo.TCurrencyVO;
import com.bittrade.pojo.model.TCurrency;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTCurrencyDAO extends IBaseDAO<TCurrency, TCurrencyDTO, TCurrencyVO> {
	
	/**
	 * 
	 * @param tCurrency
	 * @return
	 */
	public int add(TCurrency tCurrency);
	
	/**
	 * 
	 * @param tCurrency
	 * @return
	 */
	public int addWithSelective(TCurrency tCurrency);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tCurrency
	 * @return
	 */
	public int removeBy(TCurrency tCurrency);
	
	/**
	 * 
	 * @param tCurrency
	 * @return
	 */
	public int modifyByPK(TCurrency tCurrency);
	
	/**
	 * 
	 * @param tCurrency
	 * @return
	 */
	public int modifyWithSelectiveByPK(TCurrency tCurrency);
	
	/**
	 * 
	 * @param tCurrency
	 * @param condiTCurrency
	 * @return
	 */
	public int modifyBy(@Param(value="tCurrency") TCurrency tCurrency, @Param(value="condiTCurrency") TCurrency condiTCurrency);
	
	/**
	 * 
	 * @param tCurrency
	 * @param condiTCurrency
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tCurrency") TCurrency tCurrency, @Param(value="condiTCurrency") TCurrency condiTCurrency);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TCurrency getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tCurrency
	 * @return
	 */
	public TCurrency getBy(TCurrency tCurrency);
	
	/**
	 * 
	 * @return
	 */
	public TCurrency get();
	
	/**
	 * 
	 * @param tCurrency
	 * @return
	 */
	public List<TCurrency> getsBy(TCurrency tCurrency);
	
	/**
	 * 
	 * @return
	 */
	public List<TCurrency> gets();
	
	/**
	 * 
	 * @param tCurrency
	 * @return
	 */
	public int getCntBy(@Param(value="tCurrency") TCurrency tCurrency);
	
	/**
	 * 
	 * @param tCurrency
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TCurrency> getsByPage(@Param(value="tCurrency") TCurrency tCurrency, @Param(value="page") int page, @Param(value="size") int size);
	
}
