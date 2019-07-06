/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.api.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.WWithdrawWalletBillDTO;
import com.bittrade.pojo.vo.WWithdrawWalletBillVO;
import com.bittrade.pojo.model.WWithdrawWalletBill;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultWWithdrawWalletBillDAO extends IBaseDAO<WWithdrawWalletBill, WWithdrawWalletBillDTO, WWithdrawWalletBillVO> {
	
	/**
	 * 
	 * @param wWithdrawWalletBill
	 * @return
	 */
	public int add(WWithdrawWalletBill wWithdrawWalletBill);
	
	/**
	 * 
	 * @param wWithdrawWalletBill
	 * @return
	 */
	public int addWithSelective(WWithdrawWalletBill wWithdrawWalletBill);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param wWithdrawWalletBill
	 * @return
	 */
	public int remove(WWithdrawWalletBill wWithdrawWalletBill);
	
	/**
	 * 
	 * @param wWithdrawWalletBill
	 * @return
	 */
	public int modifyByPK(WWithdrawWalletBill wWithdrawWalletBill);
	
	/**
	 * 
	 * @param wWithdrawWalletBill
	 * @return
	 */
	public int modifyWithSelectiveByPK(WWithdrawWalletBill wWithdrawWalletBill);
	
	/**
	 * 
	 * @param wWithdrawWalletBill
	 * @param condiWWithdrawWalletBill
	 * @return
	 */
	public int modify(@Param(value="wWithdrawWalletBill") WWithdrawWalletBill wWithdrawWalletBill, @Param(value="condiWWithdrawWalletBill") WWithdrawWalletBill condiWWithdrawWalletBill);
	
	/**
	 * 
	 * @param wWithdrawWalletBill
	 * @param condiWWithdrawWalletBill
	 * @return
	 */
	public int modifyWithSelective(@Param(value="wWithdrawWalletBill") WWithdrawWalletBill wWithdrawWalletBill, @Param(value="condiWWithdrawWalletBill") WWithdrawWalletBill condiWWithdrawWalletBill);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public WWithdrawWalletBill getByPK(Serializable PK);
	
	/**
	 * 
	 * @param wWithdrawWalletBill
	 * @return
	 */
	public List<WWithdrawWalletBillDTO> get(WWithdrawWalletBill wWithdrawWalletBill);
	
	/**
	 * 
	 * @return
	 */
	public List<WWithdrawWalletBillDTO> gets();
	
	/**
	 * 
	 * @param wWithdrawWalletBill
	 * @return
	 */
	public int getCntWithPage(@Param(value="wWithdrawWalletBill") WWithdrawWalletBill wWithdrawWalletBill);
	
	/**
	 * 
	 * @param wWithdrawWalletBill
	 * @param page
	 * @param size
	 * @return
	 */
	public List<WWithdrawWalletBillDTO> getsWithPage(@Param(value="wWithdrawWalletBill") WWithdrawWalletBill wWithdrawWalletBill, @Param(value="page") int page, @Param(value="size") int size);
	
}
