/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TAdvertOrderDTO;
import com.bittrade.pojo.vo.TAdvertOrderVO;
import com.bittrade.pojo.model.TAdvertOrder;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTAdvertOrderDAO extends IBaseDAO<TAdvertOrder, TAdvertOrderDTO, TAdvertOrderVO> {
	
	/**
	 * 
	 * @param tAdvertOrder
	 * @return
	 */
	public int add(TAdvertOrder tAdvertOrder);
	
	/**
	 * 
	 * @param tAdvertOrder
	 * @return
	 */
	public int addWithSelective(TAdvertOrder tAdvertOrder);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tAdvertOrder
	 * @return
	 */
	public int removeBy(TAdvertOrder tAdvertOrder);
	
	/**
	 * 
	 * @param tAdvertOrder
	 * @return
	 */
	public int modifyByPK(TAdvertOrder tAdvertOrder);
	
	/**
	 * 
	 * @param tAdvertOrder
	 * @return
	 */
	public int modifyWithSelectiveByPK(TAdvertOrder tAdvertOrder);
	
	/**
	 * 
	 * @param tAdvertOrder
	 * @param condiTAdvertOrder
	 * @return
	 */
	public int modifyBy(@Param(value="tAdvertOrder") TAdvertOrder tAdvertOrder, @Param(value="condiTAdvertOrder") TAdvertOrder condiTAdvertOrder);
	
	/**
	 * 
	 * @param tAdvertOrder
	 * @param condiTAdvertOrder
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tAdvertOrder") TAdvertOrder tAdvertOrder, @Param(value="condiTAdvertOrder") TAdvertOrder condiTAdvertOrder);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TAdvertOrder getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tAdvertOrder
	 * @return
	 */
	public TAdvertOrder getBy(TAdvertOrder tAdvertOrder);
	
	/**
	 * 
	 * @return
	 */
	public TAdvertOrder get();
	
	/**
	 * 
	 * @param tAdvertOrder
	 * @return
	 */
	public List<TAdvertOrder> getsBy(TAdvertOrder tAdvertOrder);
	
	/**
	 * 
	 * @return
	 */
	public List<TAdvertOrder> gets();
	
	/**
	 * 
	 * @param tAdvertOrder
	 * @return
	 */
	public int getCntBy(@Param(value="tAdvertOrder") TAdvertOrder tAdvertOrder);
	
	/**
	 * 
	 * @param tAdvertOrder
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TAdvertOrder> getsByPage(@Param(value="tAdvertOrder") TAdvertOrder tAdvertOrder, @Param(value="page") int page, @Param(value="size") int size);
	
}
