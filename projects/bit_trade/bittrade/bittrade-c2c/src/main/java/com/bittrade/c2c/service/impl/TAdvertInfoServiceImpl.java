package com.bittrade.c2c.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.service.impl.DefaultTAdvertInfoServiceImpl;
import com.bittrade.c2c.dao.ITAdvertInfoDAO;
import com.bittrade.c2c.service.ITAdvertInfoService;
import com.bittrade.c2c.service.ITAdvertOrderService;
import com.bittrade.c2c.service.ITLegalCurrencyAccountService;
import com.bittrade.c2c.service.ITLegalCurrencyCoinService;
import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.model.TAdvertOrder;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.bittrade.pojo.vo.AdvertInfoVO;
import com.bittrade.pojo.vo.AdvertUserVO;
import com.bittrade.pojo.vo.QueryAdvertVO;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.core.web.constant.entity.LoginUser;
import com.core.web.constant.exception.BusinessException;
import com.netflix.discovery.converters.Auto;
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
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@Service
public class TAdvertInfoServiceImpl extends DefaultTAdvertInfoServiceImpl<ITAdvertInfoDAO, TAdvertInfo, TAdvertInfoDTO, TAdvertInfoVO> implements ITAdvertInfoService {

	@Autowired
	private ITLegalCurrencyAccountService itLegalCurrencyAccountService;

	@Autowired
	private ITLegalCurrencyCoinService itLegalCurrencyCoinService;

	@Autowired
	private ITAdvertOrderService itAdvertOrderService;


	/**
	 * 发布广告
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
					.setPaymentTime(Objects.nonNull(advertInfoVO.getPaymentTime()) ? advertInfoVO.getPaymentTime() : null)
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
	 * 获取广告列表
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 19:52
	 *
	 * @param page             : {@link Page}
	 * @param queryAdvertVO : {@link QueryAdvertVO}
	 * @param loginUser        : {@link LoginUser}
	 * @return result
	 */
	@Override
	public IPage<TAdvertInfo> listAdverts(Page<TAdvertInfo> page, QueryAdvertVO queryAdvertVO, LoginUser loginUser) {
		LambdaQueryWrapper<TAdvertInfo> lambdaQueryWrapper = new LambdaQueryWrapper<TAdvertInfo>().eq(TAdvertInfo::getCoinId, queryAdvertVO.getCoinId());
		lambdaQueryWrapper.eq(TAdvertInfo::getStatus, TAdvertInfo.StatusEnum.PROCESSING.getCode());
		if (queryAdvertVO.isBuyType()) {
			lambdaQueryWrapper.le(TAdvertInfo::getType, TAdvertInfo.AdvertTypeEnum.SELL.getCode());
		}
		if (queryAdvertVO.isSellType()) {
			lambdaQueryWrapper.le(TAdvertInfo::getType, TAdvertInfo.AdvertTypeEnum.BUY.getCode());
		}
		if (queryAdvertVO.getOnlyShowCanTransaction()) {
			// 认证等级 <= 登录用户 TODO 暂时只有一级
			lambdaQueryWrapper.le(TAdvertInfo::getCertificationLevel, loginUser.getCertificationLevel());
			// 注册时间 >= 用户注册时间 TODO 用户注册时间待获取
			lambdaQueryWrapper.ge(TAdvertInfo::getRegisteredTime, LocalDateTime.now().minusYears(10));
		}
		// 最小目标金额
		BigDecimal minTargetAmount = queryAdvertVO.getMinTargetAmount();
		lambdaQueryWrapper.le(Objects.nonNull(minTargetAmount), TAdvertInfo::getMinLimit, minTargetAmount);
		// 收款方式
		Long receiptWay = queryAdvertVO.getReceiptWay();
		lambdaQueryWrapper.eq(Objects.nonNull(receiptWay), TAdvertInfo::getPaymentMethodId, receiptWay);

		IPage<TAdvertInfo> advertInfoIPage = baseMapper.selectPage(page, lambdaQueryWrapper);
		// 广告列表
		List<TAdvertInfo> records = advertInfoIPage.getRecords();
		if (!CollectionUtils.isEmpty(records)) {
			// TODO 获取当前盘口价格
			BigDecimal currentHandicapPrice = BigDecimal.valueOf(10.1);
			Iterator<TAdvertInfo> iterator = records.iterator();
			while (iterator.hasNext()) {
				TAdvertInfo advertInfo = iterator.next();
				if (TAdvertInfo.PricingModeEnum.FLOAT.getCode().equals(advertInfo.getPricingMode())) {
					// 浮动交易价格 = 盘口价格 * 浮动比例
					advertInfo.setPrice(currentHandicapPrice.multiply(advertInfo.getFloatingRatio()));
					if (advertInfo.isBuyType()) {
						if (advertInfo.getPrice().compareTo(advertInfo.getHidePrice()) <= 0) {
							// 剔除 浮动交易价格 <= 隐藏价格 的广告
							iterator.remove();
						}
					}
					if (advertInfo.isSellType()) {
						if (advertInfo.getPrice().compareTo(advertInfo.getHidePrice()) >= 0) {
							// 剔除 浮动交易价格 >= 隐藏价格 的广告
							iterator.remove();
						}
					}
				}
			}
			// TODO 构建用户信息，远程调 jd 项目

			// 构建成交量、成交率
			records.forEach(record -> {
				String coinName = itLegalCurrencyCoinService.getById(record.getCoinId()).getName();
				TLegalCurrencyAccount account = itLegalCurrencyAccountService.getByUserIdAndCoinName(record.getUserId(), coinName);
				record.setCoinName(coinName);
				record.setC2cAlreadyDealCount(account.getC2cAlreadyDealCount());
				int c2cTotalCount = account.getC2cTotalCount();
				c2cTotalCount = c2cTotalCount  == 0 ? 1 : c2cTotalCount;
				record.setC2cTurnoverRate(BigDecimal.valueOf(account.getC2cAlreadyDealCount()).divide(BigDecimal.valueOf(c2cTotalCount), 2, RoundingMode.DOWN));
			});
		}
		return advertInfoIPage;
	}

