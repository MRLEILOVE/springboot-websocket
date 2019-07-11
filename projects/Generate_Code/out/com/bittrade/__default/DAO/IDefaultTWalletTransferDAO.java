/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TWalletTransferDTO;
import com.bittrade.pojo.vo.TWalletTransferVO;
import com.bittrade.pojo.model.TWalletTransfer;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTWalletTransferDAO extends IBaseDAO<TWalletTransfer, TWalletTransferDTO, TWalletTransferVO> {
	
	/**
	 * 
	 * @param tWalletTransfer
	 * @return
	 */
	public int add(TWalletTransfer tWalletTransfer);
	
	/**
	 * 
	 * @param tWalletTransfer
	 * @return
	 */
	public int addWithSelective(TWalletTransfer tWalletTransfer);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tWalletTransfer
	 * @return
	 */
	public int remove(TWalletTransfer tWalletTransfer);
	
	/**
	 * 
	 * @param tWalletTransfer
	 * @return
	 */
	public int modifyByPK(TWalletTransfer tWalletTransfer);
	
	/**
	 * 
	 * @param tWalletTransfer
	 * @return
	 */
	public int modifyWithSelectiveByPK(TWalletTransfer tWalletTransfer);
	
	/**
	 * 
	 * @param tWalletTransfer
	 * @param condiTWalletTransfer
	 * @return
	 */
	public int modify(@Param(value="tWalletTransfer") TWalletTransfer tWalletTransfer, @Param(value="condiTWalletTransfer") TWalletTransfer condiTWalletTransfer);
	
	/**
	 * 
	 * @param tWalletTransfer
	 * @param condiTWalletTransfer
	 * @return
	 */
	public int modifyWithSelective(@Param(value="tWalletTransfer") TWalletTransfer tWalletTransfer, @Param(value="condiTWalletTransfer") TWalletTransfer condiTWalletTransfer);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TWalletTransfer getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tWalletTransfer
	 * @return
	 */
	public TWalletTransfer get(TWalletTransfer tWalletTransfer);
	
	/**
	 * 
	 * @param tWalletTransfer
	 * @return
	 */
	public List<TWalletTransfer> gets(TWalletTransfer tWalletTransfer);
	
	/**
	 * 
	 * @return
	 */
	public List<TWalletTransfer> gets();
	
	/**
	 * 
	 * @param tWalletTransfer
	 * @return
	 */
	public int getCntWithPage(@Param(value="tWalletTransfer") TWalletTransfer tWalletTransfer);
	
	/**
	 * 
	 * @param tWalletTransfer
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TWalletTransfer> getsWithPage(@Param(value="tWalletTransfer") TWalletTransfer tWalletTransfer, @Param(value="page") int page, @Param(value="size") int size);
	
}
