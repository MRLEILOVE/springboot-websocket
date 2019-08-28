package com.bittrade.c2c.dao;

import com.bittrade.__default.DAO.IDefaultTAdvertOrderDAO;
import org.apache.ibatis.annotations.Param;

/**
 * @author Administrator
 */
public interface ITAdvertOrderDAO extends IDefaultTAdvertOrderDAO {

	Long getPaymentOrPutCoinAging(@Param("userId") Long userId, @Param("type") Byte type, @Param("status") Byte status);
}
