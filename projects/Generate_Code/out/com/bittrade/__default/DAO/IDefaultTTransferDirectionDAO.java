/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.TTransferDirectionDTO;
import com.bittrade.pojo.vo.TTransferDirectionVO;
import com.bittrade.pojo.model.TTransferDirection;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultTTransferDirectionDAO extends IBaseDAO<TTransferDirection, TTransferDirectionDTO, TTransferDirectionVO> {
	
	/**
	 * 
	 * @param tTransferDirection
	 * @return
	 */
	public int add(TTransferDirection tTransferDirection);
	
	/**
	 * 
	 * @param tTransferDirection
	 * @return
	 */
	public int addWithSelective(TTransferDirection tTransferDirection);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param tTransferDirection
	 * @return
	 */
	public int removeBy(TTransferDirection tTransferDirection);
	
	/**
	 * 
	 * @param tTransferDirection
	 * @return
	 */
	public int modifyByPK(TTransferDirection tTransferDirection);
	
	/**
	 * 
	 * @param tTransferDirection
	 * @return
	 */
	public int modifyWithSelectiveByPK(TTransferDirection tTransferDirection);
	
	/**
	 * 
	 * @param tTransferDirection
	 * @param condiTTransferDirection
	 * @return
	 */
	public int modifyBy(@Param(value="tTransferDirection") TTransferDirection tTransferDirection, @Param(value="condiTTransferDirection") TTransferDirection condiTTransferDirection);
	
	/**
	 * 
	 * @param tTransferDirection
	 * @param condiTTransferDirection
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="tTransferDirection") TTransferDirection tTransferDirection, @Param(value="condiTTransferDirection") TTransferDirection condiTTransferDirection);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public TTransferDirection getByPK(Serializable PK);
	
	/**
	 * 
	 * @param tTransferDirection
	 * @return
	 */
	public TTransferDirection getBy(TTransferDirection tTransferDirection);
	
	/**
	 * 
	 * @return
	 */
	public TTransferDirection get();
	
	/**
	 * 
	 * @param tTransferDirection
	 * @return
	 */
	public List<TTransferDirection> getsBy(TTransferDirection tTransferDirection);
	
	/**
	 * 
	 * @return
	 */
	public List<TTransferDirection> gets();
	
	/**
	 * 
	 * @param tTransferDirection
	 * @return
	 */
	public int getCntBy(@Param(value="tTransferDirection") TTransferDirection tTransferDirection);
	
	/**
	 * 
	 * @param tTransferDirection
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TTransferDirection> getsByPage(@Param(value="tTransferDirection") TTransferDirection tTransferDirection, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param tTransferDirectionDTO
	 * @return
	 */
	public TTransferDirectionDTO getDTOBy(TTransferDirectionDTO tTransferDirectionDTO);
	
	/**
	 * 
	 * @param tTransferDirectionDTO
	 * @return
	 */
	public List<TTransferDirectionDTO> getsDTOBy(TTransferDirectionDTO tTransferDirectionDTO);
	
	/**
	 * 
	 * @param tTransferDirectionDTO
	 * @param pageDTO
	 * @return
	 */
	public List<TTransferDirectionDTO> getsDTOBy(TTransferDirectionDTO tTransferDirectionDTO, PageDTO<TTransferDirectionDTO> pageDTO);
	
}
