/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.WCoinConfigDTO;
import com.bittrade.pojo.vo.WCoinConfigVO;
import com.bittrade.pojo.model.WCoinConfig;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultWCoinConfigDAO extends IBaseDAO<WCoinConfig, WCoinConfigDTO, WCoinConfigVO> {
	
	/**
	 * 
	 * @param wCoinConfig
	 * @return
	 */
	public int add(WCoinConfig wCoinConfig);
	
	/**
	 * 
	 * @param wCoinConfig
	 * @return
	 */
	public int addWithSelective(WCoinConfig wCoinConfig);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param wCoinConfig
	 * @return
	 */
	public int remove(WCoinConfig wCoinConfig);
	
	/**
	 * 
	 * @param wCoinConfig
	 * @return
	 */
	public int modifyByPK(WCoinConfig wCoinConfig);
	
	/**
	 * 
	 * @param wCoinConfig
	 * @return
	 */
	public int modifyWithSelectiveByPK(WCoinConfig wCoinConfig);
	
	/**
	 * 
	 * @param wCoinConfig
	 * @param condiWCoinConfig
	 * @return
	 */
	public int modify(@Param(value="wCoinConfig") WCoinConfig wCoinConfig, @Param(value="condiWCoinConfig") WCoinConfig condiWCoinConfig);
	
	/**
	 * 
	 * @param wCoinConfig
	 * @param condiWCoinConfig
	 * @return
	 */
	public int modifyWithSelective(@Param(value="wCoinConfig") WCoinConfig wCoinConfig, @Param(value="condiWCoinConfig") WCoinConfig condiWCoinConfig);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public WCoinConfig getByPK(Serializable PK);
	
	/**
	 * 
	 * @param wCoinConfig
	 * @return
	 */
	public WCoinConfig get(WCoinConfig wCoinConfig);
	
	/**
	 * 
	 * @param wCoinConfig
	 * @return
	 */
	public List<WCoinConfig> getsBy(WCoinConfig wCoinConfig);
	
	/**
	 * 
	 * @return
	 */
	public List<WCoinConfig> gets();
	
	/**
	 * 
	 * @param wCoinConfig
	 * @return
	 */
	public int getCntWithPage(@Param(value="wCoinConfig") WCoinConfig wCoinConfig);
	
	/**
	 * 
	 * @param wCoinConfig
	 * @param page
	 * @param size
	 * @return
	 */
	public List<WCoinConfig> getsWithPage(@Param(value="wCoinConfig") WCoinConfig wCoinConfig, @Param(value="page") int page, @Param(value="size") int size);
	
}
