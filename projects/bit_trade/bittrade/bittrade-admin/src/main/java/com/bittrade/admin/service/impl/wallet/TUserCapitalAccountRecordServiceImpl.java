package com.bittrade.admin.service.impl.wallet;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTUserCapitalAccountRecordServiceImpl;
import com.bittrade.pojo.dto.TUserCapitalAccountRecordDTO;
import com.bittrade.pojo.vo.TUserCapitalAccountRecordVO;
import com.bittrade.pojo.model.TUserCapitalAccountRecord;
import com.bittrade.admin.dao.wallet.ITUserCapitalAccountRecordDAO;
import com.bittrade.admin.service.wallet.ITUserCapitalAccountRecordService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserCapitalAccountRecordServiceImpl extends DefaultTUserCapitalAccountRecordServiceImpl<ITUserCapitalAccountRecordDAO, TUserCapitalAccountRecord, TUserCapitalAccountRecordDTO, TUserCapitalAccountRecordVO> implements ITUserCapitalAccountRecordService {
	
}
