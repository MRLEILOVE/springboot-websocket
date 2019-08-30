/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.TOrder;
import com.bittrade.pojo.dto.TOrderDTO;
import com.bittrade.pojo.vo.TOrderVO;
import com.bittrade.__default.service.IDefaultTOrderService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultTOrderServiceImpl extends BaseServiceImpl<TOrder, TOrderDTO, TOrderVO, ITOrderDAO> implements IDefaultTOrderService {
public abstract class DefaultTOrderServiceImpl<DAO extends IBaseDAO<TOrder, TOrderDTO, TOrderVO>> extends BaseServiceImpl<TOrder, TOrderDTO, TOrderVO, DAO> implements IDefaultTOrderService {
	
}
