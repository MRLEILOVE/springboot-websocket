package com.bittrade.api.service;

import com.bittrade.api.__default.service.IDefaultTCurrencyService;
import com.bittrade.api.dao.ITCurrencyDAO;
import com.bittrade.pojo.dto.TCurrencyDTO;
import com.bittrade.pojo.vo.TCurrencyVO;
import com.bittrade.pojo.model.TCurrency;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyService extends IDefaultTCurrencyService<TCurrency, TCurrencyDTO, TCurrencyVO, ITCurrencyDAO> {
    /**
     * 查找所有法币
     */
    List<TCurrencyVO> findAllLegalCurrency();
}
