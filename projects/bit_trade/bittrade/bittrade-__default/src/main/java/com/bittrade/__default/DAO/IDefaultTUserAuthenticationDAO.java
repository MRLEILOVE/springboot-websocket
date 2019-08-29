/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TUserAuthenticationDTO;
import com.bittrade.pojo.vo.TUserAuthenticationVO;
import com.bittrade.pojo.model.TUserAuthentication;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTUserAuthenticationDAO extends IBaseDAO<TUserAuthentication, TUserAuthenticationDTO, TUserAuthenticationVO> {
	
	/**
	 * 
	 * @param tUserAuthentication
	 * @return
	 */
	public int add(TUserAuthentication tUserAuthentication);
	
	/**
	 * 
	 * @param tUserAuthentication
	 * @return
	 */
	public int addWithSelective(TUserAuthentication tUserAuthentication);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tUserAuthentication
	 * @return
	 */
	public int removeBy(TUserAuthentication tUserAuthentication);
	
	/**
	 * 
	 * @param tUserAuthentication
	 * @return
	 */
	public int modifyByPK(TUserAuthentication tUserAuthentication);
	
	/**
	 * 
	 * @param tUserAuthentication
	 * @return
	 */
	public int modifyWithSelectiveByPK(TUserAuthentication tUserAuthentication);
	
	/**
	 * 
	 * @param tUserAuthentication
	 * @param condiTUserAuthentication
	 * @return
	 */
	public int modifyBy(@Param(value="tUserAuthentication") TUserAuthentication tUserAuthentication, @Param(value="condiTUserAuthentication") TUserAuthentication condiTUserAuthentication);
	
	/**
	 * 
	 * @param tUserAuthentication
	 * @param condiTUserAuthentication
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tUserAuthentication") TUserAuthentication tUserAuthentication, @Param(value="condiTUserAuthentication") TUserAuthentication condiTUserAuthentication);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TUserAuthentication getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tUserAuthentication
	 * @return
	 */
	public TUserAuthentication getBy(TUserAuthentication tUserAuthentication);
	
	/**
	 * 
	 * @return
	 */
	public TUserAuthentication get();
	
	/**
	 * 
	 * @param tUserAuthentication
	 * @return
	 */
	public List<TUserAuthentication> getsBy(TUserAuthentication tUserAuthentication);
	
	/**
	 * 
	 * @return
	 */
	public List<TUserAuthentication> gets();
	
	/**
	 * 
	 * @param tUserAuthentication
	 * @return
	 */
	public int getCntBy(@Param(value="tUserAuthentication") TUserAuthentication tUserAuthentication);
	
	/**
	 * 
	 * @param tUserAuthentication
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TUserAuthentication> getsByPage(@Param(value="tUserAuthentication") TUserAuthentication tUserAuthentication, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tUserAuthenticationDTO
	 * @return
	 */
	public TUserAuthenticationDTO getDTOBy(TUserAuthenticationDTO tUserAuthenticationDTO);
	
	/**
	 * 
	 * @param tUserAuthenticationDTO
	 * @return
	 */
	public List<TUserAuthenticationDTO> getsDTOBy(TUserAuthenticationDTO tUserAuthenticationDTO);
	
	/**
	 * 
	 * @param tUserAuthenticationDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TUserAuthenticationDTO> getsDTOBy(@Param(value="tUserAuthentication") TUserAuthenticationDTO tUserAuthenticationDTO, PageDTO<TUserAuthenticationDTO> pageDTO);
	
}
