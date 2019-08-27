package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTUserCapitalAccountRecordServiceImpl;
import com.bittrade.currency.dao.ITUserCapitalAccountRecordDAO;
import com.bittrade.pojo.dto.TUserCapitalAccountRecordDTO;
import com.bittrade.pojo.vo.TUserCapitalAccountRecordVO;
import com.bittrade.pojo.model.TUserCapitalAccountRecord;
import com.bittrade.currency.api.service.ITUserCapitalAccountRecordService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserCapitalAccountRecordServiceImpl extends DefaultTUserCapitalAccountRecordServiceImpl<ITUserCapitalAccountRecordDAO, TUserCapitalAccountRecord, TUserCapitalAccountRecordDTO, TUserCapitalAccountRecordVO> implements ITUserCapitalAccountRecordService {
	
}
