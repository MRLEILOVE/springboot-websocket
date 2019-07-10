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
	public int remove(TCurrencyOptional tCurrencyOptional);
	
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
	public int modify(@Param(value="tCurrencyOptional") TCurrencyOptional tCurrencyOptional, @Param(value="condiTCurrencyOptional") TCurrencyOptional condiTCurrencyOptional);
	
	/**
	 * 
	 * @param tCurrencyOptional
	 * @param condiTCurrencyOptional
	 * @return
	 */
	public int modifyWithSelective(@Param(value="tCurrencyOptional") TCurrencyOptional tCurrencyOptional, @Param(value="condiTCurrencyOptional") TCurrencyOptional condiTCurrencyOptional);
	
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
	public List<TCurrencyOptionalDTO> get(TCurrencyOptional tCurrencyOptional);
	
	/**
	 * 
	 * @return
	 */
	public List<TCurrencyOptionalDTO> gets();
	
	/**
	 * 
	 * @param tCurrencyOptional
	 * @return
	 */
	public int getCntWithPage(@Param(value="tCurrencyOptional") TCurrencyOptional tCurrencyOptional);
	
	/**
	 * 
	 * @param tCurrencyOptional
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TCurrencyOptionalDTO> getsWithPage(@Param(value="tCurrencyOptional") TCurrencyOptional tCurrencyOptional, @Param(value="page") int page, @Param(value="size") int size);
	
}
