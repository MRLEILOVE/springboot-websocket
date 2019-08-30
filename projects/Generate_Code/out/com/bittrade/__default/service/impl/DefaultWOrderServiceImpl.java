/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.WOrder;
import com.bittrade.pojo.dto.WOrderDTO;
import com.bittrade.pojo.vo.WOrderVO;
import com.bittrade.__default.service.IDefaultWOrderService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultWOrderServiceImpl extends BaseServiceImpl<WOrder, WOrderDTO, WOrderVO, IWOrderDAO> implements IDefaultWOrderService {
public abstract class DefaultWOrderServiceImpl<DAO extends IBaseDAO<WOrder, WOrderDTO, WOrderVO>> extends BaseServiceImpl<WOrder, WOrderDTO, WOrderVO, DAO> implements IDefaultWOrderService {
	
}
