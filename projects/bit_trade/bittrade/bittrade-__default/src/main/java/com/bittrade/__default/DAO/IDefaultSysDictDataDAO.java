/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.SysDictDataDTO;
import com.bittrade.pojo.vo.SysDictDataVO;
import com.bittrade.pojo.model.SysDictData;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultSysDictDataDAO extends IBaseDAO<SysDictData, SysDictDataDTO, SysDictDataVO> {
	
	/**
	 * 
	 * @param sysDictData
	 * @return
	 */
	public int add(SysDictData sysDictData);
	
	/**
	 * 
	 * @param sysDictData
	 * @return
	 */
	public int addWithSelective(SysDictData sysDictData);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysDictData
	 * @return
	 */
	public int removeBy(SysDictData sysDictData);
	
	/**
	 * 
	 * @param sysDictData
	 * @return
	 */
	public int modifyByPK(SysDictData sysDictData);
	
	/**
	 * 
	 * @param sysDictData
	 * @return
	 */
	public int modifyWithSelectiveByPK(SysDictData sysDictData);
	
	/**
	 * 
	 * @param sysDictData
	 * @param condiSysDictData
	 * @return
	 */
	public int modifyBy(@Param(value="sysDictData") SysDictData sysDictData, @Param(value="condiSysDictData") SysDictData condiSysDictData);
	
	/**
	 * 
	 * @param sysDictData
	 * @param condiSysDictData
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="sysDictData") SysDictData sysDictData, @Param(value="condiSysDictData") SysDictData condiSysDictData);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public SysDictData getByPK(Serializable PK);
	
	/**
	 * 
	 * @param sysDictData
	 * @return
	 */
	public SysDictData getBy(SysDictData sysDictData);
	
	/**
	 * 
	 * @return
	 */
	public SysDictData get();
	
	/**
	 * 
	 * @param sysDictData
	 * @return
	 */
	public List<SysDictData> getsBy(SysDictData sysDictData);
	
	/**
	 * 
	 * @return
	 */
	public List<SysDictData> gets();
	
	/**
	 * 
	 * @param sysDictData
	 * @return
	 */
	public int getCntBy(@Param(value="sysDictData") SysDictData sysDictData);
	
	/**
	 * 
	 * @param sysDictData
	 * @param page
	 * @param size
	 * @return
	 */
	public List<SysDictData> getsByPage(@Param(value="sysDictData") SysDictData sysDictData, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param sysDictDataDTO
	 * @return
	 */
	public SysDictDataDTO getDTOBy(SysDictDataDTO sysDictDataDTO);
	
	/**
	 * 
	 * @param sysDictDataDTO
	 * @return
	 */
	public List<SysDictDataDTO> getsDTOBy(SysDictDataDTO sysDictDataDTO);
	
	/**
	 * 
	 * @param sysDictDataDTO
	 * @param pageDTO
	 * @return
	 */
	public List<SysDictDataDTO> getsDTOBy(SysDictDataDTO sysDictDataDTO, PageDTO<SysDictDataDTO> pageDTO);
	
}
