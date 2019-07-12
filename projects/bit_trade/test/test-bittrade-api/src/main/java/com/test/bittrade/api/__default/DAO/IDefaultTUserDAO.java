/**
 * This code is generated automatically. Please do not edit it.
 */
package com.test.bittrade.api.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.bittrade.pojo.dto.TUserDTO;
import com.test.bittrade.pojo.vo.TUserVO;
import com.test.bittrade.pojo.model.TUser;
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
	public int remove(TUser tUser);
	
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
	public int modify(@Param(value="tUser") TUser tUser, @Param(value="condiTUser") TUser condiTUser);
	
	/**
	 * 
	 * @param tUser
	 * @param condiTUser
	 * @return
	 */
	public int modifyWithSelective(@Param(value="tUser") TUser tUser, @Param(value="condiTUser") TUser condiTUser);
	
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
	public TUser get(TUser tUser);
	
	/**
	 * 
	 * @param tUser
	 * @return
	 */
	public List<TUser> gets(TUser tUser);
	
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
	public int getCntWithPage(@Param(value="tUser") TUser tUser);
	
	/**
	 * 
	 * @param tUser
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TUser> getsWithPage(@Param(value="tUser") TUser tUser, @Param(value="page") int page, @Param(value="size") int size);
	
}
