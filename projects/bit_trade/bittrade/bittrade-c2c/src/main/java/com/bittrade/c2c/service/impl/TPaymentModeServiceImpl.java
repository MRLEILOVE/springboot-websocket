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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 收款方式
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
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
				.setQrCode(null)
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
		// 验证微信是否已存在，每个用户只能绑定一个
		checkPaymentModeExistence(loginUser, TPaymentMode.PaymentTypeEnum.WE_CHAT);
		// 构建参数
		TPaymentMode paymentMode = TPaymentMode.builder().build();
		BeanUtils.copyProperties(bindingWeChartVO, paymentMode);
		/*填充其他参数*/
		paymentMode.setUserId(loginUser.getUser_id())
				.setType(TPaymentMode.PaymentTypeEnum.WE_CHAT.getCode())
				.setStatus(TPaymentMode.StatusEnum.ENABLE.getCode())
				.setBankName(null)
				.setBankBranch(null)
				.setCreateTime(LocalDateTime.now())
				.setUpdateTime(LocalDateTime.now());
		return baseMapper.add(paymentMode) > 0;
	}

	/**
	 * 绑定支付宝
	 *
	 * @param bindingAliPayVO {@link BindingAliPayVO}
	 * @return result
	 */
	@Override
	public boolean bindingAliPay(BindingAliPayVO bindingAliPayVO, LoginUser loginUser) {
		// 验证支付宝是否已存在，每个用户只能绑定一个
		checkPaymentModeExistence(loginUser, TPaymentMode.PaymentTypeEnum.ALI_PAY);
		// 构建参数
		TPaymentMode paymentMode = TPaymentMode.builder().build();
		BeanUtils.copyProperties(bindingAliPayVO, paymentMode);
		/*填充其他参数*/
		paymentMode.setUserId(loginUser.getUser_id())
				.setType(TPaymentMode.PaymentTypeEnum.ALI_PAY.getCode())
				.setStatus(TPaymentMode.StatusEnum.ENABLE.getCode())
				.setBankName(null)
				.setBankBranch(null)
				.setCreateTime(LocalDateTime.now())
				.setUpdateTime(LocalDateTime.now());
		return baseMapper.add(paymentMode) > 0;
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

	/**
	 * 获取用户已绑定付款方式
	 *
	 * @param loginUser {@link LoginUser}
	 * @return result
	 */
	@Override
	public List<TPaymentMode> listAlreadyBindingPayment(LoginUser loginUser) {
		return baseMapper.selectList(Wrappers.<TPaymentMode>lambdaQuery()
				.eq(TPaymentMode::getUserId, loginUser.getUser_id())
		);
	}

	/**
	 * 获取付款方式详情
	 *
	 * @param type {@link TPaymentMode.PaymentTypeEnum}
	 * @param loginUser {@link LoginUser}
	 * @return result
	 */
	@Override
	public TPaymentMode getBindingPaymentDetails(Integer type, LoginUser loginUser) {
		return baseMapper.selectOne(Wrappers.<TPaymentMode>lambdaQuery()
				.eq(TPaymentMode::getUserId, loginUser.getUser_id())
				.eq(TPaymentMode::getType, type)
		);
	}

	/**
	 * 启用或禁用付款方式
	 *
	 * @param id 付款方式id
	 * @param loginUser {@link LoginUser}
	 * @return result
	 */
	@Override
	public Integer enableOrDisablePayment(Long id, LoginUser loginUser) {
		TPaymentMode paymentMode = baseMapper.selectOne(Wrappers.<TPaymentMode>lambdaQuery()
				.select(TPaymentMode::getStatus, TPaymentMode::getId)
				.eq(TPaymentMode::getId, id)
				.eq(TPaymentMode::getUserId, loginUser.getUser_id())
		);
		if (Objects.isNull(paymentMode)) {
			throw new BusinessException("操作失敗，請稍候重試");
		}
		Integer status;
		if (TPaymentMode.StatusEnum.ENABLE.getCode().equals(paymentMode.getStatus())) {
			// 如果是启用的，则禁用
			status = TPaymentMode.StatusEnum.DISABLE.getCode();
		} else {
			status = TPaymentMode.StatusEnum.ENABLE.getCode();
		}
		paymentMode.setStatus(status);
		try {
			baseMapper.update(paymentMode, Wrappers.<TPaymentMode>lambdaUpdate().eq(TPaymentMode::getId, id));
		} catch (Exception e) {
			log.error("启用或禁用付款方式异常：", e);
			throw new BusinessException("操作失敗，請稍候重試");
		}
		return baseMapper.selectOne(Wrappers.<TPaymentMode>lambdaQuery()
				.select(TPaymentMode::getStatus)
				.eq(TPaymentMode::getId, id))
				.getStatus();
	}

	/**
	 * 解绑付款方式
	 *
	 * @param id 付款方式id
	 * @param loginUser {@link LoginUser}
	 * @return result
	 */
	@Override
	public Boolean unBindingPayment(Long id, LoginUser loginUser) {
		int result = baseMapper.delete(Wrappers.<TPaymentMode>lambdaUpdate()
				.eq(TPaymentMode::getId, id)
				.eq(TPaymentMode::getUserId, loginUser.getUser_id())
		);
		return result > 0;
	}


}
