/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TCurrencyTradeDTO;
import com.bittrade.pojo.vo.TCurrencyTradeVO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTCurrencyTradeDAO extends IBaseDAO<TCurrencyTrade, TCurrencyTradeDTO, TCurrencyTradeVO> {
	
	/**
	 * 
	 * @param tCurrencyTrade
	 * @return
	 */
	public int add(TCurrencyTrade tCurrencyTrade);
	
	/**
	 * 
	 * @param tCurrencyTrade
	 * @return
	 */
	public int addWithSelective(TCurrencyTrade tCurrencyTrade);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tCurrencyTrade
	 * @return
	 */
	public int removeBy(TCurrencyTrade tCurrencyTrade);
	
	/**
	 * 
	 * @param tCurrencyTrade
	 * @return
	 */
	public int modifyByPK(TCurrencyTrade tCurrencyTrade);
	
	/**
	 * 
	 * @param tCurrencyTrade
	 * @return
	 */
	public int modifyWithSelectiveByPK(TCurrencyTrade tCurrencyTrade);
	
	/**
	 * 
	 * @param tCurrencyTrade
	 * @param condiTCurrencyTrade
	 * @return
	 */
	public int modifyBy(@Param(value="tCurrencyTrade") TCurrencyTrade tCurrencyTrade, @Param(value="condiTCurrencyTrade") TCurrencyTrade condiTCurrencyTrade);
	
	/**
	 * 
	 * @param tCurrencyTrade
	 * @param condiTCurrencyTrade
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tCurrencyTrade") TCurrencyTrade tCurrencyTrade, @Param(value="condiTCurrencyTrade") TCurrencyTrade condiTCurrencyTrade);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TCurrencyTrade getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tCurrencyTrade
	 * @return
	 */
	public TCurrencyTrade getBy(TCurrencyTrade tCurrencyTrade);
	
	/**
	 * 
	 * @return
	 */
	public TCurrencyTrade get();
	
	/**
	 * 
	 * @param tCurrencyTrade
	 * @return
	 */
	public List<TCurrencyTrade> getsBy(TCurrencyTrade tCurrencyTrade);
	
	/**
	 * 
	 * @return
	 */
	public List<TCurrencyTrade> gets();
	
	/**
	 * 
	 * @param tCurrencyTrade
	 * @return
	 */
	public int getCntBy(@Param(value="tCurrencyTrade") TCurrencyTrade tCurrencyTrade);
	
	/**
	 * 
	 * @param tCurrencyTrade
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TCurrencyTrade> getsByPage(@Param(value="tCurrencyTrade") TCurrencyTrade tCurrencyTrade, @Param(value="page") int page, @Param(value="size") int size);
	
}
