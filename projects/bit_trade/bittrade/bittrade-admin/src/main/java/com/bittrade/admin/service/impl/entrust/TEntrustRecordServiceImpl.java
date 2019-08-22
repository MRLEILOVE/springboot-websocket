package com.bittrade.admin.service.impl.entrust;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTEntrustRecordServiceImpl;
import com.bittrade.pojo.dto.TEntrustRecordDTO;
import com.bittrade.pojo.vo.TEntrustRecordVO;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.admin.dao.entrust.ITEntrustRecordDAO;
import com.bittrade.admin.service.entrust.ITEntrustRecordService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TEntrustRecordServiceImpl extends DefaultTEntrustRecordServiceImpl<ITEntrustRecordDAO, TEntrustRecord, TEntrustRecordDTO, TEntrustRecordVO> implements ITEntrustRecordService {
	
}
