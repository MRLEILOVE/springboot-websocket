package com.bittrade.c2c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.c2c.service.ITAdvertOrderService;
import com.bittrade.pojo.dto.TAdvertOrderDTO;
import com.bittrade.pojo.model.TAdvertOrder;
import com.bittrade.pojo.vo.TAdvertOrderVO;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.framework.base.controller.BaseController;
import com.core.web.constant.entity.LoginUser;

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
     * TODO 待測試
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 22:15
	 * @param orderId : 广告订单id
	 * @return result
	 */
	@PostMapping("/action/cancel/{order_id}")
	public ReturnDTO<Object> cancelAdvertOrder(@PathVariable("order_id") Long orderId) {
		boolean cancelResult = itAdvertOrderService.cancelAdvertOrder(orderId);
		return cancelResult ? ReturnDTO.ok("取消成功") : ReturnDTO.error("取消失敗");
	}

    /**
     * 點擊已付款
     * TODO 待測試
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/8/22 22:15
     * @param orderId : 广告订单id
     * @return result
     */
	@PostMapping("/action/click_already_paid/{order_id}")
    public ReturnDTO<Object> clickAlreadyPaid(@PathVariable("order_id") Long orderId) {
        boolean result = itAdvertOrderService.clickAlreadyPaid(orderId);
		return result ? ReturnDTO.ok("付款成功") : ReturnDTO.error("付款失敗，請稍後重試");
    }

    /**
     * 點擊確認收款
     * TODO 待測試
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/8/22 22:15
     * @param orderId : 广告订单id
     * @return result
     */
    @PostMapping("/action/click_already_receipt/{order_id}")
    public ReturnDTO<Object> clickAlreadyReceipt(@PathVariable("order_id") Long orderId) {
        boolean result = itAdvertOrderService.clickAlreadyReceipt(orderId);
		return result ? ReturnDTO.ok("收款成功") : ReturnDTO.error("收款失敗，請稍後重試");
    }

	/**
	 * 未完成订单列表
	 * TODO 待測試
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 22:15
	 * @param page : {@link Page}
	 * @param loginUser : {@link LoginUser}
	 * @return result
	 */
	@GetMapping("/advert_orders/no_complete")
	public ReturnDTO<Object> noCompleteAdvertOrders(Page<TAdvertOrder> page, @ALoginUser LoginUser loginUser) {
		Page<TAdvertOrder> advertOrderPage = itAdvertOrderService.listAdvertOrders(page, loginUser, TAdvertOrderDTO.StatusEnum.ALREADY_AUCTION.getCode());
		return ReturnDTO.ok(advertOrderPage);
	}


	/**
	 * 已完成订单列表
	 * TODO 待測試
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 22:15
	 * @param page : {@link Page}
	 * @param loginUser : {@link LoginUser}
	 * @return result
	 */
	@GetMapping("/advert_orders/already_complete")
	public ReturnDTO<Object> alreadyCompleteAdvertOrders(Page<TAdvertOrder> page, @ALoginUser LoginUser loginUser) {
		Page<TAdvertOrder> advertOrderPage = itAdvertOrderService.listAdvertOrders(page, loginUser, TAdvertOrderDTO.StatusEnum.ALREADY_COMPLETE.getCode());
		return ReturnDTO.ok(advertOrderPage);
	}


	/**
	 * 已取消订单列表
	 * TODO 待測試
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 22:15
	 * @param page : {@link Page}
	 * @param loginUser : {@link LoginUser}
	 * @return result
	 */
	@GetMapping("/advert_orders/already_cancel")
	public ReturnDTO<Object> alreadyCancelAdvertOrders(Page<TAdvertOrder> page, @ALoginUser LoginUser loginUser) {
		Page<TAdvertOrder> advertOrderPage = itAdvertOrderService.listAdvertOrders(page, loginUser, TAdvertOrderDTO.StatusEnum.ALREADY_CANCEL.getCode());
		return ReturnDTO.ok(advertOrderPage);
	}

}
