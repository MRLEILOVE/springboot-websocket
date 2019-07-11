/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TCurrencyAddressDTO;
import com.bittrade.pojo.vo.TCurrencyAddressVO;
import com.bittrade.pojo.model.TCurrencyAddress;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTCurrencyAddressDAO extends IBaseDAO<TCurrencyAddress, TCurrencyAddressDTO, TCurrencyAddressVO> {
	
	/**
	 * 
	 * @param tCurrencyAddress
	 * @return
	 */
	public int add(TCurrencyAddress tCurrencyAddress);
	
	/**
	 * 
	 * @param tCurrencyAddress
	 * @return
	 */
	public int addWithSelective(TCurrencyAddress tCurrencyAddress);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tCurrencyAddress
	 * @return
	 */
	public int remove(TCurrencyAddress tCurrencyAddress);
	
	/**
	 * 
	 * @param tCurrencyAddress
	 * @return
	 */
	public int modifyByPK(TCurrencyAddress tCurrencyAddress);
	
	/**
	 * 
	 * @param tCurrencyAddress
	 * @return
	 */
	public int modifyWithSelectiveByPK(TCurrencyAddress tCurrencyAddress);
	
	/**
	 * 
	 * @param tCurrencyAddress
	 * @param condiTCurrencyAddress
	 * @return
	 */
	public int modify(@Param(value="tCurrencyAddress") TCurrencyAddress tCurrencyAddress, @Param(value="condiTCurrencyAddress") TCurrencyAddress condiTCurrencyAddress);
	
	/**
	 * 
	 * @param tCurrencyAddress
	 * @param condiTCurrencyAddress
	 * @return
	 */
	public int modifyWithSelective(@Param(value="tCurrencyAddress") TCurrencyAddress tCurrencyAddress, @Param(value="condiTCurrencyAddress") TCurrencyAddress condiTCurrencyAddress);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TCurrencyAddress getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tCurrencyAddress
	 * @return
	 */
	public TCurrencyAddress get(TCurrencyAddress tCurrencyAddress);
	
	/**
	 * 
	 * @param tCurrencyAddress
	 * @return
	 */
	public List<TCurrencyAddress> gets(TCurrencyAddress tCurrencyAddress);
	
	/**
	 * 
	 * @return
	 */
	public List<TCurrencyAddress> gets();
	
	/**
	 * 
	 * @param tCurrencyAddress
	 * @return
	 */
	public int getCntWithPage(@Param(value="tCurrencyAddress") TCurrencyAddress tCurrencyAddress);
	
	/**
	 * 
	 * @param tCurrencyAddress
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TCurrencyAddress> getsWithPage(@Param(value="tCurrencyAddress") TCurrencyAddress tCurrencyAddress, @Param(value="page") int page, @Param(value="size") int size);
	
}
