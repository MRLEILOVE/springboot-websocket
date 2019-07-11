package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTWalletRecordServiceImpl;
import com.bittrade.currency.api.service.ITWalletRecordService;
import com.bittrade.currency.dao.ITWalletRecordDAO;
import com.bittrade.pojo.dto.TWalletRecordDTO;
import com.bittrade.pojo.vo.TWalletRecordVO;
import com.bittrade.pojo.model.TWalletRecord;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TWalletRecordServiceImpl extends DefaultTWalletRecordServiceImpl<ITWalletRecordDAO, TWalletRecord, TWalletRecordDTO, TWalletRecordVO> implements ITWalletRecordService {
	
}
