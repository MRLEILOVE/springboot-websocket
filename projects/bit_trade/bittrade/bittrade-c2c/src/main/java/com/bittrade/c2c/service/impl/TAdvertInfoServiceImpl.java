package com.bittrade.c2c.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.bittrade.pojo.vo.QueryBuyAdvertVO;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.core.web.constant.entity.LoginUser;
import com.core.web.constant.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.List;
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


	/**
	 * 发布广告
	 * TODO 有待优化
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/19 14:43
	 *
	 * @param user         {@link LoginUser}
	 * @param advertInfoVO {@link AdvertInfoVO}
	 * @return result
	 */
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

		// 获取对应虚拟币
		TLegalCurrencyCoin coin = itLegalCurrencyCoinService.getById(advertInfoVO.getCoinId());

		// 广告交易数量
		BigDecimal amount = advertInfoVO.getAmount();
		// 发布广告最小数量
		if (amount.compareTo(coin.getMinQuota()) < 0) {
			throw new BusinessException(String.format("發布廣告數量最少需 %f 個", coin.getMinQuota()));
		}

		// 验证支付密码
		if (!user.checkPayPassWord(advertInfoVO.getPayPassword())) {
			throw new BusinessException("支付密碼錯誤");
		}

		if (advertInfoVO.isBuyType()) {
			// 发布购买，购买无付款时间限制
			advertInfoVO.setPaymentTime(null);
		} else {
			// 发布出售
			// 法幣账户
			TLegalCurrencyAccount currencyAccount = itLegalCurrencyAccountService.getByUserIdAndCoinName(user.getUser_id(), coin.getName());
			// 账户可用数量
			if (amount.compareTo(currencyAccount.getBalanceAmount()) > 0) {
				throw new BusinessException("賬戶可用餘額不足");
			}
			try {
				// 扣除账户余额  -> 余额 - 冻结 +
				itLegalCurrencyAccountService.freezeAmount(user.getUser_id(), advertInfoVO.getCoinId(), amount);
			} catch (Exception e) {
				log.error("发布广告异常：", e);
				throw new BusinessException("發布廣告失敗");
			}
		}

		try {
			TAdvertInfo advertInfo = TAdvertInfo.builder().build();
			BeanUtils.copyProperties(advertInfoVO, advertInfo);
			// 填充其余参数
			advertInfo.setUserId(user.getUser_id())
					.setFloatingRatio(Objects.nonNull(advertInfoVO.getFloatingRatio()) ? BigDecimal.valueOf(advertInfoVO.getFloatingRatio()).divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN) : null)
					.setBalanceAmount(amount)
					// TODO 支付方式待改
					.setPaymentMethodId(advertInfoVO.getPaymentMethodId().stream().findFirst().get())
					.setStatus(TAdvertInfo.StatusEnum.PROCESSING.getCode())
					.setPaymentTime(Objects.nonNull(advertInfoVO.getPaymentTime()) ? LocalDateTime.now().plusMinutes(advertInfoVO.getPaymentTime().longValue()) : null)
					.setRegisteredTime(advertInfoVO.getRegisteredTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			return baseMapper.insert(advertInfo) > 0;
		} catch (Exception e) {
			// 回滾账户余额  -> 余额 + 冻结 -
			if (advertInfoVO.isSellType()) {
				itLegalCurrencyAccountService.unFreezeAmount(user.getUser_id(), advertInfoVO.getCoinId(), amount);
			}
			log.error("发布广告异常：", e);
			throw new BusinessException("發布廣告失敗");
		}
	}

	/**
	 * 获取购买广告列表
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 19:52
	 *
	 * @param page             : {@link Page}
	 * @param queryBuyAdvertVO : {@link QueryBuyAdvertVO}
	 * @param loginUser        : {@link LoginUser}
	 * @return result
	 */
	@Override
	public IPage<TAdvertInfo> listBuyAdverts(Page<TAdvertInfo> page, QueryBuyAdvertVO queryBuyAdvertVO, LoginUser loginUser) {
		LambdaQueryWrapper<TAdvertInfo> lambdaQueryWrapper = new LambdaQueryWrapper<TAdvertInfo>()
				.eq(TAdvertInfo::getCoinId, queryBuyAdvertVO.getCoinId())
				.eq(TAdvertInfo::getType, TAdvertInfo.AdvertTypeEnum.SELL.getCode());
		if (queryBuyAdvertVO.getOnlyShowCanTransaction()) {
			// 认证等级 <= 登录用户 TODO 暂时只有一级
			lambdaQueryWrapper.le(TAdvertInfo::getCertificationLevel, loginUser.getCertificationLevel());
			// 注册时间 >= 用户注册时间 TODO 用户注册时间待获取
			lambdaQueryWrapper.ge(TAdvertInfo::getRegisteredTime, LocalDateTime.now());
		}
		// 最小目标金额
		BigDecimal minTargetAmount = queryBuyAdvertVO.getMinTargetAmount();
		lambdaQueryWrapper.le(Objects.nonNull(minTargetAmount), TAdvertInfo::getMinLimit, minTargetAmount);
		// 收款方式
		Long receiptWay = queryBuyAdvertVO.getReceiptWay();
		lambdaQueryWrapper.eq(Objects.nonNull(receiptWay), TAdvertInfo::getPaymentMethodId, receiptWay);

		IPage<TAdvertInfo> advertInfoIPage = baseMapper.selectPage(page, lambdaQueryWrapper);
		// 广告列表
		List<TAdvertInfo> records = advertInfoIPage.getRecords();
		if (!CollectionUtils.isEmpty(records)) {
			// TODO 获取当前盘口价格
			BigDecimal currentHandicapPrice = BigDecimal.valueOf(6.91);
			Iterator<TAdvertInfo> iterator = records.iterator();
			while (iterator.hasNext()) {
				TAdvertInfo advertInfo = iterator.next();
				if (TAdvertInfo.PricingModeEnum.FLOAT.getCode().equals(advertInfo.getPricingMode())) {
					// 浮动交易价格 = 盘口价格 * 浮动比例
					advertInfo.setPrice(currentHandicapPrice.multiply(advertInfo.getFloatingRatio()));
					if (advertInfo.getPrice().compareTo(advertInfo.getHidePrice()) <= 0) {
						// 剔除 浮动交易价格 <= 隐藏价格 的广告
						iterator.remove();
					}
				}
			}
			// TODO 构建用户信息，远程调 jd 项目

			// 构建成交量、成交率
			records.forEach(record -> {
				String coinName = itLegalCurrencyCoinService.getById(record.getCoinId()).getName();
				TLegalCurrencyAccount account = itLegalCurrencyAccountService.getByUserIdAndCoinName(loginUser.getUser_id(), coinName);
				record.setCoinName(coinName);
				record.setC2cAlreadyDealCount(account.getC2cAlreadyDealCount());
				record.setC2cTurnoverRate(BigDecimal.valueOf(account.getC2cAlreadyDealCount()).divide(BigDecimal.valueOf(account.getC2cTotalCount()), 2, RoundingMode.DOWN));
			});
		}
		return advertInfoIPage;
	}
}
