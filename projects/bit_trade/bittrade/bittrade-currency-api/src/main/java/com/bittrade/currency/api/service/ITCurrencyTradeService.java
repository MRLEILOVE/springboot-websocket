package com.bittrade.currency.api.service;

import java.util.List;

import com.bittrade.__default.service.IDefaultTCurrencyTradeService;
import com.bittrade.pojo.dto.TCurrencyTradeDTO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.vo.TCurrencyTradeVO;
import com.bittrade.pojo.vo.TransactionPairVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyTradeService extends IDefaultTCurrencyTradeService<TCurrencyTrade, TCurrencyTradeDTO, TCurrencyTradeVO> {
    /**
     * 根据法币id查找交易对
     */
    List<TransactionPairVO> findTradeByCurrencyId2(String currencyId2);
}
