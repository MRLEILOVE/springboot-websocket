package com.bittrade.c2c.controller;

import com.bittrade.c2c.service.ITAdvertOrderService;
import com.bittrade.pojo.dto.TAdvertOrderDTO;
import com.bittrade.pojo.model.TAdvertOrder;
import com.bittrade.pojo.vo.TAdvertOrderVO;
import com.core.common.DTO.ReturnDTO;
import com.core.framework.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 广告订单
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value = { "/tAdvertOrder" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TAdvertOrderController extends BaseController<TAdvertOrder, TAdvertOrderDTO, TAdvertOrderVO, ITAdvertOrderService> {

	@Autowired
	private ITAdvertOrderService itAdvertOrderService;

	/**
	 * 获取订单详情
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 19:35
	 * @param orderId : 广告订单id
	 * @return  {@link TAdvertOrder}
	 */
	@GetMapping("/advert_orders/details/{order_id}")
	public ReturnDTO<Object> getAdvertOrderDetails(@PathVariable("order_id") Long orderId) {
		return ReturnDTO.ok(itAdvertOrderService.getAdvertOrderDetails(orderId));
	}

	/**
	 * 取消订单
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 22:15
	 * @param orderId : 广告订单id
	 * @return
	 */
	@PostMapping("/action/cancel/{order_id}")
	public ReturnDTO<Object> cancelAdvertOrder(@PathVariable("order_id") Long orderId) {
		boolean cancelResult = itAdvertOrderService.cancelAdvertOrder(orderId);
		return cancelResult ? ReturnDTO.ok("取消成功") : ReturnDTO.error("取消失敗");
	}



}
