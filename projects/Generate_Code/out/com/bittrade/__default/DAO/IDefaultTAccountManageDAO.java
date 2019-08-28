/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TAccountManageDTO;
import com.bittrade.pojo.vo.TAccountManageVO;
import com.bittrade.pojo.model.TAccountManage;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTAccountManageDAO extends IBaseDAO<TAccountManage, TAccountManageDTO, TAccountManageVO> {
	
	/**
	 * 
	 * @param tAccountManage
	 * @return
	 */
	public int add(TAccountManage tAccountManage);
	
	/**
	 * 
	 * @param tAccountManage
	 * @return
	 */
	public int addWithSelective(TAccountManage tAccountManage);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tAccountManage
	 * @return
	 */
	public int removeBy(TAccountManage tAccountManage);
	
	/**
	 * 
	 * @param tAccountManage
	 * @return
	 */
	public int modifyByPK(TAccountManage tAccountManage);
	
	/**
	 * 
	 * @param tAccountManage
	 * @return
	 */
	public int modifyWithSelectiveByPK(TAccountManage tAccountManage);
	
	/**
	 * 
	 * @param tAccountManage
	 * @param condiTAccountManage
	 * @return
	 */
	public int modifyBy(@Param(value="tAccountManage") TAccountManage tAccountManage, @Param(value="condiTAccountManage") TAccountManage condiTAccountManage);
	
	/**
	 * 
	 * @param tAccountManage
	 * @param condiTAccountManage
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tAccountManage") TAccountManage tAccountManage, @Param(value="condiTAccountManage") TAccountManage condiTAccountManage);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TAccountManage getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tAccountManage
	 * @return
	 */
	public TAccountManage getBy(TAccountManage tAccountManage);
	
	/**
	 * 
	 * @return
	 */
	public TAccountManage get();
	
	/**
	 * 
	 * @param tAccountManage
	 * @return
	 */
	public List<TAccountManage> getsBy(TAccountManage tAccountManage);
	
	/**
	 * 
	 * @return
	 */
	public List<TAccountManage> gets();
	
	/**
	 * 
	 * @param tAccountManage
	 * @return
	 */
	public int getCntBy(@Param(value="tAccountManage") TAccountManage tAccountManage);
	
	/**
	 * 
	 * @param tAccountManage
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TAccountManage> getsByPage(@Param(value="tAccountManage") TAccountManage tAccountManage, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tAccountManageDTO
	 * @return
	 */
	public TAccountManageDTO getDTOBy(TAccountManageDTO tAccountManageDTO);
	
	/**
	 * 
	 * @param tAccountManageDTO
	 * @return
	 */
	public List<TAccountManageDTO> getsDTOBy(TAccountManageDTO tAccountManageDTO);
	
	/**
	 * 
	 * @param tAccountManageDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TAccountManageDTO> getsDTOBy(TAccountManageDTO tAccountManageDTO, PageDTO<TAccountManageDTO> pageDTO);
	
}
