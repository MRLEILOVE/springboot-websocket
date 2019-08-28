/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.SysDictTypeDTO;
import com.bittrade.pojo.vo.SysDictTypeVO;
import com.bittrade.pojo.model.SysDictType;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultSysDictTypeDAO extends IBaseDAO<SysDictType, SysDictTypeDTO, SysDictTypeVO> {
	
	/**
	 * 
	 * @param sysDictType
	 * @return
	 */
	public int add(SysDictType sysDictType);
	
	/**
	 * 
	 * @param sysDictType
	 * @return
	 */
	public int addWithSelective(SysDictType sysDictType);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysDictType
	 * @return
	 */
	public int removeBy(SysDictType sysDictType);
	
	/**
	 * 
	 * @param sysDictType
	 * @return
	 */
	public int modifyByPK(SysDictType sysDictType);
	
	/**
	 * 
	 * @param sysDictType
	 * @return
	 */
	public int modifyWithSelectiveByPK(SysDictType sysDictType);
	
	/**
	 * 
	 * @param sysDictType
	 * @param condiSysDictType
	 * @return
	 */
	public int modifyBy(@Param(value="sysDictType") SysDictType sysDictType, @Param(value="condiSysDictType") SysDictType condiSysDictType);
	
	/**
	 * 
	 * @param sysDictType
	 * @param condiSysDictType
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="sysDictType") SysDictType sysDictType, @Param(value="condiSysDictType") SysDictType condiSysDictType);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public SysDictType getByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysDictType
	 * @return
	 */
	public SysDictType getBy(SysDictType sysDictType);
	
	/**
	 * 
	 * @return
	 */
	public SysDictType get();
	
	/**
	 * 
	 * @param sysDictType
	 * @return
	 */
	public List<SysDictType> getsBy(SysDictType sysDictType);
	
	/**
	 * 
	 * @return
	 */
	public List<SysDictType> gets();
	
	/**
	 * 
	 * @param sysDictType
	 * @return
	 */
	public int getCntBy(@Param(value="sysDictType") SysDictType sysDictType);
	
	/**
	 * 
	 * @param sysDictType
	 * @param page
	 * @param size
	 * @return
	 */
	public List<SysDictType> getsByPage(@Param(value="sysDictType") SysDictType sysDictType, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param sysDictTypeDTO
	 * @return
	 */
	public SysDictTypeDTO getDTOBy(SysDictTypeDTO sysDictTypeDTO);
	
	/**
	 * 
	 * @param sysDictTypeDTO
	 * @return
	 */
	public List<SysDictTypeDTO> getsDTOBy(SysDictTypeDTO sysDictTypeDTO);
	
	/**
	 * 
	 * @param sysDictTypeDTO
	 * @param pageDTO
	 * @return
	 */
	public List<SysDictTypeDTO> getsDTOBy(SysDictTypeDTO sysDictTypeDTO, PageDTO<SysDictTypeDTO> pageDTO);
	
}
