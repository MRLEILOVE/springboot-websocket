package com.bittrade.c2c.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bittrade.__default.service.impl.DefaultTPaymentModeServiceImpl;
import com.bittrade.c2c.dao.ITPaymentModeDAO;
import com.bittrade.c2c.service.ITPaymentModeService;
import com.bittrade.pojo.model.TPaymentMode;
import com.bittrade.pojo.vo.BindingAliPayVO;
import com.bittrade.pojo.vo.BindingBankCardVO;
import com.bittrade.pojo.vo.BindingWeChartVO;
import com.core.web.constant.entity.LoginUser;
import com.core.web.constant.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 收款方式
 *
 * @author Administrator
 */
@Service
public class TPaymentModeServiceImpl extends DefaultTPaymentModeServiceImpl<ITPaymentModeDAO> implements ITPaymentModeService {

	/**
	 * 绑定银行卡
	 *
	 * @param bindingBankCardVO {@link BindingBankCardVO}
	 * @return result
	 */
	@Override
	public boolean bindingBankCard(BindingBankCardVO bindingBankCardVO, LoginUser loginUser) {
		// 验证银行卡是否已存在，每个用户只能绑定一张
		checkPaymentModeExistence(loginUser, TPaymentMode.PaymentTypeEnum.BANK_CARD);
		// 构建参数
		TPaymentMode paymentMode = TPaymentMode.builder().build();
		BeanUtils.copyProperties(bindingBankCardVO, paymentMode);
		/*填充其他参数*/
		paymentMode.setUserId(loginUser.getUser_id())
				.setType(TPaymentMode.PaymentTypeEnum.BANK_CARD.getCode())
				.setStatus(TPaymentMode.StatusEnum.ENABLE.getCode())
				.setCreateTime(LocalDateTime.now())
				.setUpdateTime(LocalDateTime.now());
		return baseMapper.add(paymentMode) > 0;
	}

	/**
	 * 绑定微信支付
	 *
	 * @param bindingWeChartVO {@link BindingWeChartVO}
	 * @return result
	 */
	@Override
	public boolean bindingWeChart(BindingWeChartVO bindingWeChartVO, LoginUser loginUser) {
		return false;
	}

	/**
	 * 绑定支付宝
	 *
	 * @param bindingAliPayVO {@link BindingAliPayVO}
	 * @return result
	 */
	@Override
	public boolean bindingAliPay(BindingAliPayVO bindingAliPayVO, LoginUser loginUser) {
		return false;
	}

	/**
	 * 验证绑定的付款方式是否已存在，每个用户每种付款方式只能绑定一个
	 * @param loginUser {@link LoginUser}
	 * @param paymentTypeEnum {@link TPaymentMode.PaymentTypeEnum}
	 */
	private void checkPaymentModeExistence(LoginUser loginUser, TPaymentMode.PaymentTypeEnum paymentTypeEnum) {
		// 验证绑定的付款方式是否已存在，每个用户每种付款方式只能绑定一个
		Integer count = baseMapper.selectCount(Wrappers.<TPaymentMode>lambdaQuery()
				.eq(TPaymentMode::getUserId, loginUser.getUser_id())
				.eq(TPaymentMode::getType, paymentTypeEnum.getCode()));
		if (count > 0) {
			throw new BusinessException("收款方式已存在，請勿重複綁定");
		}
	}

}
