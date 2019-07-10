package com.bittrade.currency.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTEntrustRecordServiceImpl;
import com.bittrade.currency.api.service.ITEntrustRecordService;
import com.bittrade.currency.dao.ITEntrustRecordDAO;
import com.bittrade.pojo.dto.TEntrustRecordDTO;
import com.bittrade.pojo.vo.TEntrustRecordVO;
import com.bittrade.pojo.vo.TRealTimeTransactionVO;
import com.bittrade.pojo.model.TEntrustRecord;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TEntrustRecordServiceImpl extends
		DefaultTEntrustRecordServiceImpl<ITEntrustRecordDAO, TEntrustRecord, TEntrustRecordDTO, TEntrustRecordVO>
		implements ITEntrustRecordService {

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
		// #TODO 查询redis的交易数据
		return null;
	}

}
