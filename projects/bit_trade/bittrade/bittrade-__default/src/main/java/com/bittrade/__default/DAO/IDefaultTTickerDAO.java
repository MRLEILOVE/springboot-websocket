/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TTickerDTO;
import com.bittrade.pojo.vo.TTickerVO;
import com.bittrade.pojo.model.TTicker;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTTickerDAO extends IBaseDAO<TTicker, TTickerDTO, TTickerVO> {
	
	/**
	 * 
	 * @param tTicker
	 * @return
	 */
	public int add(TTicker tTicker);
	
	/**
	 * 
	 * @param tTicker
	 * @return
	 */
	public int addWithSelective(TTicker tTicker);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tTicker
	 * @return
	 */
	public int remove(TTicker tTicker);
	
	/**
	 * 
	 * @param tTicker
	 * @return
	 */
	public int modifyByPK(TTicker tTicker);
	
	/**
	 * 
	 * @param tTicker
	 * @return
	 */
	public int modifyWithSelectiveByPK(TTicker tTicker);
	
	/**
	 * 
	 * @param tTicker
	 * @param condiTTicker
	 * @return
	 */
	public int modify(@Param(value="tTicker") TTicker tTicker, @Param(value="condiTTicker") TTicker condiTTicker);
	
	/**
	 * 
	 * @param tTicker
	 * @param condiTTicker
	 * @return
	 */
	public int modifyWithSelective(@Param(value="tTicker") TTicker tTicker, @Param(value="condiTTicker") TTicker condiTTicker);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TTicker getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tTicker
	 * @return
	 */
	public TTicker get(TTicker tTicker);
	
	/**
	 * 
	 * @param tTicker
	 * @return
	 */
	public List<TTicker> gets(TTicker tTicker);
	
	/**
	 * 
	 * @return
	 */
	public List<TTicker> gets();
	
	/**
	 * 
	 * @param tTicker
	 * @return
	 */
	public int getCntWithPage(@Param(value="tTicker") TTicker tTicker);
	
	/**
	 * 
	 * @param tTicker
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TTicker> getsWithPage(@Param(value="tTicker") TTicker tTicker, @Param(value="page") int page, @Param(value="size") int size);
	
}
