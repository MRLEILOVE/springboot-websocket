/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.SysJobLog;
import com.bittrade.pojo.dto.SysJobLogDTO;
import com.bittrade.pojo.vo.SysJobLogVO;
import com.bittrade.__default.service.IDefaultSysJobLogService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultSysJobLogServiceImpl extends BaseServiceImpl<SysJobLog, SysJobLogDTO, SysJobLogVO, ISysJobLogDAO> implements IDefaultSysJobLogService {
public abstract class DefaultSysJobLogServiceImpl<DAO extends IBaseDAO<SysJobLog, SysJobLogDTO, SysJobLogVO>> extends BaseServiceImpl<SysJobLog, SysJobLogDTO, SysJobLogVO, DAO> implements IDefaultSysJobLogService {
	
}
