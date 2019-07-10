package com.bittrade.api.service;

import com.bittrade.pojo.vo.TRealTimeTransactionVO;
import com.core.framework.base.DAO.IBaseDAO;

import com.bittrade.api.__default.service.IDefaultTEntrustRecordService;
import com.bittrade.pojo.dto.TEntrustRecordDTO;
import com.bittrade.pojo.vo.TEntrustRecordVO;
import com.bittrade.pojo.model.TEntrustRecord;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITEntrustRecordService<DAO extends IBaseDAO<TEntrustRecord, TEntrustRecordDTO, TEntrustRecordVO>> extends IDefaultTEntrustRecordService<TEntrustRecord, TEntrustRecordDTO, TEntrustRecordVO, DAO> {
    /**
     * 查询用户成交记录
     */
    List<TEntrustRecord> queryDealEntrustByUserId(String userId);

    /**
     * 实时成交
     */
    List<TRealTimeTransactionVO> realTimeTransaction(String currencyTradeId);
}
