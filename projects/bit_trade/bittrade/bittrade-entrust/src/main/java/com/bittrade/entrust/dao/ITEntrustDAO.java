package com.bittrade.entrust.dao;

import com.bittrade.__default.DAO.IDefaultTEntrustDAO;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.vo.TEntrustInfoVO;
import com.bittrade.pojo.vo.TEntrustVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITEntrustDAO extends IDefaultTEntrustDAO {

	/**
	 * 查询用户当前委托
	 */
	List<TEntrustVO> queryPresentEntrustByUserId(@Param("userId") String userId);

	/**
	 * 查询用户历史委托
	 */
	List<TEntrustVO> queryHistoryEntrustByUserId(@Param("userId") String userId);

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
			@Param("updateTime") Date updateTime, 
			@Param("ID") long ID, 
			@Param("version") long version
			);

}
