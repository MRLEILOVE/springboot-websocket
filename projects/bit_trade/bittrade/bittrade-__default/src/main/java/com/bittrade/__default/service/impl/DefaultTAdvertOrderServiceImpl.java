/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.TAdvertOrder;
import com.bittrade.pojo.dto.TAdvertOrderDTO;
import com.bittrade.pojo.vo.TAdvertOrderVO;
import com.bittrade.__default.service.IDefaultTAdvertOrderService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultTAdvertOrderServiceImpl extends BaseServiceImpl<TAdvertOrder, TAdvertOrderDTO, TAdvertOrderVO, ITAdvertOrderDAO> implements IDefaultTAdvertOrderService {
public abstract class DefaultTAdvertOrderServiceImpl<DAO extends IBaseDAO<TAdvertOrder, TAdvertOrderDTO, TAdvertOrderVO>> extends BaseServiceImpl<TAdvertOrder, TAdvertOrderDTO, TAdvertOrderVO, DAO> implements IDefaultTAdvertOrderService {
	
}
