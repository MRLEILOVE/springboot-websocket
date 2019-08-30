package com.bittrade.entrust.api.service;

import java.util.List;

import com.bittrade.__default.service.IDefaultTEntrustRecordService;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.pojo.vo.TRealTimeTransactionVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITEntrustRecordService
		extends IDefaultTEntrustRecordService {

	/**
	 * 查询用户成交记录
	 */
	List<TEntrustRecord> queryDealEntrustByUserId(String userId);

	/**
	 * 实时成交
	 */
	List<TRealTimeTransactionVO> realTimeTransaction(String currencyTradeId);

}
