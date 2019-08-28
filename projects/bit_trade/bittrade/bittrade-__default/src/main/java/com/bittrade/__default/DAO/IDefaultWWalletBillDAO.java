/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.WWalletBillDTO;
import com.bittrade.pojo.vo.WWalletBillVO;
import com.bittrade.pojo.model.WWalletBill;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultWWalletBillDAO extends IBaseDAO<WWalletBill, WWalletBillDTO, WWalletBillVO> {
	
	/**
	 * 
	 * @param wWalletBill
	 * @return
	 */
	public int add(WWalletBill wWalletBill);
	
	/**
	 * 
	 * @param wWalletBill
	 * @return
	 */
	public int addWithSelective(WWalletBill wWalletBill);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param wWalletBill
	 * @return
	 */
	public int removeBy(WWalletBill wWalletBill);
	
	/**
	 * 
	 * @param wWalletBill
	 * @return
	 */
	public int modifyByPK(WWalletBill wWalletBill);
	
	/**
	 * 
	 * @param wWalletBill
	 * @return
	 */
	public int modifyWithSelectiveByPK(WWalletBill wWalletBill);
	
	/**
	 * 
	 * @param wWalletBill
	 * @param condiWWalletBill
	 * @return
	 */
	public int modifyBy(@Param(value="wWalletBill") WWalletBill wWalletBill, @Param(value="condiWWalletBill") WWalletBill condiWWalletBill);
	
	/**
	 * 
	 * @param wWalletBill
	 * @param condiWWalletBill
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="wWalletBill") WWalletBill wWalletBill, @Param(value="condiWWalletBill") WWalletBill condiWWalletBill);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public WWalletBill getByPK(Serializable PK);
	
	/**
	 * 
	 * @param wWalletBill
	 * @return
	 */
	public WWalletBill getBy(WWalletBill wWalletBill);
	
	/**
	 * 
	 * @return
	 */
	public WWalletBill get();
	
	/**
	 * 
	 * @param wWalletBill
	 * @return
	 */
	public List<WWalletBill> getsBy(WWalletBill wWalletBill);
	
	/**
	 * 
	 * @return
	 */
	public List<WWalletBill> gets();
	
	/**
	 * 
	 * @param wWalletBill
	 * @return
	 */
	public int getCntBy(@Param(value="wWalletBill") WWalletBill wWalletBill);
	
	/**
	 * 
	 * @param wWalletBill
	 * @param page
	 * @param size
	 * @return
	 */
	public List<WWalletBill> getsByPage(@Param(value="wWalletBill") WWalletBill wWalletBill, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param wWalletBillDTO
	 * @return
	 */
	public WWalletBillDTO getDTOBy(WWalletBillDTO wWalletBillDTO);
	
	/**
	 * 
	 * @param wWalletBillDTO
	 * @return
	 */
	public List<WWalletBillDTO> getsDTOBy(WWalletBillDTO wWalletBillDTO);
	
	/**
	 * 
	 * @param wWalletBillDTO
	 * @param pageDTO
	 * @return
	 */
	public List<WWalletBillDTO> getsDTOBy(@Param(value="wWalletBill") WWalletBillDTO wWalletBillDTO, PageDTO<WWalletBillDTO> pageDTO);
	
}
