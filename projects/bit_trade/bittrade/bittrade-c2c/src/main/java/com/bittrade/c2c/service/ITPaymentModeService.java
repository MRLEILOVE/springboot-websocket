package com.bittrade.c2c.service;

import com.bittrade.__default.service.IDefaultTPaymentModeService;
import com.bittrade.pojo.model.TPaymentMode;
import com.bittrade.pojo.vo.BindingAliPayVO;
import com.bittrade.pojo.vo.BindingBankCardVO;
import com.bittrade.pojo.vo.BindingWeChartVO;
import com.core.web.constant.entity.LoginUser;

import java.util.List;

/**
 * 收款方式
 * @author Administrator
 *
 */
public interface ITPaymentModeService extends IDefaultTPaymentModeService {

	boolean bindingBankCard(BindingBankCardVO bindingBankCardVO, LoginUser loginUser);

	boolean bindingWeChart(BindingWeChartVO bindingWeChartVO, LoginUser loginUser);

	boolean bindingAliPay(BindingAliPayVO bindingAliPayVO, LoginUser loginUser);

	List<TPaymentMode> listAlreadyBindingPayment(LoginUser loginUser);

	TPaymentMode getBindingPaymentDetails(Integer type, LoginUser loginUser);

	Integer enableOrDisablePayment(Long id, LoginUser loginUser);

	Boolean unBindingPayment(Long id, LoginUser loginUser);
}
