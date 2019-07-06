/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.api.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.WConfigWalletDTO;
import com.bittrade.pojo.vo.WConfigWalletVO;
import com.bittrade.pojo.model.WConfigWallet;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultWConfigWalletDAO extends IBaseDAO<WConfigWallet, WConfigWalletDTO, WConfigWalletVO> {
	
	/**
	 * 
	 * @param wConfigWallet
	 * @return
	 */
	public int add(WConfigWallet wConfigWallet);
	
	/**
	 * 
	 * @param wConfigWallet
	 * @return
	 */
	public int addWithSelective(WConfigWallet wConfigWallet);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param wConfigWallet
	 * @return
	 */
	public int remove(WConfigWallet wConfigWallet);
	
	/**
	 * 
	 * @param wConfigWallet
	 * @return
	 */
	public int modifyByPK(WConfigWallet wConfigWallet);
	
	/**
	 * 
	 * @param wConfigWallet
	 * @return
	 */
	public int modifyWithSelectiveByPK(WConfigWallet wConfigWallet);
	
	/**
	 * 
	 * @param wConfigWallet
	 * @param condiWConfigWallet
	 * @return
	 */
	public int modify(@Param(value="wConfigWallet") WConfigWallet wConfigWallet, @Param(value="condiWConfigWallet") WConfigWallet condiWConfigWallet);
	
	/**
	 * 
	 * @param wConfigWallet
	 * @param condiWConfigWallet
	 * @return
	 */
	public int modifyWithSelective(@Param(value="wConfigWallet") WConfigWallet wConfigWallet, @Param(value="condiWConfigWallet") WConfigWallet condiWConfigWallet);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public WConfigWallet getByPK(Serializable PK);
	
	/**
	 * 
	 * @param wConfigWallet
	 * @return
	 */
	public List<WConfigWalletDTO> get(WConfigWallet wConfigWallet);
	
	/**
	 * 
	 * @return
	 */
	public List<WConfigWalletDTO> gets();
	
	/**
	 * 
	 * @param wConfigWallet
	 * @return
	 */
	public int getCntWithPage(@Param(value="wConfigWallet") WConfigWallet wConfigWallet);
	
	/**
	 * 
	 * @param wConfigWallet
	 * @param page
	 * @param size
	 * @return
	 */
	public List<WConfigWalletDTO> getsWithPage(@Param(value="wConfigWallet") WConfigWallet wConfigWallet, @Param(value="page") int page, @Param(value="size") int size);
	
}
