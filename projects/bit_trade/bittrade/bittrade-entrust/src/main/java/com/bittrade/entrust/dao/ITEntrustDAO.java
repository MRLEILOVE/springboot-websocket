package com.bittrade.entrust.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.bittrade.api.__default.DAO.IDefaultTEntrustDAO;

public interface ITEntrustDAO extends IDefaultTEntrustDAO {

	/**
	 * 
	 * @param successAmount
	 * @param leftCount
	 * @param status
	 * @param ID
	 */
	void updateOnMatch(@Param("successAmount") BigDecimal successAmount, @Param("leftCount") BigDecimal leftCount, @Param("status") long status, @Param("ID") long ID);

}
