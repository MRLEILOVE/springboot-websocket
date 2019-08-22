package com.bittrade.admin.service.impl.wallet;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTWalletRecordServiceImpl;
import com.bittrade.pojo.dto.TWalletRecordDTO;
import com.bittrade.pojo.vo.TWalletRecordVO;
import com.bittrade.pojo.model.TWalletRecord;
import com.bittrade.admin.dao.wallet.ITWalletRecordDAO;
import com.bittrade.admin.service.wallet.ITWalletRecordService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TWalletRecordServiceImpl extends DefaultTWalletRecordServiceImpl<ITWalletRecordDAO, TWalletRecord, TWalletRecordDTO, TWalletRecordVO> implements ITWalletRecordService {
	
}
