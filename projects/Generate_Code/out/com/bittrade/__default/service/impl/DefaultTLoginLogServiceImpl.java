/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.TLoginLog;
import com.bittrade.pojo.dto.TLoginLogDTO;
import com.bittrade.pojo.vo.TLoginLogVO;
import com.bittrade.__default.service.IDefaultTLoginLogService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultTLoginLogServiceImpl extends BaseServiceImpl<TLoginLog, TLoginLogDTO, TLoginLogVO, ITLoginLogDAO> implements IDefaultTLoginLogService {
public abstract class DefaultTLoginLogServiceImpl<DAO extends IBaseDAO<TLoginLog, TLoginLogDTO, TLoginLogVO>> extends BaseServiceImpl<TLoginLog, TLoginLogDTO, TLoginLogVO, DAO> implements IDefaultTLoginLogService {
	
}
