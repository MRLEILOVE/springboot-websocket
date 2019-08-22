package com.bittrade.admin.service.impl.wallet;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWWalletAccountRecordServiceImpl;
import com.bittrade.pojo.dto.WWalletAccountRecordDTO;
import com.bittrade.pojo.vo.WWalletAccountRecordVO;
import com.bittrade.pojo.model.WWalletAccountRecord;
import com.bittrade.admin.dao.wallet.IWWalletAccountRecordDAO;
import com.bittrade.admin.service.wallet.IWWalletAccountRecordService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WWalletAccountRecordServiceImpl extends DefaultWWalletAccountRecordServiceImpl<IWWalletAccountRecordDAO, WWalletAccountRecord, WWalletAccountRecordDTO, WWalletAccountRecordVO> implements IWWalletAccountRecordService {
	
}
