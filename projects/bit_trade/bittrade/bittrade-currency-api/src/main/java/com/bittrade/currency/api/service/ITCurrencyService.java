package com.bittrade.currency.api.service;

import java.util.List;

import com.bittrade.__default.service.IDefaultTCurrencyService;
import com.bittrade.pojo.dto.TCurrencyDTO;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.pojo.vo.TCurrencyVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyService extends IDefaultTCurrencyService<TCurrency, TCurrencyDTO, TCurrencyVO> {
    /**
     * 查找所有法币
     */
    List<TCurrency> findAllLegalCurrency();
}
