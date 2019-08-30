/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TUserDTO;
import com.bittrade.pojo.vo.TUserVO;
import com.bittrade.pojo.model.TUser;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTUserDAO extends IBaseDAO<TUser, TUserDTO, TUserVO> {
	
	/**
	 * 
	 * @param tUser
	 * @return
	 */
	public int add(TUser tUser);
	
	/**
	 * 
	 * @param tUser
	 * @return
	 */
	public int addWithSelective(TUser tUser);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tUser
	 * @return
	 */
	public int removeBy(TUser tUser);
	
	/**
	 * 
	 * @param tUser
	 * @return
	 */
	public int modifyByPK(TUser tUser);
	
	/**
	 * 
	 * @param tUser
	 * @return
	 */
	public int modifyWithSelectiveByPK(TUser tUser);
	
	/**
	 * 
	 * @param tUser
	 * @param condiTUser
	 * @return
	 */
	public int modifyBy(@Param(value="tUser") TUser tUser, @Param(value="condiTUser") TUser condiTUser);
	
	/**
	 * 
	 * @param tUser
	 * @param condiTUser
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tUser") TUser tUser, @Param(value="condiTUser") TUser condiTUser);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TUser getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tUser
	 * @return
	 */
	public TUser getBy(TUser tUser);
	
	/**
	 * 
	 * @return
	 */
	public TUser get();
	
	/**
	 * 
	 * @param tUser
	 * @return
	 */
	public List<TUser> getsBy(TUser tUser);
	
	/**
	 * 
	 * @return
	 */
	public List<TUser> gets();
	
	/**
	 * 
	 * @param tUser
	 * @return
	 */
	public int getCntBy(@Param(value="tUser") TUser tUser);
	
	/**
	 * 
	 * @param tUser
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TUser> getsByPage(@Param(value="tUser") TUser tUser, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tUserDTO
	 * @return
	 */
	public TUserDTO getDTOBy(TUserDTO tUserDTO);
	
	/**
	 * 
	 * @param tUserDTO
	 * @return
	 */
	public List<TUserDTO> getsDTOBy(TUserDTO tUserDTO);
	
	/**
	 * 
	 * @param tUserDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TUserDTO> getsDTOBy(TUserDTO tUserDTO, PageDTO<TUserDTO> pageDTO);
	
}
