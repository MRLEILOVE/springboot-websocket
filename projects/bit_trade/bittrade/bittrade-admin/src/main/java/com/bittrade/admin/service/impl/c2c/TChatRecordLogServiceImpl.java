package com.bittrade.admin.service.impl.c2c;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTChatRecordLogServiceImpl;
import com.bittrade.admin.dao.c2c.ITChatRecordLogDAO;
import com.bittrade.admin.service.c2c.ITChatRecordLogService;
import com.bittrade.pojo.dto.TChatRecordLogDTO;
import com.bittrade.pojo.model.TChatRecordLog;
import com.bittrade.pojo.vo.TChatRecordLogVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TChatRecordLogServiceImpl extends DefaultTChatRecordLogServiceImpl<ITChatRecordLogDAO, TChatRecordLog, TChatRecordLogDTO, TChatRecordLogVO> implements ITChatRecordLogService {
	
}
