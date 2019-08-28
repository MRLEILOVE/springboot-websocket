/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TLegalCurrencyAccountDTO;
import com.bittrade.pojo.vo.TLegalCurrencyAccountVO;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTLegalCurrencyAccountDAO extends IBaseDAO<TLegalCurrencyAccount, TLegalCurrencyAccountDTO, TLegalCurrencyAccountVO> {
	
	/**
	 * 
	 * @param tLegalCurrencyAccount
	 * @return
	 */
	public int add(TLegalCurrencyAccount tLegalCurrencyAccount);
	
	/**
	 * 
	 * @param tLegalCurrencyAccount
	 * @return
	 */
	public int addWithSelective(TLegalCurrencyAccount tLegalCurrencyAccount);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tLegalCurrencyAccount
	 * @return
	 */
	public int removeBy(TLegalCurrencyAccount tLegalCurrencyAccount);
	
	/**
	 * 
	 * @param tLegalCurrencyAccount
	 * @return
	 */
	public int modifyByPK(TLegalCurrencyAccount tLegalCurrencyAccount);
	
	/**
	 * 
	 * @param tLegalCurrencyAccount
	 * @return
	 */
	public int modifyWithSelectiveByPK(TLegalCurrencyAccount tLegalCurrencyAccount);
	
	/**
	 * 
	 * @param tLegalCurrencyAccount
	 * @param condiTLegalCurrencyAccount
	 * @return
	 */
	public int modifyBy(@Param(value="tLegalCurrencyAccount") TLegalCurrencyAccount tLegalCurrencyAccount, @Param(value="condiTLegalCurrencyAccount") TLegalCurrencyAccount condiTLegalCurrencyAccount);
	
	/**
	 * 
	 * @param tLegalCurrencyAccount
	 * @param condiTLegalCurrencyAccount
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tLegalCurrencyAccount") TLegalCurrencyAccount tLegalCurrencyAccount, @Param(value="condiTLegalCurrencyAccount") TLegalCurrencyAccount condiTLegalCurrencyAccount);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TLegalCurrencyAccount getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tLegalCurrencyAccount
	 * @return
	 */
	public TLegalCurrencyAccount getBy(TLegalCurrencyAccount tLegalCurrencyAccount);
	
	/**
	 * 
	 * @return
	 */
	public TLegalCurrencyAccount get();
	
	/**
	 * 
	 * @param tLegalCurrencyAccount
	 * @return
	 */
	public List<TLegalCurrencyAccount> getsBy(TLegalCurrencyAccount tLegalCurrencyAccount);
	
	/**
	 * 
	 * @return
	 */
	public List<TLegalCurrencyAccount> gets();
	
	/**
	 * 
	 * @param tLegalCurrencyAccount
	 * @return
	 */
	public int getCntBy(@Param(value="tLegalCurrencyAccount") TLegalCurrencyAccount tLegalCurrencyAccount);
	
	/**
	 * 
	 * @param tLegalCurrencyAccount
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TLegalCurrencyAccount> getsByPage(@Param(value="tLegalCurrencyAccount") TLegalCurrencyAccount tLegalCurrencyAccount, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tLegalCurrencyAccountDTO
	 * @return
	 */
	public TLegalCurrencyAccountDTO getDTOBy(TLegalCurrencyAccountDTO tLegalCurrencyAccountDTO);
	
	/**
	 * 
	 * @param tLegalCurrencyAccountDTO
	 * @return
	 */
	public List<TLegalCurrencyAccountDTO> getsDTOBy(TLegalCurrencyAccountDTO tLegalCurrencyAccountDTO);
	
	/**
	 * 
	 * @param tLegalCurrencyAccountDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TLegalCurrencyAccountDTO> getsDTOBy(TLegalCurrencyAccountDTO tLegalCurrencyAccountDTO, PageDTO<TLegalCurrencyAccountDTO> pageDTO);
	
}
