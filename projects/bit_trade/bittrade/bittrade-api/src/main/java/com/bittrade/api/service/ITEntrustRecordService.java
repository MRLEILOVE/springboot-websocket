package com.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;

import com.bittrade.api.__default.service.IDefaultTEntrustRecordService;
import com.bittrade.pojo.dto.TEntrustRecordDTO;
import com.bittrade.pojo.vo.TEntrustRecordVO;
import com.bittrade.pojo.model.TEntrustRecord;

/**
 * 
 * @author Administrator
 *
 */
public interface ITEntrustRecordService<DAO extends IBaseDAO<TEntrustRecord, TEntrustRecordDTO, TEntrustRecordVO>> extends IDefaultTEntrustRecordService<TEntrustRecord, TEntrustRecordDTO, TEntrustRecordVO, DAO> {
	
}
