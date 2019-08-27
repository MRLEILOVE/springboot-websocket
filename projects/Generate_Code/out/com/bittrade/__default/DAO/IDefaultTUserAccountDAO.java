/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TUserAccountDTO;
import com.bittrade.pojo.vo.TUserAccountVO;
import com.bittrade.pojo.model.TUserAccount;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTUserAccountDAO extends IBaseDAO<TUserAccount, TUserAccountDTO, TUserAccountVO> {
	
	/**
	 * 
	 * @param tUserAccount
	 * @return
	 */
	public int add(TUserAccount tUserAccount);
	
	/**
	 * 
	 * @param tUserAccount
	 * @return
	 */
	public int addWithSelective(TUserAccount tUserAccount);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tUserAccount
	 * @return
	 */
	public int removeBy(TUserAccount tUserAccount);
	
	/**
	 * 
	 * @param tUserAccount
	 * @return
	 */
	public int modifyByPK(TUserAccount tUserAccount);
	
	/**
	 * 
	 * @param tUserAccount
	 * @return
	 */
	public int modifyWithSelectiveByPK(TUserAccount tUserAccount);
	
	/**
	 * 
	 * @param tUserAccount
	 * @param condiTUserAccount
	 * @return
	 */
	public int modifyBy(@Param(value="tUserAccount") TUserAccount tUserAccount, @Param(value="condiTUserAccount") TUserAccount condiTUserAccount);
	
	/**
	 * 
	 * @param tUserAccount
	 * @param condiTUserAccount
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tUserAccount") TUserAccount tUserAccount, @Param(value="condiTUserAccount") TUserAccount condiTUserAccount);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TUserAccount getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tUserAccount
	 * @return
	 */
	public TUserAccount getBy(TUserAccount tUserAccount);
	
	/**
	 * 
	 * @return
	 */
	public TUserAccount get();
	
	/**
	 * 
	 * @param tUserAccount
	 * @return
	 */
	public List<TUserAccount> getsBy(TUserAccount tUserAccount);
	
	/**
	 * 
	 * @return
	 */
	public List<TUserAccount> gets();
	
	/**
	 * 
	 * @param tUserAccount
	 * @return
	 */
	public int getCntBy(@Param(value="tUserAccount") TUserAccount tUserAccount);
	
	/**
	 * 
	 * @param tUserAccount
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TUserAccount> getsByPage(@Param(value="tUserAccount") TUserAccount tUserAccount, @Param(value="page") int page, @Param(value="size") int size);
	
}
