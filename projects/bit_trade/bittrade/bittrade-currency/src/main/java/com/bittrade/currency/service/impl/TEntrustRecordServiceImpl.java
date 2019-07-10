package com.bittrade.currency.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.pojo.vo.TRealTimeTransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.api.__default.service.impl.DefaultTEntrustRecordServiceImpl;
import com.bittrade.currency.dao.ITEntrustRecordDAO;
import com.bittrade.pojo.dto.TEntrustRecordDTO;
import com.bittrade.pojo.vo.TEntrustRecordVO;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.api.service.ITEntrustRecordService;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TEntrustRecordServiceImpl extends DefaultTEntrustRecordServiceImpl<ITEntrustRecordDAO, TEntrustRecord, TEntrustRecordDTO, TEntrustRecordVO> implements ITEntrustRecordService<ITEntrustRecordDAO> {
    @Autowired
    private ITEntrustRecordDAO tEntrustRecordDAO;

    /**
     * 查询用户成交记录
     */
    @Override
    public List<TEntrustRecord> queryDealEntrustByUserId(String userId) {
        QueryWrapper<TEntrustRecord> wrapper = new QueryWrapper<>();
        wrapper.eq(TEntrustRecord.FieldNames.USER_ID,userId).orderByDesc(TEntrustRecord.FieldNames.ID);
        return tEntrustRecordDAO.selectList(wrapper);
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
