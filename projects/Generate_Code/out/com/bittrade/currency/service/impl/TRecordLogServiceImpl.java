package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTRecordLogServiceImpl;
import com.bittrade.currency.dao.ITRecordLogDAO;
import com.bittrade.pojo.dto.TRecordLogDTO;
import com.bittrade.pojo.vo.TRecordLogVO;
import com.bittrade.pojo.model.TRecordLog;
import com.bittrade.currency.api.service.ITRecordLogService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TRecordLogServiceImpl extends DefaultTRecordLogServiceImpl<ITRecordLogDAO, TRecordLog, TRecordLogDTO, TRecordLogVO> implements ITRecordLogService {
	
}
