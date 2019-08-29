/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TAcceptDealerDTO;
import com.bittrade.pojo.vo.TAcceptDealerVO;
import com.bittrade.pojo.model.TAcceptDealer;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTAcceptDealerDAO extends IBaseDAO<TAcceptDealer, TAcceptDealerDTO, TAcceptDealerVO> {
	
	/**
	 * 
	 * @param tAcceptDealer
	 * @return
	 */
	public int add(TAcceptDealer tAcceptDealer);
	
	/**
	 * 
	 * @param tAcceptDealer
	 * @return
	 */
	public int addWithSelective(TAcceptDealer tAcceptDealer);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tAcceptDealer
	 * @return
	 */
	public int removeBy(TAcceptDealer tAcceptDealer);
	
	/**
	 * 
	 * @param tAcceptDealer
	 * @return
	 */
	public int modifyByPK(TAcceptDealer tAcceptDealer);
	
	/**
	 * 
	 * @param tAcceptDealer
	 * @return
	 */
	public int modifyWithSelectiveByPK(TAcceptDealer tAcceptDealer);
	
	/**
	 * 
	 * @param tAcceptDealer
	 * @param condiTAcceptDealer
	 * @return
	 */
	public int modifyBy(@Param(value="tAcceptDealer") TAcceptDealer tAcceptDealer, @Param(value="condiTAcceptDealer") TAcceptDealer condiTAcceptDealer);
	
	/**
	 * 
	 * @param tAcceptDealer
	 * @param condiTAcceptDealer
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tAcceptDealer") TAcceptDealer tAcceptDealer, @Param(value="condiTAcceptDealer") TAcceptDealer condiTAcceptDealer);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TAcceptDealer getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tAcceptDealer
	 * @return
	 */
	public TAcceptDealer getBy(TAcceptDealer tAcceptDealer);
	
	/**
	 * 
	 * @return
	 */
	public TAcceptDealer get();
	
	/**
	 * 
	 * @param tAcceptDealer
	 * @return
	 */
	public List<TAcceptDealer> getsBy(TAcceptDealer tAcceptDealer);
	
	/**
	 * 
	 * @return
	 */
	public List<TAcceptDealer> gets();
	
	/**
	 * 
	 * @param tAcceptDealer
	 * @return
	 */
	public int getCntBy(@Param(value="tAcceptDealer") TAcceptDealer tAcceptDealer);
	
	/**
	 * 
	 * @param tAcceptDealer
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TAcceptDealer> getsByPage(@Param(value="tAcceptDealer") TAcceptDealer tAcceptDealer, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tAcceptDealerDTO
	 * @return
	 */
	public TAcceptDealerDTO getDTOBy(TAcceptDealerDTO tAcceptDealerDTO);
	
	/**
	 * 
	 * @param tAcceptDealerDTO
	 * @return
	 */
	public List<TAcceptDealerDTO> getsDTOBy(TAcceptDealerDTO tAcceptDealerDTO);
	
	/**
	 * 
	 * @param tAcceptDealerDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TAcceptDealerDTO> getsDTOBy(@Param(value="tAcceptDealer") TAcceptDealerDTO tAcceptDealerDTO, PageDTO<TAcceptDealerDTO> pageDTO);
	
}
