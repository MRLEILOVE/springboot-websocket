/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.vo.TWalletVO;
import com.bittrade.pojo.model.TWallet;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTWalletDAO extends IBaseDAO<TWallet, TWalletDTO, TWalletVO> {
	
	/**
	 * 
	 * @param tWallet
	 * @return
	 */
	public int add(TWallet tWallet);
	
	/**
	 * 
	 * @param tWallet
	 * @return
	 */
	public int addWithSelective(TWallet tWallet);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tWallet
	 * @return
	 */
	public int remove(TWallet tWallet);
	
	/**
	 * 
	 * @param tWallet
	 * @return
	 */
	public int modifyByPK(TWallet tWallet);
	
	/**
	 * 
	 * @param tWallet
	 * @return
	 */
	public int modifyWithSelectiveByPK(TWallet tWallet);
	
	/**
	 * 
	 * @param tWallet
	 * @param condiTWallet
	 * @return
	 */
	public int modify(@Param(value="tWallet") TWallet tWallet, @Param(value="condiTWallet") TWallet condiTWallet);
	
	/**
	 * 
	 * @param tWallet
	 * @param condiTWallet
	 * @return
	 */
	public int modifyWithSelective(@Param(value="tWallet") TWallet tWallet, @Param(value="condiTWallet") TWallet condiTWallet);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TWallet getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tWallet
	 * @return
	 */
	public List<TWalletDTO> get(TWallet tWallet);
	
	/**
	 * 
	 * @return
	 */
	public List<TWalletDTO> gets();
	
	/**
	 * 
	 * @param tWallet
	 * @return
	 */
	public int getCntWithPage(@Param(value="tWallet") TWallet tWallet);
	
	/**
	 * 
	 * @param tWallet
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TWalletDTO> getsWithPage(@Param(value="tWallet") TWallet tWallet, @Param(value="page") int page, @Param(value="size") int size);
	
}
