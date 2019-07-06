package com.bittrade.api.service;

import com.bittrade.api.__default.service.IDefaultTCurrencyTradeService;
import com.bittrade.api.dao.ITCurrencyTradeDAO;
import com.bittrade.pojo.dto.TCurrencyTradeDTO;
import com.bittrade.pojo.vo.TCurrencyTradeVO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.vo.TransactionPairVO;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyTradeService extends IDefaultTCurrencyTradeService<TCurrencyTrade, TCurrencyTradeDTO, TCurrencyTradeVO, ITCurrencyTradeDAO> {
    /**
     * 根据法币id查找交易对
     */
    List<TransactionPairVO> findTradeByCurrencyId(String currencyId2);
}
