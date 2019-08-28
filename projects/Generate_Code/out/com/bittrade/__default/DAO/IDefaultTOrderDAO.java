/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TOrderDTO;
import com.bittrade.pojo.vo.TOrderVO;
import com.bittrade.pojo.model.TOrder;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTOrderDAO extends IBaseDAO<TOrder, TOrderDTO, TOrderVO> {
	
	/**
	 * 
	 * @param tOrder
	 * @return
	 */
	public int add(TOrder tOrder);
	
	/**
	 * 
	 * @param tOrder
	 * @return
	 */
	public int addWithSelective(TOrder tOrder);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tOrder
	 * @return
	 */
	public int removeBy(TOrder tOrder);
	
	/**
	 * 
	 * @param tOrder
	 * @return
	 */
	public int modifyByPK(TOrder tOrder);
	
	/**
	 * 
	 * @param tOrder
	 * @return
	 */
	public int modifyWithSelectiveByPK(TOrder tOrder);
	
	/**
	 * 
	 * @param tOrder
	 * @param condiTOrder
	 * @return
	 */
	public int modifyBy(@Param(value="tOrder") TOrder tOrder, @Param(value="condiTOrder") TOrder condiTOrder);
	
	/**
	 * 
	 * @param tOrder
	 * @param condiTOrder
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tOrder") TOrder tOrder, @Param(value="condiTOrder") TOrder condiTOrder);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TOrder getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tOrder
	 * @return
	 */
	public TOrder getBy(TOrder tOrder);
	
	/**
	 * 
	 * @return
	 */
	public TOrder get();
	
	/**
	 * 
	 * @param tOrder
	 * @return
	 */
	public List<TOrder> getsBy(TOrder tOrder);
	
	/**
	 * 
	 * @return
	 */
	public List<TOrder> gets();
	
	/**
	 * 
	 * @param tOrder
	 * @return
	 */
	public int getCntBy(@Param(value="tOrder") TOrder tOrder);
	
	/**
	 * 
	 * @param tOrder
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TOrder> getsByPage(@Param(value="tOrder") TOrder tOrder, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tOrderDTO
	 * @return
	 */
	public TOrderDTO getDTOBy(TOrderDTO tOrderDTO);
	
	/**
	 * 
	 * @param tOrderDTO
	 * @return
	 */
	public List<TOrderDTO> getsDTOBy(TOrderDTO tOrderDTO);
	
	/**
	 * 
	 * @param tOrderDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TOrderDTO> getsDTOBy(@Param(value="tOrder") TOrderDTO tOrderDTO, PageDTO<TOrderDTO> pageDTO);
	
}
