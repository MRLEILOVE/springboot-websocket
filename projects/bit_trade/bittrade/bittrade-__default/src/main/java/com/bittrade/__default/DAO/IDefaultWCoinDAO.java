/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.WCoinDTO;
import com.bittrade.pojo.vo.WCoinVO;
import com.bittrade.pojo.model.WCoin;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultWCoinDAO extends IBaseDAO<WCoin, WCoinDTO, WCoinVO> {
	
	/**
	 * 
	 * @param wCoin
	 * @return
	 */
	public int add(WCoin wCoin);
	
	/**
	 * 
	 * @param wCoin
	 * @return
	 */
	public int addWithSelective(WCoin wCoin);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param wCoin
	 * @return
	 */
	public int removeBy(WCoin wCoin);
	
	/**
	 * 
	 * @param wCoin
	 * @return
	 */
	public int modifyByPK(WCoin wCoin);
	
	/**
	 * 
	 * @param wCoin
	 * @return
	 */
	public int modifyWithSelectiveByPK(WCoin wCoin);
	
	/**
	 * 
	 * @param wCoin
	 * @param condiWCoin
	 * @return
	 */
	public int modifyBy(@Param(value="wCoin") WCoin wCoin, @Param(value="condiWCoin") WCoin condiWCoin);
	
	/**
	 * 
	 * @param wCoin
	 * @param condiWCoin
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="wCoin") WCoin wCoin, @Param(value="condiWCoin") WCoin condiWCoin);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public WCoin getByPK(Serializable PK);
	
	/**
	 * 
	 * @param wCoin
	 * @return
	 */
	public WCoin getBy(WCoin wCoin);
	
	/**
	 * 
	 * @return
	 */
	public WCoin get();
	
	/**
	 * 
	 * @param wCoin
	 * @return
	 */
	public List<WCoin> getsBy(WCoin wCoin);
	
	/**
	 * 
	 * @return
	 */
	public List<WCoin> gets();
	
	/**
	 * 
	 * @param wCoin
	 * @return
	 */
	public int getCntBy(@Param(value="wCoin") WCoin wCoin);
	
	/**
	 * 
	 * @param wCoin
	 * @param page
	 * @param size
	 * @return
	 */
	public List<WCoin> getsByPage(@Param(value="wCoin") WCoin wCoin, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param wCoinDTO
	 * @return
	 */
	public WCoinDTO getDTOBy(WCoinDTO wCoinDTO);
	
	/**
	 * 
	 * @param wCoinDTO
	 * @return
	 */
	public List<WCoinDTO> getsDTOBy(WCoinDTO wCoinDTO);
	
	/**
	 * 
	 * @param wCoinDTO
	 * @param pageDTO
	 * @return
	 */
	public List<WCoinDTO> getsDTOBy(@Param(value="wCoin") WCoinDTO wCoinDTO, PageDTO<WCoinDTO> pageDTO);
	
}
