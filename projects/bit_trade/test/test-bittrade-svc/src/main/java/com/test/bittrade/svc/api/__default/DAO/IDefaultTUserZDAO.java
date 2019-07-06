/**
 * This code is generated automatically. Please do not edit it.
 */
package com.test.bittrade.svc.api.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.bittrade.svc.pojo.dto.TUserZDTO;
import com.test.bittrade.svc.pojo.vo.TUserZVO;
import com.test.bittrade.svc.pojo.model.TUserZ;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTUserZDAO extends IBaseDAO<TUserZ, TUserZDTO, TUserZVO> {
	
	/**
	 * 
	 * @param tUserZ
	 * @return
	 */
	public int add(TUserZ tUserZ);
	
	/**
	 * 
	 * @param tUserZ
	 * @return
	 */
	public int addWithSelective(TUserZ tUserZ);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tUserZ
	 * @return
	 */
	public int remove(TUserZ tUserZ);
	
	/**
	 * 
	 * @param tUserZ
	 * @return
	 */
	public int modifyByPK(TUserZ tUserZ);
	
	/**
	 * 
	 * @param tUserZ
	 * @return
	 */
	public int modifyWithSelectiveByPK(TUserZ tUserZ);
	
	/**
	 * 
	 * @param tUserZ
	 * @param condiTUserZ
	 * @return
	 */
	public int modify(@Param(value="tUserZ") TUserZ tUserZ, @Param(value="condiTUserZ") TUserZ condiTUserZ);
	
	/**
	 * 
	 * @param tUserZ
	 * @param condiTUserZ
	 * @return
	 */
	public int modifyWithSelective(@Param(value="tUserZ") TUserZ tUserZ, @Param(value="condiTUserZ") TUserZ condiTUserZ);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TUserZ getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tUserZ
	 * @return
	 */
	public List<TUserZDTO> get(TUserZ tUserZ);
	
	/**
	 * 
	 * @return
	 */
	public List<TUserZDTO> gets();
	
	/**
	 * 
	 * @param tUserZ
	 * @return
	 */
	public int getCntWithPage(@Param(value="tUserZ") TUserZ tUserZ);
	
	/**
	 * 
	 * @param tUserZ
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TUserZDTO> getsWithPage(@Param(value="tUserZ") TUserZ tUserZ, @Param(value="page") int page, @Param(value="size") int size);
	
}
