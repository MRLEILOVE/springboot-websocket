/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.api.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.vo.TKlineVO;
import com.bittrade.pojo.model.TKline;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTKlineDAO extends IBaseDAO<TKline, TKlineDTO, TKlineVO> {
	
	/**
	 * 
	 * @param tKline
	 * @return
	 */
	public int add(TKline tKline);
	
	/**
	 * 
	 * @param tKline
	 * @return
	 */
	public int addWithSelective(TKline tKline);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tKline
	 * @return
	 */
	public int remove(TKline tKline);
	
	/**
	 * 
	 * @param tKline
	 * @return
	 */
	public int modifyByPK(TKline tKline);
	
	/**
	 * 
	 * @param tKline
	 * @return
	 */
	public int modifyWithSelectiveByPK(TKline tKline);
	
	/**
	 * 
	 * @param tKline
	 * @param condiTKline
	 * @return
	 */
	public int modify(@Param(value="tKline") TKline tKline, @Param(value="condiTKline") TKline condiTKline);
	
	/**
	 * 
	 * @param tKline
	 * @param condiTKline
	 * @return
	 */
	public int modifyWithSelective(@Param(value="tKline") TKline tKline, @Param(value="condiTKline") TKline condiTKline);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TKline getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tKline
	 * @return
	 */
	public List<TKlineDTO> get(TKline tKline);
	
	/**
	 * 
	 * @return
	 */
	public List<TKlineDTO> gets();
	
	/**
	 * 
	 * @param tKline
	 * @return
	 */
	public int getCntWithPage(@Param(value="tKline") TKline tKline);
	
	/**
	 * 
	 * @param tKline
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TKlineDTO> getsWithPage(@Param(value="tKline") TKline tKline, @Param(value="page") int page, @Param(value="size") int size);
	
}
