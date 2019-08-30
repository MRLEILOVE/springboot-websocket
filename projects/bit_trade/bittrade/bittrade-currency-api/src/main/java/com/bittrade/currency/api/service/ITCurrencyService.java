package com.bittrade.currency.api.service;

import java.util.List;

import com.bittrade.__default.service.IDefaultTCurrencyService;
import com.bittrade.pojo.model.TCurrency;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyService extends IDefaultTCurrencyService {
    /**
     * 查找所有法币
     */
    List<TCurrency> findAllLegalCurrency();

    /**
     * 查找所有可用币种
     */
    List<String> findUsableCurrency();

    /**
     * 获取币种列表
     */
    List<TCurrency> getCurrencies();

    /**
     * 通过名称获取币种列表
     */
    List<TCurrency> getCurrencies(List<String> list);
}
