package com.bittrade.currency.service.impl;

import com.bittrade.api.dao.ITEntrustRecordDAO;
import com.bittrade.api.service.ITDealService;
import com.bittrade.pojo.vo.TEntrustRecordVO;
import com.bittrade.pojo.vo.TRealTimeTransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITDealServiceImpl implements ITDealService {

    @Autowired
    private ITEntrustRecordDAO tEntrustRecordDAO;

    /**
     * 查询用户成交记录
     */
    @Override
    public List<TEntrustRecordVO> queryDealEntrustByUserId(String userId) {
        return tEntrustRecordDAO.queryDealEntrustByUserId(userId);
    }

    /**
     * 实时成交
     */
    @Override
    public List<TRealTimeTransactionVO> realTimeTransaction(String currencyTradeId) {
        //#TODO 查询redis的交易数据
        return null;
    }
}
