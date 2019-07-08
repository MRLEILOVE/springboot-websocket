package com.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;

import com.bittrade.api.__default.service.IDefaultTUserCapitalAccountRecordService;
import com.bittrade.pojo.dto.TUserCapitalAccountRecordDTO;
import com.bittrade.pojo.vo.TUserCapitalAccountRecordVO;
import com.bittrade.pojo.model.TUserCapitalAccountRecord;

/**
 * 
 * @author Administrator
 *
 */
public interface ITUserCapitalAccountRecordService<DAO extends IBaseDAO<TUserCapitalAccountRecord, TUserCapitalAccountRecordDTO, TUserCapitalAccountRecordVO>> extends IDefaultTUserCapitalAccountRecordService<TUserCapitalAccountRecord, TUserCapitalAccountRecordDTO, TUserCapitalAccountRecordVO, DAO> {
	
}
