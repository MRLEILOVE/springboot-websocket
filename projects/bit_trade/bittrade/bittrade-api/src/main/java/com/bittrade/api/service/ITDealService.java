package com.bittrade.api.service;

import com.bittrade.pojo.vo.TEntrustRecordVO;
import com.bittrade.pojo.vo.TRealTimeTransactionVO;

import java.util.List;

public interface ITDealService {
    /**
     * 查询用户成交记录
     */
    List<TEntrustRecordVO> queryDealEntrustByUserId(String userId);

    /**
     * 实时成交
     */
    List<TRealTimeTransactionVO> realTimeTransaction(String currencyTradeId);
}
