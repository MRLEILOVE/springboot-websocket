/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.WWalletAddressDTO;
import com.bittrade.pojo.vo.WWalletAddressVO;
import com.bittrade.pojo.model.WWalletAddress;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultWWalletAddressDAO extends IBaseDAO<WWalletAddress, WWalletAddressDTO, WWalletAddressVO> {
	
	/**
	 * 
	 * @param wWalletAddress
	 * @return
	 */
	public int add(WWalletAddress wWalletAddress);
	
	/**
	 * 
	 * @param wWalletAddress
	 * @return
	 */
	public int addWithSelective(WWalletAddress wWalletAddress);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param wWalletAddress
	 * @return
	 */
	public int removeBy(WWalletAddress wWalletAddress);
	
	/**
	 * 
	 * @param wWalletAddress
	 * @return
	 */
	public int modifyByPK(WWalletAddress wWalletAddress);
	
	/**
	 * 
	 * @param wWalletAddress
	 * @return
	 */
	public int modifyWithSelectiveByPK(WWalletAddress wWalletAddress);
	
	/**
	 * 
	 * @param wWalletAddress
	 * @param condiWWalletAddress
	 * @return
	 */
	public int modifyBy(@Param(value="wWalletAddress") WWalletAddress wWalletAddress, @Param(value="condiWWalletAddress") WWalletAddress condiWWalletAddress);
	
	/**
	 * 
	 * @param wWalletAddress
	 * @param condiWWalletAddress
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="wWalletAddress") WWalletAddress wWalletAddress, @Param(value="condiWWalletAddress") WWalletAddress condiWWalletAddress);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public WWalletAddress getByPK(Serializable PK);
	
	/**
	 * 
	 * @param wWalletAddress
	 * @return
	 */
	public WWalletAddress getBy(WWalletAddress wWalletAddress);
	
	/**
	 * 
	 * @return
	 */
	public WWalletAddress get();
	
	/**
	 * 
	 * @param wWalletAddress
	 * @return
	 */
	public List<WWalletAddress> getsBy(WWalletAddress wWalletAddress);
	
	/**
	 * 
	 * @return
	 */
	public List<WWalletAddress> gets();
	
	/**
	 * 
	 * @param wWalletAddress
	 * @return
	 */
	public int getCntBy(@Param(value="wWalletAddress") WWalletAddress wWalletAddress);
	
	/**
	 * 
	 * @param wWalletAddress
	 * @param page
	 * @param size
	 * @return
	 */
	public List<WWalletAddress> getsByPage(@Param(value="wWalletAddress") WWalletAddress wWalletAddress, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param wWalletAddressDTO
	 * @return
	 */
	public WWalletAddressDTO getDTOBy(WWalletAddressDTO wWalletAddressDTO);
	
	/**
	 * 
	 * @param wWalletAddressDTO
	 * @return
	 */
	public List<WWalletAddressDTO> getsDTOBy(WWalletAddressDTO wWalletAddressDTO);
	
	/**
	 * 
	 * @param wWalletAddressDTO
	 * @param pageDTO
	 * @return
	 */
	public List<WWalletAddressDTO> getsDTOBy(@Param(value="wWalletAddress") WWalletAddressDTO wWalletAddressDTO, PageDTO<WWalletAddressDTO> pageDTO);
	
}
