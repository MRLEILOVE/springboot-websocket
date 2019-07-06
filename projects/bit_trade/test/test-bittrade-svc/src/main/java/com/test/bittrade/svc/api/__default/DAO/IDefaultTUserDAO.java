/**
 * This code is generated automatically. Please do not edit it.
 */
package com.test.bittrade.svc.api.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.bittrade.svc.pojo.dto.TUserDTO;
import com.test.bittrade.svc.pojo.vo.TUserVO;
import com.test.bittrade.svc.pojo.model.TUser;
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
	public List<TUserDTO> get(TUser tUser);
	
	/**
	 * 
	 * @return
	 */
	public List<TUserDTO> gets();
	
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
	public List<TUserDTO> getsWithPage(@Param(value="tUser") TUser tUser, @Param(value="page") int page, @Param(value="size") int size);
	
}
