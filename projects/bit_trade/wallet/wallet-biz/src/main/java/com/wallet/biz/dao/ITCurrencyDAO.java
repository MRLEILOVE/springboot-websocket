package com.wallet.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wallet.biz.pojo.model.TCurrency;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyDAO extends BaseMapper<TCurrency> {
    /**
     * 查找所有可用币种
     */
    List<String> findUsableCurrency();
}
