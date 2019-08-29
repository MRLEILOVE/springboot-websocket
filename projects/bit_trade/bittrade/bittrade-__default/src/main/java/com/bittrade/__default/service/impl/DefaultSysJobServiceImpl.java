/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.SysJob;
import com.bittrade.pojo.dto.SysJobDTO;
import com.bittrade.pojo.vo.SysJobVO;
import com.bittrade.__default.service.IDefaultSysJobService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultSysJobServiceImpl extends BaseServiceImpl<SysJob, SysJobDTO, SysJobVO, ISysJobDAO> implements IDefaultSysJobService {
public abstract class DefaultSysJobServiceImpl<DAO extends IBaseDAO<SysJob, SysJobDTO, SysJobVO>> extends BaseServiceImpl<SysJob, SysJobDTO, SysJobVO, DAO> implements IDefaultSysJobService {
	
}
