package com.bittrade.c2c.service.impl;

import com.bittrade.__default.service.IDefaultTLegalCurrencyAccountService;
import com.bittrade.__default.service.IDefaultTLegalCurrencyCoinService;
import com.bittrade.__default.service.impl.DefaultTAdvertInfoServiceImpl;
import com.bittrade.c2c.dao.ITAdvertInfoDAO;
import com.bittrade.c2c.service.ITAdvertInfoService;
import com.bittrade.c2c.service.ITLegalCurrencyAccountService;
import com.bittrade.c2c.service.ITLegalCurrencyCoinService;
import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.bittrade.pojo.vo.AdvertInfoVO;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.core.web.common.entity.LoginUser;
import com.core.web.common.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

/**
 * @author Administrator
 */
@Service
public class TAdvertInfoServiceImpl extends DefaultTAdvertInfoServiceImpl<ITAdvertInfoDAO, TAdvertInfo, TAdvertInfoDTO, TAdvertInfoVO> implements ITAdvertInfoService {

	@Autowired
	private ITLegalCurrencyAccountService itLegalCurrencyAccountService;

	@Autowired
	private ITLegalCurrencyCoinService itLegalCurrencyCoinService;

	@Override
	public Boolean publishAdvert(LoginUser user, AdvertInfoVO advertInfoVO) {
		if (advertInfoVO.isFloatPricing()) {
			// 浮动定价方式
			if (Objects.isNull(advertInfoVO.getFloatingRatio())) {
				throw new BusinessException("浮動比例必填");
			}
			if (Objects.isNull(advertInfoVO.getHidePrice())) {
				throw new BusinessException("隱藏價格必填");
			}
			advertInfoVO.setPrice(null);
		} else {
			// 固定价格方式
			advertInfoVO.setFloatingRatio(null);
			advertInfoVO.setHidePrice(null);
		}

		if (advertInfoVO.isBuyType()) {
			// 发布购买，购买无付款时间限制，无数量限制
			advertInfoVO.setPaymentTime(null);
		} else {
			// 发布出售
			// 验证账户可用数量
			TLegalCurrencyCoin coin = itLegalCurrencyCoinService.getById(advertInfoVO.getCoinId());
			TLegalCurrencyAccount currencyAccount = itLegalCurrencyAccountService.getByUserIdAndCoinName(user.getUser_id(), coin.getName());
			if (advertInfoVO.getAmount().compareTo(currencyAccount.getBalanceAmount()) > 0) {
				throw new BusinessException("賬戶可用餘額不足");
			}
		}
		// 验证支付密码
		if (!user.checkPayPassWord(advertInfoVO.getPayPassword())) {
			throw new BusinessException("支付密碼錯誤");
		}
		TAdvertInfo advertInfo = TAdvertInfo.builder().build();
		BeanUtils.copyProperties(advertInfoVO, advertInfo);
		// 填充其余参数
		advertInfo.setUserId(user.getUser_id())
				.setFloatingRatio(BigDecimal.valueOf(advertInfoVO.getFloatingRatio()).divide(BigDecimal.TEN.multiply(BigDecimal.TEN), 2, RoundingMode.DOWN))
				.setBalanceAmount(advertInfoVO.getAmount())
				// TODO 支付方式待改
				.setPaymentMethodId(advertInfoVO.getPaymentMethodId().stream().findFirst().get())
				.setStatus(TAdvertInfo.StatusEnum.PROCESSING.getCode())
				.setPaymentTime(Objects.nonNull(advertInfoVO.getPaymentTime()) ? LocalDateTime.now().plusMinutes(advertInfoVO.getPaymentTime().longValue()) : null)
				.setRegisteredTime(advertInfoVO.getRegisteredTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		try {
			baseMapper.insert(advertInfo);
			return true;
		} catch (Exception e) {
			log.error("发布广告异常：", e);
			return false;
		}
	}
}
