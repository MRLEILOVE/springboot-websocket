/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.WUserWalletBillDTO;
import com.bittrade.pojo.vo.WUserWalletBillVO;
import com.bittrade.pojo.model.WUserWalletBill;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultWUserWalletBillDAO extends IBaseDAO<WUserWalletBill, WUserWalletBillDTO, WUserWalletBillVO> {
	
	/**
	 * 
	 * @param wUserWalletBill
	 * @return
	 */
	public int add(WUserWalletBill wUserWalletBill);
	
	/**
	 * 
	 * @param wUserWalletBill
	 * @return
	 */
	public int addWithSelective(WUserWalletBill wUserWalletBill);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param wUserWalletBill
	 * @return
	 */
	public int removeBy(WUserWalletBill wUserWalletBill);
	
	/**
	 * 
	 * @param wUserWalletBill
	 * @return
	 */
	public int modifyByPK(WUserWalletBill wUserWalletBill);
	
	/**
	 * 
	 * @param wUserWalletBill
	 * @return
	 */
	public int modifyWithSelectiveByPK(WUserWalletBill wUserWalletBill);
	
	/**
	 * 
	 * @param wUserWalletBill
	 * @param condiWUserWalletBill
	 * @return
	 */
	public int modifyBy(@Param(value="wUserWalletBill") WUserWalletBill wUserWalletBill, @Param(value="condiWUserWalletBill") WUserWalletBill condiWUserWalletBill);
	
	/**
	 * 
	 * @param wUserWalletBill
	 * @param condiWUserWalletBill
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="wUserWalletBill") WUserWalletBill wUserWalletBill, @Param(value="condiWUserWalletBill") WUserWalletBill condiWUserWalletBill);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public WUserWalletBill getByPK(Serializable PK);
	
	/**
	 * 
	 * @param wUserWalletBill
	 * @return
	 */
	public WUserWalletBill getBy(WUserWalletBill wUserWalletBill);
	
	/**
	 * 
	 * @return
	 */
	public WUserWalletBill get();
	
	/**
	 * 
	 * @param wUserWalletBill
	 * @return
	 */
	public List<WUserWalletBill> getsBy(WUserWalletBill wUserWalletBill);
	
	/**
	 * 
	 * @return
	 */
	public List<WUserWalletBill> gets();
	
	/**
	 * 
	 * @param wUserWalletBill
	 * @return
	 */
	public int getCntBy(@Param(value="wUserWalletBill") WUserWalletBill wUserWalletBill);
	
	/**
	 * 
	 * @param wUserWalletBill
	 * @param page
	 * @param size
	 * @return
	 */
	public List<WUserWalletBill> getsByPage(@Param(value="wUserWalletBill") WUserWalletBill wUserWalletBill, @Param(value="page") int page, @Param(value="size") int size);
	
}
