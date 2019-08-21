package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWWalletAccountRecordServiceImpl;
import com.bittrade.currency.dao.IWWalletAccountRecordDAO;
import com.bittrade.pojo.dto.WWalletAccountRecordDTO;
import com.bittrade.pojo.vo.WWalletAccountRecordVO;
import com.bittrade.pojo.model.WWalletAccountRecord;
import com.bittrade.currency.api.service.IWWalletAccountRecordService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WWalletAccountRecordServiceImpl extends DefaultWWalletAccountRecordServiceImpl<IWWalletAccountRecordDAO, WWalletAccountRecord, WWalletAccountRecordDTO, WWalletAccountRecordVO> implements IWWalletAccountRecordService {
	
}
