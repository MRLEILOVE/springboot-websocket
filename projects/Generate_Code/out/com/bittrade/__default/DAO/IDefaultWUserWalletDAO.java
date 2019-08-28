/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.WUserWalletDTO;
import com.bittrade.pojo.vo.WUserWalletVO;
import com.bittrade.pojo.model.WUserWallet;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultWUserWalletDAO extends IBaseDAO<WUserWallet, WUserWalletDTO, WUserWalletVO> {
	
	/**
	 * 
	 * @param wUserWallet
	 * @return
	 */
	public int add(WUserWallet wUserWallet);
	
	/**
	 * 
	 * @param wUserWallet
	 * @return
	 */
	public int addWithSelective(WUserWallet wUserWallet);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param wUserWallet
	 * @return
	 */
	public int removeBy(WUserWallet wUserWallet);
	
	/**
	 * 
	 * @param wUserWallet
	 * @return
	 */
	public int modifyByPK(WUserWallet wUserWallet);
	
	/**
	 * 
	 * @param wUserWallet
	 * @return
	 */
	public int modifyWithSelectiveByPK(WUserWallet wUserWallet);
	
	/**
	 * 
	 * @param wUserWallet
	 * @param condiWUserWallet
	 * @return
	 */
	public int modifyBy(@Param(value="wUserWallet") WUserWallet wUserWallet, @Param(value="condiWUserWallet") WUserWallet condiWUserWallet);
	
	/**
	 * 
	 * @param wUserWallet
	 * @param condiWUserWallet
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="wUserWallet") WUserWallet wUserWallet, @Param(value="condiWUserWallet") WUserWallet condiWUserWallet);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public WUserWallet getByPK(Serializable PK);
	
	/**
	 * 
	 * @param wUserWallet
	 * @return
	 */
	public WUserWallet getBy(WUserWallet wUserWallet);
	
	/**
	 * 
	 * @return
	 */
	public WUserWallet get();
	
	/**
	 * 
	 * @param wUserWallet
	 * @return
	 */
	public List<WUserWallet> getsBy(WUserWallet wUserWallet);
	
	/**
	 * 
	 * @return
	 */
	public List<WUserWallet> gets();
	
	/**
	 * 
	 * @param wUserWallet
	 * @return
	 */
	public int getCntBy(@Param(value="wUserWallet") WUserWallet wUserWallet);
	
	/**
	 * 
	 * @param wUserWallet
	 * @param page
	 * @param size
	 * @return
	 */
	public List<WUserWallet> getsByPage(@Param(value="wUserWallet") WUserWallet wUserWallet, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param wUserWalletDTO
	 * @return
	 */
	public WUserWalletDTO getDTOBy(WUserWalletDTO wUserWalletDTO);
	
	/**
	 * 
	 * @param wUserWalletDTO
	 * @return
	 */
	public List<WUserWalletDTO> getsDTOBy(WUserWalletDTO wUserWalletDTO);
	
	/**
	 * 
	 * @param wUserWalletDTO
	 * @param pageDTO
	 * @return
	 */
	public List<WUserWalletDTO> getsDTOBy(WUserWalletDTO wUserWalletDTO, PageDTO<WUserWalletDTO> pageDTO);
	
}
