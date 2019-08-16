package com.bittrade.currency.dao;

import com.bittrade.__default.DAO.IDefaultTCurrencyDAO;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyDAO extends IDefaultTCurrencyDAO {
    /**
     * 查找所有可用币种
     */
    List<String> findUsableCurrency();
}
