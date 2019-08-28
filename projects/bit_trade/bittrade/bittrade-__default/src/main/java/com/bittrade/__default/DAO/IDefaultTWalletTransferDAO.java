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
import com.core.common.DTO.PageDTO;
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
	public int removeBy(TWalletTransfer tWalletTransfer);
	
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
	public int modifyBy(@Param(value="tWalletTransfer") TWalletTransfer tWalletTransfer, @Param(value="condiTWalletTransfer") TWalletTransfer condiTWalletTransfer);
	
	/**
	 * 
	 * @param tWalletTransfer
	 * @param condiTWalletTransfer
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tWalletTransfer") TWalletTransfer tWalletTransfer, @Param(value="condiTWalletTransfer") TWalletTransfer condiTWalletTransfer);
	
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
	public TWalletTransfer getBy(TWalletTransfer tWalletTransfer);
	
	/**
	 * 
	 * @return
	 */
	public TWalletTransfer get();
	
	/**
	 * 
	 * @param tWalletTransfer
	 * @return
	 */
	public List<TWalletTransfer> getsBy(TWalletTransfer tWalletTransfer);
	
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
	public int getCntBy(@Param(value="tWalletTransfer") TWalletTransfer tWalletTransfer);
	
	/**
	 * 
	 * @param tWalletTransfer
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TWalletTransfer> getsByPage(@Param(value="tWalletTransfer") TWalletTransfer tWalletTransfer, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tWalletTransferDTO
	 * @return
	 */
	public TWalletTransferDTO getDTOBy(TWalletTransferDTO tWalletTransferDTO);
	
	/**
	 * 
	 * @param tWalletTransferDTO
	 * @return
	 */
	public List<TWalletTransferDTO> getsDTOBy(TWalletTransferDTO tWalletTransferDTO);
	
	/**
	 * 
	 * @param tWalletTransferDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TWalletTransferDTO> getsDTOBy(TWalletTransferDTO tWalletTransferDTO, PageDTO<TWalletTransferDTO> pageDTO);
	
}