	/**
	 * 获取登录用户广告列表
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 19:52
	 * @param page : {@link Page}
	 * @param coinId
	 * @param loginUser : {@link LoginUser}
	 * @return  result
	 */
	@Override
	public IPage<AdvertUserVO> listAdvertsUsers(Page<AdvertUserVO> page, Long coinId, LoginUser loginUser) {
		// TODO 获取当前盘口价格
		BigDecimal currentHandicapPrice = BigDecimal.valueOf(10.1);
		IPage<AdvertUserVO> advertsUsers = baseDAO.listAdvertsUsers(page, coinId, loginUser.getUser_id());
		// 处理浮动价格
		List<AdvertUserVO> collect = advertsUsers.getRecords().stream()
				.map(au -> au.getPricingMode().equals(TAdvertInfo.PricingModeEnum.FLOAT.getCode()) ? au.setPrice(currentHandicapPrice) : au).collect(Collectors.toList());
		return page.setRecords(collect);
	}

	/**
	 * 暂停广告
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/21 17:07
	 * @param advertIds : 多个广告 ids
	 * @param loginUser : {@link LoginUser}
	 * @return  result
	 */
	@Override
	public Boolean suspendAdverts(List<Long> advertIds, LoginUser loginUser) {
		try {
			advertIds.forEach(advertId -> {
				TAdvertInfo info = TAdvertInfo.builder().status(TAdvertInfo.StatusEnum.PAUSE.getCode()).build();
				LambdaUpdateWrapper<TAdvertInfo> wrapper = new LambdaUpdateWrapper<TAdvertInfo>()
						.eq(TAdvertInfo::getId, advertId)
						.eq(TAdvertInfo::getUserId, loginUser.getUser_id());
				baseMapper.update(info, wrapper);
			});
			return true;
		} catch (Exception e) {
			log.error("广告暂停异常：", e);
			return false;
		}
	}

