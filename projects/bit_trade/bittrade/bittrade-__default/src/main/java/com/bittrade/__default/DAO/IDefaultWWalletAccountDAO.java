/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.WWalletAccountDTO;
import com.bittrade.pojo.vo.WWalletAccountVO;
import com.bittrade.pojo.model.WWalletAccount;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultWWalletAccountDAO extends IBaseDAO<WWalletAccount, WWalletAccountDTO, WWalletAccountVO> {
	
	/**
	 * 
	 * @param wWalletAccount
	 * @return
	 */
	public int add(WWalletAccount wWalletAccount);
	
	/**
	 * 
	 * @param wWalletAccount
	 * @return
	 */
	public int addWithSelective(WWalletAccount wWalletAccount);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param wWalletAccount
	 * @return
	 */
	public int removeBy(WWalletAccount wWalletAccount);
	
	/**
	 * 
	 * @param wWalletAccount
	 * @return
	 */
	public int modifyByPK(WWalletAccount wWalletAccount);
	
	/**
	 * 
	 * @param wWalletAccount
	 * @return
	 */
	public int modifyWithSelectiveByPK(WWalletAccount wWalletAccount);
	
	/**
	 * 
	 * @param wWalletAccount
	 * @param condiWWalletAccount
	 * @return
	 */
	public int modifyBy(@Param(value="wWalletAccount") WWalletAccount wWalletAccount, @Param(value="condiWWalletAccount") WWalletAccount condiWWalletAccount);
	
	/**
	 * 
	 * @param wWalletAccount
	 * @param condiWWalletAccount
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="wWalletAccount") WWalletAccount wWalletAccount, @Param(value="condiWWalletAccount") WWalletAccount condiWWalletAccount);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public WWalletAccount getByPK(Serializable PK);
	
	/**
	 * 
	 * @param wWalletAccount
	 * @return
	 */
	public WWalletAccount getBy(WWalletAccount wWalletAccount);
	
	/**
	 * 
	 * @return
	 */
	public WWalletAccount get();
	
	/**
	 * 
	 * @param wWalletAccount
	 * @return
	 */
	public List<WWalletAccount> getsBy(WWalletAccount wWalletAccount);
	
	/**
	 * 
	 * @return
	 */
	public List<WWalletAccount> gets();
	
	/**
	 * 
	 * @param wWalletAccount
	 * @return
	 */
	public int getCntBy(@Param(value="wWalletAccount") WWalletAccount wWalletAccount);
	
	/**
	 * 
	 * @param wWalletAccount
	 * @param page
	 * @param size
	 * @return
	 */
	public List<WWalletAccount> getsByPage(@Param(value="wWalletAccount") WWalletAccount wWalletAccount, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param wWalletAccountDTO
	 * @return
	 */
	public WWalletAccountDTO getDTOBy(WWalletAccountDTO wWalletAccountDTO);
	
	/**
	 * 
	 * @param wWalletAccountDTO
	 * @return
	 */
	public List<WWalletAccountDTO> getsDTOBy(WWalletAccountDTO wWalletAccountDTO);
	
	/**
	 * 
	 * @param wWalletAccountDTO
	 * @param pageDTO
	 * @return
	 */
	public List<WWalletAccountDTO> getsDTOBy(WWalletAccountDTO wWalletAccountDTO, PageDTO<WWalletAccountDTO> pageDTO);
	
}
