package com.bittrade.api.service;

import java.util.List;

import com.bittrade.api.__default.service.IDefaultTCurrencyTradeService;
import com.bittrade.pojo.dto.TCurrencyTradeDTO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.vo.TCurrencyTradeVO;
import com.bittrade.pojo.vo.TransactionPairVO;
import com.core.framework.base.DAO.IBaseDAO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyTradeService<DAO extends IBaseDAO<TCurrencyTrade, TCurrencyTradeDTO, TCurrencyTradeVO>> extends IDefaultTCurrencyTradeService<TCurrencyTrade, TCurrencyTradeDTO, TCurrencyTradeVO, DAO> {
    /**
     * 根据法币id查找交易对
     */
    List<TransactionPairVO> findTradeByCurrencyId(String currencyId2);
}
