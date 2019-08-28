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
import com.core.common.DTO.PageDTO;
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
	public int removeBy(TParamConfig tParamConfig);
	
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
	public int modifyBy(@Param(value="tParamConfig") TParamConfig tParamConfig, @Param(value="condiTParamConfig") TParamConfig condiTParamConfig);
	
	/**
	 * 
	 * @param tParamConfig
	 * @param condiTParamConfig
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tParamConfig") TParamConfig tParamConfig, @Param(value="condiTParamConfig") TParamConfig condiTParamConfig);
	
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
	public TParamConfig getBy(TParamConfig tParamConfig);
	
	/**
	 * 
	 * @return
	 */
	public TParamConfig get();
	
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
	public int getCntBy(@Param(value="tParamConfig") TParamConfig tParamConfig);
	
	/**
	 * 
	 * @param tParamConfig
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TParamConfig> getsByPage(@Param(value="tParamConfig") TParamConfig tParamConfig, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tParamConfigDTO
	 * @return
	 */
	public TParamConfigDTO getDTOBy(TParamConfigDTO tParamConfigDTO);
	
	/**
	 * 
	 * @param tParamConfigDTO
	 * @return
	 */
	public List<TParamConfigDTO> getsDTOBy(TParamConfigDTO tParamConfigDTO);
	
	/**
	 * 
	 * @param tParamConfigDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TParamConfigDTO> getsDTOBy(TParamConfigDTO tParamConfigDTO, PageDTO<TParamConfigDTO> pageDTO);
	
}
