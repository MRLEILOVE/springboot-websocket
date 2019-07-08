package com.bittrade.api.service;

import java.util.List;

import com.bittrade.api.__default.service.IDefaultTCurrencyService;
import com.bittrade.pojo.dto.TCurrencyDTO;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.pojo.vo.TCurrencyVO;
import com.core.framework.base.DAO.IBaseDAO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyService<DAO extends IBaseDAO<TCurrency, TCurrencyDTO, TCurrencyVO>> extends IDefaultTCurrencyService<TCurrency, TCurrencyDTO, TCurrencyVO, DAO> {
    /**
     * 查找所有法币
     */
    List<TCurrency> findAllLegalCurrency();
}
