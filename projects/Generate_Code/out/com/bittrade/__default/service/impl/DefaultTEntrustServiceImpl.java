/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.vo.TEntrustVO;
import com.bittrade.__default.service.IDefaultTEntrustService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultTEntrustServiceImpl extends BaseServiceImpl<TEntrust, TEntrustDTO, TEntrustVO, ITEntrustDAO> implements IDefaultTEntrustService {
public abstract class DefaultTEntrustServiceImpl<DAO extends IBaseDAO<TEntrust, TEntrustDTO, TEntrustVO>> extends BaseServiceImpl<TEntrust, TEntrustDTO, TEntrustVO, DAO> implements IDefaultTEntrustService {
	
}
