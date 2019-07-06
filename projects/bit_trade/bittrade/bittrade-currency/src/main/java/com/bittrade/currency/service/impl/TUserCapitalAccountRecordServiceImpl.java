package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.DAO.IDefaultTUserCapitalAccountRecordDAO;
import com.bittrade.api.__default.service.impl.DefaultTUserCapitalAccountRecordServiceImpl;
import com.bittrade.api.service.ITUserCapitalAccountRecordService;
import com.bittrade.pojo.dto.TUserCapitalAccountRecordDTO;
import com.bittrade.pojo.model.TUserCapitalAccountRecord;
import com.bittrade.pojo.vo.TUserCapitalAccountRecordVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserCapitalAccountRecordServiceImpl extends DefaultTUserCapitalAccountRecordServiceImpl<IDefaultTUserCapitalAccountRecordDAO, TUserCapitalAccountRecord, TUserCapitalAccountRecordDTO, TUserCapitalAccountRecordVO> implements ITUserCapitalAccountRecordService {
	
}
