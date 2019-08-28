package com.bittrade.c2c.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.service.IDefaultTAdvertOrderService;
import com.bittrade.pojo.dto.TAdvertOrderDTO;
import com.bittrade.pojo.vo.TAdvertOrderVO;
import com.bittrade.pojo.model.TAdvertOrder;
import com.core.web.constant.entity.LoginUser;

/**
 * 
 * @author Administrator
 *
 */
public interface ITAdvertOrderService extends IDefaultTAdvertOrderService<TAdvertOrder, TAdvertOrderDTO, TAdvertOrderVO> {

	Long getPaymentOrPutCoinAging(Long userId, Integer type, Integer status);

	boolean existenceNoCompleteOrders(Long advertId);

	TAdvertOrderDTO getAdvertOrderDetails(Long orderId);

	boolean cancelAdvertOrder(Long orderId);

    boolean clickAlreadyPaid(Long orderId);

	boolean clickAlreadyReceipt(Long orderId);

	Page<TAdvertOrder> listAdvertOrders(Page<TAdvertOrder> page, LoginUser loginUser, Integer status);
}
