/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.vo.TEntrustVO;
import com.bittrade.pojo.model.TEntrust;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTEntrustDAO extends IBaseDAO<TEntrust, TEntrustDTO, TEntrustVO> {
	
	/**
	 * 
	 * @param tEntrust
	 * @return
	 */
	public int add(TEntrust tEntrust);
	
	/**
	 * 
	 * @param tEntrust
	 * @return
	 */
	public int addWithSelective(TEntrust tEntrust);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tEntrust
	 * @return
	 */
	public int remove(TEntrust tEntrust);
	
	/**
	 * 
	 * @param tEntrust
	 * @return
	 */
	public int modifyByPK(TEntrust tEntrust);
	
	/**
	 * 
	 * @param tEntrust
	 * @return
	 */
	public int modifyWithSelectiveByPK(TEntrust tEntrust);
	
	/**
	 * 
	 * @param tEntrust
	 * @param condiTEntrust
	 * @return
	 */
	public int modify(@Param(value="tEntrust") TEntrust tEntrust, @Param(value="condiTEntrust") TEntrust condiTEntrust);
	
	/**
	 * 
	 * @param tEntrust
	 * @param condiTEntrust
	 * @return
	 */
	public int modifyWithSelective(@Param(value="tEntrust") TEntrust tEntrust, @Param(value="condiTEntrust") TEntrust condiTEntrust);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TEntrust getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tEntrust
	 * @return
	 */
	public List<TEntrustDTO> get(TEntrust tEntrust);
	
	/**
	 * 
	 * @return
	 */
	public List<TEntrustDTO> gets();
	
	/**
	 * 
	 * @param tEntrust
	 * @return
	 */
	public int getCntWithPage(@Param(value="tEntrust") TEntrust tEntrust);
	
	/**
	 * 
	 * @param tEntrust
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TEntrustDTO> getsWithPage(@Param(value="tEntrust") TEntrust tEntrust, @Param(value="page") int page, @Param(value="size") int size);
	
}