package com.bittrade.entrust.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.service.impl.DefaultTEntrustRecordServiceImpl;
import com.bittrade.api.service.ITEntrustRecordService;
import com.bittrade.entrust.dao.ITEntrustRecordDAO;
import com.bittrade.pojo.dto.TEntrustRecordDTO;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.pojo.vo.TEntrustRecordVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TEntrustRecordServiceImpl extends DefaultTEntrustRecordServiceImpl<ITEntrustRecordDAO, TEntrustRecord, TEntrustRecordDTO, TEntrustRecordVO> implements ITEntrustRecordService<ITEntrustRecordDAO> {
	
}
