package com.bittrade.c2c.service;

import com.bittrade.__default.service.IDefaultTAdvertOrderService;
import com.bittrade.pojo.dto.TAdvertOrderDTO;
import com.bittrade.pojo.vo.TAdvertOrderVO;
import com.bittrade.pojo.model.TAdvertOrder;

/**
 * 
 * @author Administrator
 *
 */
public interface ITAdvertOrderService extends IDefaultTAdvertOrderService<TAdvertOrder, TAdvertOrderDTO, TAdvertOrderVO> {

	Long getPaymentOrPutCoinAging(Long userId, Integer type, Integer status);

	boolean existenceNoCompleteOrders(Long advertId);
}
