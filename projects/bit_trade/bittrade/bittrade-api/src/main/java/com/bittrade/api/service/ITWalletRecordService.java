package com.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;

import com.bittrade.api.__default.service.IDefaultTWalletRecordService;
import com.bittrade.pojo.dto.TWalletRecordDTO;
import com.bittrade.pojo.vo.TWalletRecordVO;
import com.bittrade.pojo.model.TWalletRecord;

/**
 * 
 * @author Administrator
 *
 */
public interface ITWalletRecordService<DAO extends IBaseDAO<TWalletRecord, TWalletRecordDTO, TWalletRecordVO>> extends IDefaultTWalletRecordService<TWalletRecord, TWalletRecordDTO, TWalletRecordVO, DAO> {
	
}
