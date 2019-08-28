/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TPaymentModeDTO;
import com.bittrade.pojo.vo.TPaymentModeVO;
import com.bittrade.pojo.model.TPaymentMode;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTPaymentModeDAO extends IBaseDAO<TPaymentMode, TPaymentModeDTO, TPaymentModeVO> {
	
	/**
	 * 
	 * @param tPaymentMode
	 * @return
	 */
	public int add(TPaymentMode tPaymentMode);
	
	/**
	 * 
	 * @param tPaymentMode
	 * @return
	 */
	public int addWithSelective(TPaymentMode tPaymentMode);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tPaymentMode
	 * @return
	 */
	public int removeBy(TPaymentMode tPaymentMode);
	
	/**
	 * 
	 * @param tPaymentMode
	 * @return
	 */
	public int modifyByPK(TPaymentMode tPaymentMode);
	
	/**
	 * 
	 * @param tPaymentMode
	 * @return
	 */
	public int modifyWithSelectiveByPK(TPaymentMode tPaymentMode);
	
	/**
	 * 
	 * @param tPaymentMode
	 * @param condiTPaymentMode
	 * @return
	 */
	public int modifyBy(@Param(value="tPaymentMode") TPaymentMode tPaymentMode, @Param(value="condiTPaymentMode") TPaymentMode condiTPaymentMode);
	
	/**
	 * 
	 * @param tPaymentMode
	 * @param condiTPaymentMode
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tPaymentMode") TPaymentMode tPaymentMode, @Param(value="condiTPaymentMode") TPaymentMode condiTPaymentMode);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TPaymentMode getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tPaymentMode
	 * @return
	 */
	public TPaymentMode getBy(TPaymentMode tPaymentMode);
	
	/**
	 * 
	 * @return
	 */
	public TPaymentMode get();
	
	/**
	 * 
	 * @param tPaymentMode
	 * @return
	 */
	public List<TPaymentMode> getsBy(TPaymentMode tPaymentMode);
	
	/**
	 * 
	 * @return
	 */
	public List<TPaymentMode> gets();
	
	/**
	 * 
	 * @param tPaymentMode
	 * @return
	 */
	public int getCntBy(@Param(value="tPaymentMode") TPaymentMode tPaymentMode);
	
	/**
	 * 
	 * @param tPaymentMode
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TPaymentMode> getsByPage(@Param(value="tPaymentMode") TPaymentMode tPaymentMode, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tPaymentModeDTO
	 * @return
	 */
	public TPaymentModeDTO getDTOBy(TPaymentModeDTO tPaymentModeDTO);
	
	/**
	 * 
	 * @param tPaymentModeDTO
	 * @return
	 */
	public List<TPaymentModeDTO> getsDTOBy(TPaymentModeDTO tPaymentModeDTO);
	
	/**
	 * 
	 * @param tPaymentModeDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TPaymentModeDTO> getsDTOBy(@Param(value="tPaymentMode") TPaymentModeDTO tPaymentModeDTO, PageDTO<TPaymentModeDTO> pageDTO);
	
}
