package com.bittrade.api.dao;

import com.bittrade.api.__default.DAO.IDefaultTCurrencyDAO;
import com.bittrade.pojo.vo.TCurrencyVO;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyDAO extends IDefaultTCurrencyDAO {
    /**
     * 查找所有法币
     */
    List<TCurrencyVO> findAllLegalCurrency();
}