	/**
	 * 撤销广告
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/21 17:14
	 * @param advertId : 广告 id
	 * @param loginUser : {@link LoginUser}
	 * @return  result
	 */
	@Override
	public Boolean revokeAdverts(Long advertId, LoginUser loginUser) {
		TAdvertInfo advertInfo = baseMapper.selectOne(new LambdaQueryWrapper<TAdvertInfo>().eq(TAdvertInfo::getId, advertId).eq(TAdvertInfo::getUserId, loginUser.getUser_id()));
		if (Objects.isNull(advertInfo)) {
			return false;
		}
		if (TAdvertInfo.StatusEnum.revoked.getCode().equals(advertInfo.getStatus())) {
			throw new BusinessException("請勿重複撤銷操作");
		}
		boolean revokeResult = advertInfo.update(new LambdaUpdateWrapper<TAdvertInfo>()
				.set(TAdvertInfo::getStatus, TAdvertInfo.StatusEnum.revoked.getCode())
				.eq(TAdvertInfo::getId, advertId)
		);
		// 出售需退还广告余额
		if (advertInfo.isSellType()) {
			// 广告余额
			BigDecimal balanceAmount = advertInfo.getBalanceAmount();
			// 用户账户可用 + balanceAmount，冻结 - balanceAmount
			Boolean returnResult = itLegalCurrencyAccountService.unFreezeAmount(loginUser.getUser_id(), advertInfo.getCoinId(), balanceAmount);
			return returnResult && revokeResult;
		}
		return revokeResult;
	}

	/**
	 * 获取广告详情
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/21 17:14
	 * @param advertId : 广告 id
	 * @return  result
	 */
	@Override
	public TAdvertInfo getAdvertDetails(Long advertId) {
		LambdaQueryWrapper<TAdvertInfo> lambdaQueryWrapper = new LambdaQueryWrapper<TAdvertInfo>()
				.eq(TAdvertInfo::getId, advertId)
				.eq(TAdvertInfo::getStatus, TAdvertInfo.StatusEnum.PROCESSING.getCode());
		TAdvertInfo info = baseMapper.selectOne(lambdaQueryWrapper);
		if (Objects.isNull(info)) {
			throw new BusinessException("廣告不存在，請刷新重試");
		}
		if (TAdvertInfo.PricingModeEnum.FLOAT.getCode().equals(info.getPricingMode())) {
			// TODO 获取当前盘口价格
			BigDecimal currentHandicapPrice = BigDecimal.valueOf(10.1);
			// 浮动交易价格 = 盘口价格 * 浮动比例
			info.setPrice(currentHandicapPrice.multiply(info.getFloatingRatio()));
			if (info.isBuyType()) {
				if (info.getPrice().compareTo(info.getHidePrice()) >= 0) {
					throw new BusinessException("廣告不存在，請刷新重試");
				}
			}
			if (info.isSellType()) {
				if (info.getPrice().compareTo(info.getHidePrice()) <= 0) {
					throw new BusinessException("廣告不存在，請刷新重試");
				}
			}
		}
		// TODO 构建用户信息，远程调 jd 项目

		// 构建成交量、成交率
		String coinName = itLegalCurrencyCoinService.getById(info.getCoinId()).getName();
		TLegalCurrencyAccount account = itLegalCurrencyAccountService.getByUserIdAndCoinName(info.getUserId(), coinName);
		info.setCoinName(coinName);
		info.setC2cAlreadyDealCount(account.getC2cAlreadyDealCount());
		int c2cTotalCount = account.getC2cTotalCount();
		c2cTotalCount = c2cTotalCount == 0 ? 1 : c2cTotalCount;
		info.setC2cTurnoverRate(BigDecimal.valueOf(account.getC2cAlreadyDealCount()).divide(BigDecimal.valueOf(c2cTotalCount), 2, RoundingMode.DOWN));

		// 付款时效，放币时效
		// 卖单：放币时效  买单：付款时效， 单位：秒，前端处理格式
		Long paymentOrPutCoinAging = itAdvertOrderService.getPaymentOrPutCoinAging(info.getUserId(), info.getType(), TAdvertOrder.StatusEnum.ALREADY_COMPLETE.getCode());
		info.setPaymentOrPutCoinAging(paymentOrPutCoinAging);
		return info;
	}
}
