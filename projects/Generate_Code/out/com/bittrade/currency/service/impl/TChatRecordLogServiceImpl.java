package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTChatRecordLogServiceImpl;
import com.bittrade.currency.dao.ITChatRecordLogDAO;
import com.bittrade.pojo.dto.TChatRecordLogDTO;
import com.bittrade.pojo.vo.TChatRecordLogVO;
import com.bittrade.pojo.model.TChatRecordLog;
import com.bittrade.currency.api.service.ITChatRecordLogService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TChatRecordLogServiceImpl extends DefaultTChatRecordLogServiceImpl<ITChatRecordLogDAO, TChatRecordLog, TChatRecordLogDTO, TChatRecordLogVO> implements ITChatRecordLogService {
	
}
