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
	public int remove(TOrder tOrder);
	
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
	public int modify(@Param(value="tOrder") TOrder tOrder, @Param(value="condiTOrder") TOrder condiTOrder);
	
	/**
	 * 
	 * @param tOrder
	 * @param condiTOrder
	 * @return
	 */
	public int modifyWithSelective(@Param(value="tOrder") TOrder tOrder, @Param(value="condiTOrder") TOrder condiTOrder);
	
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
	public TOrder get(TOrder tOrder);
	
	/**
	 * 
	 * @param tOrder
	 * @return
	 */
	public List<TOrder> gets(TOrder tOrder);
	
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
	public int getCntWithPage(@Param(value="tOrder") TOrder tOrder);
	
	/**
	 * 
	 * @param tOrder
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TOrder> getsWithPage(@Param(value="tOrder") TOrder tOrder, @Param(value="page") int page, @Param(value="size") int size);
	
}
