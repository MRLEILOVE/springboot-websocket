package com.bittrade.entrust.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.__default.DAO.IDefaultTEntrustDAO;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.vo.TEntrustInfoVO;
import com.core.common.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITEntrustDAO extends IDefaultTEntrustDAO {
	/**
	 * 查询用户的委托单成交明细
	 */
	TEntrustInfoVO queryEntrustInfoByUserId(@Param("userId") String userId, @Param("entrustId") String entrustId);

	/**
	 * 撤单
	 */
	int killOrder(TEntrust tEntrust);

	/**
	 * 
	 * @param successAmount
	 * @param leftCount
	 * @param status
	 * @param updateTime
	 * @param ID
	 * @param version
	 */
	int updateOnMatch(
			@Param("successAmount") BigDecimal successAmount, 
			@Param("leftCount") BigDecimal leftCount,
			@Param("status") long status, 
			@Param("updateTime") LocalDateTime updateTime, 
			@Param("ID") long ID, 
			@Param("version") long version
			);
	
	List<TEntrust> getsByPage(@Param(value = "entrust") TEntrust entrust, PageDTO<TEntrust> pageDTO);

}
