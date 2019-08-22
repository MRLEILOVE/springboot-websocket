package com.wallet.biz.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wallet.biz.pojo.model.TCurrency;



import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyService extends IService<TCurrency> {
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
}
