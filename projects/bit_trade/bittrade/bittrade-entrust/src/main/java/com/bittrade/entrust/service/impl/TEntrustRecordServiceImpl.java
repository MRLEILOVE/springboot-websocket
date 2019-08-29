package com.bittrade.entrust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.__default.service.impl.DefaultTEntrustRecordServiceImpl;
import com.bittrade.entrust.api.service.ITEntrustRecordService;
import com.bittrade.entrust.dao.ITEntrustRecordDAO;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.pojo.vo.TRealTimeTransactionVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Component
public class TEntrustRecordServiceImpl extends
		DefaultTEntrustRecordServiceImpl<ITEntrustRecordDAO>
		implements ITEntrustRecordService {

	@Autowired
	private ITEntrustRecordDAO tEntrustRecordDAO;

	/**
	 * 查询用户成交记录
	 */
	@Override
	public List<TEntrustRecord> queryDealEntrustByUserId(String userId) {
		QueryWrapper<TEntrustRecord> wrapper = new QueryWrapper<>();
		wrapper.eq(TEntrustRecord.FieldNames.USER_ID, userId).orderByDesc(TEntrustRecord.FieldNames.ID);
		return tEntrustRecordDAO.selectList(wrapper);
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
