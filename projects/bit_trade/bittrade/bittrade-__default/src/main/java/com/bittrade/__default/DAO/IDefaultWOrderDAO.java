/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.WOrderDTO;
import com.bittrade.pojo.vo.WOrderVO;
import com.bittrade.pojo.model.WOrder;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultWOrderDAO extends IBaseDAO<WOrder, WOrderDTO, WOrderVO> {
	
	/**
	 * 
	 * @param wOrder
	 * @return
	 */
	public int add(WOrder wOrder);
	
	/**
	 * 
	 * @param wOrder
	 * @return
	 */
	public int addWithSelective(WOrder wOrder);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param wOrder
	 * @return
	 */
	public int removeBy(WOrder wOrder);
	
	/**
	 * 
	 * @param wOrder
	 * @return
	 */
	public int modifyByPK(WOrder wOrder);
	
	/**
	 * 
	 * @param wOrder
	 * @return
	 */
	public int modifyWithSelectiveByPK(WOrder wOrder);
	
	/**
	 * 
	 * @param wOrder
	 * @param condiWOrder
	 * @return
	 */
	public int modifyBy(@Param(value="wOrder") WOrder wOrder, @Param(value="condiWOrder") WOrder condiWOrder);
	
	/**
	 * 
	 * @param wOrder
	 * @param condiWOrder
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="wOrder") WOrder wOrder, @Param(value="condiWOrder") WOrder condiWOrder);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public WOrder getByPK(Serializable PK);
	
	/**
	 * 
	 * @param wOrder
	 * @return
	 */
	public WOrder getBy(WOrder wOrder);
	
	/**
	 * 
	 * @return
	 */
	public WOrder get();
	
	/**
	 * 
	 * @param wOrder
	 * @return
	 */
	public List<WOrder> getsBy(WOrder wOrder);
	
	/**
	 * 
	 * @return
	 */
	public List<WOrder> gets();
	
	/**
	 * 
	 * @param wOrder
	 * @return
	 */
	public int getCntBy(@Param(value="wOrder") WOrder wOrder);
	
	/**
	 * 
	 * @param wOrder
	 * @param page
	 * @param size
	 * @return
	 */
	public List<WOrder> getsByPage(@Param(value="wOrder") WOrder wOrder, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param wOrderDTO
	 * @return
	 */
	public WOrderDTO getDTOBy(WOrderDTO wOrderDTO);
	
	/**
	 * 
	 * @param wOrderDTO
	 * @return
	 */
	public List<WOrderDTO> getsDTOBy(WOrderDTO wOrderDTO);
	
	/**
	 * 
	 * @param wOrderDTO
	 * @param pageDTO
	 * @return
	 */
	public List<WOrderDTO> getsDTOBy(WOrderDTO wOrderDTO, PageDTO<WOrderDTO> pageDTO);
	
}
