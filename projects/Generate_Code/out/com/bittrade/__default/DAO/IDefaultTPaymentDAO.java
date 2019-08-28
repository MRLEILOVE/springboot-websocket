/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TPaymentDTO;
import com.bittrade.pojo.vo.TPaymentVO;
import com.bittrade.pojo.model.TPayment;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTPaymentDAO extends IBaseDAO<TPayment, TPaymentDTO, TPaymentVO> {
	
	/**
	 * 
	 * @param tPayment
	 * @return
	 */
	public int add(TPayment tPayment);
	
	/**
	 * 
	 * @param tPayment
	 * @return
	 */
	public int addWithSelective(TPayment tPayment);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tPayment
	 * @return
	 */
	public int removeBy(TPayment tPayment);
	
	/**
	 * 
	 * @param tPayment
	 * @return
	 */
	public int modifyByPK(TPayment tPayment);
	
	/**
	 * 
	 * @param tPayment
	 * @return
	 */
	public int modifyWithSelectiveByPK(TPayment tPayment);
	
	/**
	 * 
	 * @param tPayment
	 * @param condiTPayment
	 * @return
	 */
	public int modifyBy(@Param(value="tPayment") TPayment tPayment, @Param(value="condiTPayment") TPayment condiTPayment);
	
	/**
	 * 
	 * @param tPayment
	 * @param condiTPayment
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tPayment") TPayment tPayment, @Param(value="condiTPayment") TPayment condiTPayment);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TPayment getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tPayment
	 * @return
	 */
	public TPayment getBy(TPayment tPayment);
	
	/**
	 * 
	 * @return
	 */
	public TPayment get();
	
	/**
	 * 
	 * @param tPayment
	 * @return
	 */
	public List<TPayment> getsBy(TPayment tPayment);
	
	/**
	 * 
	 * @return
	 */
	public List<TPayment> gets();
	
	/**
	 * 
	 * @param tPayment
	 * @return
	 */
	public int getCntBy(@Param(value="tPayment") TPayment tPayment);
	
	/**
	 * 
	 * @param tPayment
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TPayment> getsByPage(@Param(value="tPayment") TPayment tPayment, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tPaymentDTO
	 * @return
	 */
	public TPaymentDTO getDTOBy(TPaymentDTO tPaymentDTO);
	
	/**
	 * 
	 * @param tPaymentDTO
	 * @return
	 */
	public List<TPaymentDTO> getsDTOBy(TPaymentDTO tPaymentDTO);
	
	/**
	 * 
	 * @param tPaymentDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TPaymentDTO> getsDTOBy(TPaymentDTO tPaymentDTO, PageDTO<TPaymentDTO> pageDTO);
	
}
