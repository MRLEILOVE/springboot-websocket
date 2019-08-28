/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTAdvertInfoDAO extends IBaseDAO<TAdvertInfo, TAdvertInfoDTO, TAdvertInfoVO> {
	
	/**
	 * 
	 * @param tAdvertInfo
	 * @return
	 */
	public int add(TAdvertInfo tAdvertInfo);
	
	/**
	 * 
	 * @param tAdvertInfo
	 * @return
	 */
	public int addWithSelective(TAdvertInfo tAdvertInfo);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tAdvertInfo
	 * @return
	 */
	public int removeBy(TAdvertInfo tAdvertInfo);
	
	/**
	 * 
	 * @param tAdvertInfo
	 * @return
	 */
	public int modifyByPK(TAdvertInfo tAdvertInfo);
	
	/**
	 * 
	 * @param tAdvertInfo
	 * @return
	 */
	public int modifyWithSelectiveByPK(TAdvertInfo tAdvertInfo);
	
	/**
	 * 
	 * @param tAdvertInfo
	 * @param condiTAdvertInfo
	 * @return
	 */
	public int modifyBy(@Param(value="tAdvertInfo") TAdvertInfo tAdvertInfo, @Param(value="condiTAdvertInfo") TAdvertInfo condiTAdvertInfo);
	
	/**
	 * 
	 * @param tAdvertInfo
	 * @param condiTAdvertInfo
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tAdvertInfo") TAdvertInfo tAdvertInfo, @Param(value="condiTAdvertInfo") TAdvertInfo condiTAdvertInfo);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TAdvertInfo getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tAdvertInfo
	 * @return
	 */
	public TAdvertInfo getBy(TAdvertInfo tAdvertInfo);
	
	/**
	 * 
	 * @return
	 */
	public TAdvertInfo get();
	
	/**
	 * 
	 * @param tAdvertInfo
	 * @return
	 */
	public List<TAdvertInfo> getsBy(TAdvertInfo tAdvertInfo);
	
	/**
	 * 
	 * @return
	 */
	public List<TAdvertInfo> gets();
	
	/**
	 * 
	 * @param tAdvertInfo
	 * @return
	 */
	public int getCntBy(@Param(value="tAdvertInfo") TAdvertInfo tAdvertInfo);
	
	/**
	 * 
	 * @param tAdvertInfo
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TAdvertInfo> getsByPage(@Param(value="tAdvertInfo") TAdvertInfo tAdvertInfo, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tAdvertInfoDTO
	 * @return
	 */
	public TAdvertInfoDTO getDTOBy(TAdvertInfoDTO tAdvertInfoDTO);
	
	/**
	 * 
	 * @param tAdvertInfoDTO
	 * @return
	 */
	public List<TAdvertInfoDTO> getsDTOBy(TAdvertInfoDTO tAdvertInfoDTO);
	
	/**
	 * 
	 * @param tAdvertInfoDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TAdvertInfoDTO> getsDTOBy(TAdvertInfoDTO tAdvertInfoDTO, PageDTO<TAdvertInfoDTO> pageDTO);
	
}
