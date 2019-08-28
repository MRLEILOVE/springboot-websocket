package com.bittrade.c2c.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.DAO.IDefaultTAdvertOrderDAO;
import com.bittrade.pojo.model.TAdvertOrder;
import org.apache.ibatis.annotations.Param;

/**
 * @author Administrator
 */
public interface ITAdvertOrderDAO extends IDefaultTAdvertOrderDAO {

    Long getPaymentOrPutCoinAging(@Param("userId") Long userId, @Param("type") Integer type, @Param("status") Integer status);

    Page<TAdvertOrder> listAdvertOrders(@Param("page") Page<TAdvertOrder> page, @Param("userId") Long userId, @Param("status") Integer status);
}
