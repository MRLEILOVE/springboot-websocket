package com.bittrade.api.service;

import java.util.List;

import com.bittrade.api.__default.DAO.IDefaultTCurrencyDAO;
import com.bittrade.api.__default.service.IDefaultTCurrencyService;
import com.bittrade.pojo.dto.TCurrencyDTO;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.pojo.vo.TCurrencyVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyService extends IDefaultTCurrencyService<TCurrency, TCurrencyDTO, TCurrencyVO, IDefaultTCurrencyDAO> {
    /**
     * 查找所有法币
     */
    List<TCurrencyVO> findAllLegalCurrency();
}
