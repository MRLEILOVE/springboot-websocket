package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTEntrustRecordServiceImpl;
import com.bittrade.currency.dao.ITEntrustRecordDAO;
import com.bittrade.pojo.dto.TEntrustRecordDTO;
import com.bittrade.pojo.vo.TEntrustRecordVO;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.currency.api.service.ITEntrustRecordService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TEntrustRecordServiceImpl extends DefaultTEntrustRecordServiceImpl<ITEntrustRecordDAO, TEntrustRecord, TEntrustRecordDTO, TEntrustRecordVO> implements ITEntrustRecordService {
	
}
