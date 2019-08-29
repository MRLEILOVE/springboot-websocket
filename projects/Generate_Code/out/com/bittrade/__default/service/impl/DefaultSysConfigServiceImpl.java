/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.SysConfig;
import com.bittrade.pojo.dto.SysConfigDTO;
import com.bittrade.pojo.vo.SysConfigVO;
import com.bittrade.__default.service.IDefaultSysConfigService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultSysConfigServiceImpl extends BaseServiceImpl<SysConfig, SysConfigDTO, SysConfigVO, ISysConfigDAO> implements IDefaultSysConfigService {
public abstract class DefaultSysConfigServiceImpl<DAO extends IBaseDAO<SysConfig, SysConfigDTO, SysConfigVO>> extends BaseServiceImpl<SysConfig, SysConfigDTO, SysConfigVO, DAO> implements IDefaultSysConfigService {
	
}
