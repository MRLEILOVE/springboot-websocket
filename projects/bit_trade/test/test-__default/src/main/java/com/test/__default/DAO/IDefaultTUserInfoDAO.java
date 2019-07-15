/**
 * This code is generated automatically. Please do not edit it.
 */
package com.test.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.pojo.dto.TUserInfoDTO;
import com.test.pojo.vo.TUserInfoVO;
import com.test.pojo.model.TUserInfo;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTUserInfoDAO extends IBaseDAO<TUserInfo, TUserInfoDTO, TUserInfoVO> {
	
	/**
	 * 
	 * @param tUserInfo
	 * @return
	 */
	public int add(TUserInfo tUserInfo);
	
	/**
	 * 
	 * @param tUserInfo
	 * @return
	 */
	public int addWithSelective(TUserInfo tUserInfo);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tUserInfo
	 * @return
	 */
	public int removeBy(TUserInfo tUserInfo);
	
	/**
	 * 
	 * @param tUserInfo
	 * @return
	 */
	public int modifyByPK(TUserInfo tUserInfo);
	
	/**
	 * 
	 * @param tUserInfo
	 * @return
	 */
	public int modifyWithSelectiveByPK(TUserInfo tUserInfo);
	
	/**
	 * 
	 * @param tUserInfo
	 * @param condiTUserInfo
	 * @return
	 */
	public int modifyBy(@Param(value="tUserInfo") TUserInfo tUserInfo, @Param(value="condiTUserInfo") TUserInfo condiTUserInfo);
	
	/**
	 * 
	 * @param tUserInfo
	 * @param condiTUserInfo
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tUserInfo") TUserInfo tUserInfo, @Param(value="condiTUserInfo") TUserInfo condiTUserInfo);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TUserInfo getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tUserInfo
	 * @return
	 */
	public TUserInfo getBy(TUserInfo tUserInfo);
	
	/**
	 * 
	 * @return
	 */
	public TUserInfo get();
	
	/**
	 * 
	 * @param tUserInfo
	 * @return
	 */
	public List<TUserInfo> getsBy(TUserInfo tUserInfo);
	
	/**
	 * 
	 * @return
	 */
	public List<TUserInfo> gets();
	
	/**
	 * 
	 * @param tUserInfo
	 * @return
	 */
	public int getCntBy(@Param(value="tUserInfo") TUserInfo tUserInfo);
	
	/**
	 * 
	 * @param tUserInfo
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TUserInfo> getsByPage(@Param(value="tUserInfo") TUserInfo tUserInfo, @Param(value="page") int page, @Param(value="size") int size);
	
}
