package com.bittrade.c2c.controller;

import com.bittrade.c2c.service.ITPaymentModeService;
import com.bittrade.pojo.dto.TPaymentModeDTO;
import com.bittrade.pojo.model.TPaymentMode;
import com.bittrade.pojo.vo.BindingAliPayVO;
import com.bittrade.pojo.vo.BindingBankCardVO;
import com.bittrade.pojo.vo.BindingWeChartVO;
import com.bittrade.pojo.vo.TPaymentModeVO;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.framework.base.controller.BaseController;
import com.core.web.constant.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收款方式
 *
 * @author Administrator
 */
@Controller
@ResponseBody
@RequestMapping(value = {"/tPaymentMode"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TPaymentModeController extends BaseController<TPaymentMode, TPaymentModeDTO, TPaymentModeVO, ITPaymentModeService> {

	@Autowired
	private ITPaymentModeService itPaymentModeService;

	/**
	 * 绑定银行卡
	 *
	 * @param bindingBankCardVO {@link BindingBankCardVO}
	 * @param loginUser         {@link LoginUser}
	 * @return result
	 */
	@PostMapping("/action/binding_bank_card")
	public ReturnDTO<Object> bindingBankCard(@Validated BindingBankCardVO bindingBankCardVO, @ALoginUser LoginUser loginUser) {
		boolean result = itPaymentModeService.bindingBankCard(bindingBankCardVO, loginUser);
		return result ? ReturnDTO.ok("綁定成功") : ReturnDTO.error("綁定失敗");
	}

	/**
	 * 绑定微信支付
	 *
	 * @param bindingWeChartVO {@link BindingWeChartVO}
	 * @param loginUser        {@link LoginUser}
	 * @return result
	 */
	@PostMapping("/action/binding_we_chart")
	public ReturnDTO<Object> bindingWeChart(@Validated BindingWeChartVO bindingWeChartVO, @ALoginUser LoginUser loginUser) {
		boolean result = itPaymentModeService.bindingWeChart(bindingWeChartVO, loginUser);
		return result ? ReturnDTO.ok("綁定成功") : ReturnDTO.error("綁定失敗");
	}

	/**
	 * 绑定支付宝
	 *
	 * @param bindingAliPayVO {@link BindingAliPayVO}
	 * @param loginUser       {@link LoginUser}
	 * @return result
	 */
	@PostMapping("/action/binding_ali_pay")
	public ReturnDTO<Object> bindingAliPay(@Validated BindingAliPayVO bindingAliPayVO, @ALoginUser LoginUser loginUser) {
		boolean result = itPaymentModeService.bindingAliPay(bindingAliPayVO, loginUser);
		return result ? ReturnDTO.ok("綁定成功") : ReturnDTO.error("綁定失敗");
	}

	/**
	 * 获取用户已绑定付款方式
	 *
	 * @param loginUser {@link LoginUser}
	 * @return result
	 */
	@GetMapping("/payments/already_binding")
	public ReturnDTO<Object> listAlreadyBindingPayment(@ALoginUser LoginUser loginUser) {
		List<TPaymentMode> listAlreadyBindingPayments = itPaymentModeService.listAlreadyBindingPayment(loginUser);
		return ReturnDTO.ok(listAlreadyBindingPayments);
	}

	/**
	 * 获取付款方式详情
	 *
	 * @param type      {@link TPaymentMode.PaymentTypeEnum}
	 * @param loginUser {@link LoginUser}
	 * @return result
	 */
	@GetMapping("/payments/details/{type}")
	public ReturnDTO<Object> getBindingPaymentDetails(@PathVariable("type") Integer type, @ALoginUser LoginUser loginUser) {
		TPaymentMode bindingPaymentDetails = itPaymentModeService.getBindingPaymentDetails(type, loginUser);
		return ReturnDTO.ok(bindingPaymentDetails);
	}

	/**
	 * 启用或禁用付款方式
	 *
	 * @param id 付款方式id
	 * @param loginUser {@link LoginUser}
	 * @return result
	 */
	@PostMapping("/action/enable_or_disable/{id}")
	public ReturnDTO<Object> enableOrDisablePayment(@PathVariable("id") Long id, @ALoginUser LoginUser loginUser) {
		Integer status = itPaymentModeService.enableOrDisablePayment(id, loginUser);
		String msg = TPaymentMode.StatusEnum.ENABLE.getCode().equals(status) ? "啓用成功" : "禁用成功";
		return ReturnDTO.ok(msg);
	}

	/**
	 * 解绑付款方式
	 *
	 * @param id 付款方式id
	 * @param loginUser {@link LoginUser}
	 * @return result
	 */
	@PostMapping("/action/un_binding/{id}")
	public ReturnDTO<Object> unBindingPayment(@PathVariable("id") Long id, @ALoginUser LoginUser loginUser) {
		Boolean result = itPaymentModeService.unBindingPayment(id, loginUser);
		return result ? ReturnDTO.ok("解綁成功") : ReturnDTO.error("解綁失敗");
	}

}
