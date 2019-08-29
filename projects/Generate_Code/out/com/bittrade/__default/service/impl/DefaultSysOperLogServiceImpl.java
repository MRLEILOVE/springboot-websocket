/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.SysOperLog;
import com.bittrade.pojo.dto.SysOperLogDTO;
import com.bittrade.pojo.vo.SysOperLogVO;
import com.bittrade.__default.service.IDefaultSysOperLogService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultSysOperLogServiceImpl extends BaseServiceImpl<SysOperLog, SysOperLogDTO, SysOperLogVO, ISysOperLogDAO> implements IDefaultSysOperLogService {
public abstract class DefaultSysOperLogServiceImpl<DAO extends IBaseDAO<SysOperLog, SysOperLogDTO, SysOperLogVO>> extends BaseServiceImpl<SysOperLog, SysOperLogDTO, SysOperLogVO, DAO> implements IDefaultSysOperLogService {
	
}
