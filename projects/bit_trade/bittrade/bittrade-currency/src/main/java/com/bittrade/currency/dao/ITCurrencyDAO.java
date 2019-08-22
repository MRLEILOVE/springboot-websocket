package com.bittrade.currency.dao;

import com.bittrade.__default.DAO.IDefaultTCurrencyDAO;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.pojo.vo.CoinVo;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 通过名称获取资金账户币种
     * @param list 币种名称列表
     * @return
     */
    List<CoinVo> getFundCoinVo(@Param("list") List<String> list);

    /**
     * 通过名称获取资金账户币种
     * @param list 币种名称列表
     * @return
     */
    List<TCurrency> getCurrencies(@Param("list") List<String> list);
}
