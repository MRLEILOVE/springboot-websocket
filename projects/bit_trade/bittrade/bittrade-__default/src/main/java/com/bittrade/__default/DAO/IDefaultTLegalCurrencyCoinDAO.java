/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TLegalCurrencyCoinDTO;
import com.bittrade.pojo.vo.TLegalCurrencyCoinVO;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTLegalCurrencyCoinDAO extends IBaseDAO<TLegalCurrencyCoin, TLegalCurrencyCoinDTO, TLegalCurrencyCoinVO> {
	
	/**
	 * 
	 * @param tLegalCurrencyCoin
	 * @return
	 */
	public int add(TLegalCurrencyCoin tLegalCurrencyCoin);
	
	/**
	 * 
	 * @param tLegalCurrencyCoin
	 * @return
	 */
	public int addWithSelective(TLegalCurrencyCoin tLegalCurrencyCoin);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tLegalCurrencyCoin
	 * @return
	 */
	public int removeBy(TLegalCurrencyCoin tLegalCurrencyCoin);
	
	/**
	 * 
	 * @param tLegalCurrencyCoin
	 * @return
	 */
	public int modifyByPK(TLegalCurrencyCoin tLegalCurrencyCoin);
	
	/**
	 * 
	 * @param tLegalCurrencyCoin
	 * @return
	 */
	public int modifyWithSelectiveByPK(TLegalCurrencyCoin tLegalCurrencyCoin);
	
	/**
	 * 
	 * @param tLegalCurrencyCoin
	 * @param condiTLegalCurrencyCoin
	 * @return
	 */
	public int modifyBy(@Param(value="tLegalCurrencyCoin") TLegalCurrencyCoin tLegalCurrencyCoin, @Param(value="condiTLegalCurrencyCoin") TLegalCurrencyCoin condiTLegalCurrencyCoin);
	
	/**
	 * 
	 * @param tLegalCurrencyCoin
	 * @param condiTLegalCurrencyCoin
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tLegalCurrencyCoin") TLegalCurrencyCoin tLegalCurrencyCoin, @Param(value="condiTLegalCurrencyCoin") TLegalCurrencyCoin condiTLegalCurrencyCoin);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TLegalCurrencyCoin getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tLegalCurrencyCoin
	 * @return
	 */
	public TLegalCurrencyCoin getBy(TLegalCurrencyCoin tLegalCurrencyCoin);
	
	/**
	 * 
	 * @return
	 */
	public TLegalCurrencyCoin get();
	
	/**
	 * 
	 * @param tLegalCurrencyCoin
	 * @return
	 */
	public List<TLegalCurrencyCoin> getsBy(TLegalCurrencyCoin tLegalCurrencyCoin);
	
	/**
	 * 
	 * @return
	 */
	public List<TLegalCurrencyCoin> gets();
	
	/**
	 * 
	 * @param tLegalCurrencyCoin
	 * @return
	 */
	public int getCntBy(@Param(value="tLegalCurrencyCoin") TLegalCurrencyCoin tLegalCurrencyCoin);
	
	/**
	 * 
	 * @param tLegalCurrencyCoin
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TLegalCurrencyCoin> getsByPage(@Param(value="tLegalCurrencyCoin") TLegalCurrencyCoin tLegalCurrencyCoin, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tLegalCurrencyCoinDTO
	 * @return
	 */
	public TLegalCurrencyCoinDTO getDTOBy(TLegalCurrencyCoinDTO tLegalCurrencyCoinDTO);
	
	/**
	 * 
	 * @param tLegalCurrencyCoinDTO
	 * @return
	 */
	public List<TLegalCurrencyCoinDTO> getsDTOBy(TLegalCurrencyCoinDTO tLegalCurrencyCoinDTO);
	
	/**
	 * 
	 * @param tLegalCurrencyCoinDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TLegalCurrencyCoinDTO> getsDTOBy(@Param(value="tLegalCurrencyCoin") TLegalCurrencyCoinDTO tLegalCurrencyCoinDTO, PageDTO<TLegalCurrencyCoinDTO> pageDTO);
	
}
