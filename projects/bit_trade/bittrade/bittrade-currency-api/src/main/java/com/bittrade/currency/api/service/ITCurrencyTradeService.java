package com.bittrade.currency.api.service;

import java.util.List;

import com.bittrade.__default.service.IDefaultTCurrencyTradeService;
import com.bittrade.pojo.vo.CurrencyTradeVO;
import com.bittrade.pojo.vo.TransactionPairVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyTradeService extends IDefaultTCurrencyTradeService {
    /**
     * 根据法币id查找交易对
     */
    List<TransactionPairVO> findTradeByCurrencyId2(String currencyId2,String userId);

    /**
     * 刚点进币币页面，获取交易对信息
     * @param id 交易对id
     * @return 交易对对象信息
     */
    CurrencyTradeVO queryCurrencyTradeAtFirst(Integer id);
}
