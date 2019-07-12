/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TParamConfigDTO;
import com.bittrade.pojo.vo.TParamConfigVO;
import com.bittrade.pojo.model.TParamConfig;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTParamConfigDAO extends IBaseDAO<TParamConfig, TParamConfigDTO, TParamConfigVO> {
	
	/**
	 * 
	 * @param tParamConfig
	 * @return
	 */
	public int add(TParamConfig tParamConfig);
	
	/**
	 * 
	 * @param tParamConfig
	 * @return
	 */
	public int addWithSelective(TParamConfig tParamConfig);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tParamConfig
	 * @return
	 */
	public int remove(TParamConfig tParamConfig);
	
	/**
	 * 
	 * @param tParamConfig
	 * @return
	 */
	public int modifyByPK(TParamConfig tParamConfig);
	
	/**
	 * 
	 * @param tParamConfig
	 * @return
	 */
	public int modifyWithSelectiveByPK(TParamConfig tParamConfig);
	
	/**
	 * 
	 * @param tParamConfig
	 * @param condiTParamConfig
	 * @return
	 */
	public int modify(@Param(value="tParamConfig") TParamConfig tParamConfig, @Param(value="condiTParamConfig") TParamConfig condiTParamConfig);
	
	/**
	 * 
	 * @param tParamConfig
	 * @param condiTParamConfig
	 * @return
	 */
	public int modifyWithSelective(@Param(value="tParamConfig") TParamConfig tParamConfig, @Param(value="condiTParamConfig") TParamConfig condiTParamConfig);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TParamConfig getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tParamConfig
	 * @return
	 */
	public TParamConfig get(TParamConfig tParamConfig);
	
	/**
	 * 
	 * @param tParamConfig
	 * @return
	 */
	public List<TParamConfig> getsBy(TParamConfig tParamConfig);
	
	/**
	 * 
	 * @return
	 */
	public List<TParamConfig> gets();
	
	/**
	 * 
	 * @param tParamConfig
	 * @return
	 */
	public int getCntWithPage(@Param(value="tParamConfig") TParamConfig tParamConfig);
	
	/**
	 * 
	 * @param tParamConfig
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TParamConfig> getsWithPage(@Param(value="tParamConfig") TParamConfig tParamConfig, @Param(value="page") int page, @Param(value="size") int size);
	
}
