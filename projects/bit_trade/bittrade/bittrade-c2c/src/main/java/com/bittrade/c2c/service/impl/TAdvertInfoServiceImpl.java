package com.bittrade.c2c.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.vo.PublishAdvertVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.service.impl.DefaultTAdvertInfoServiceImpl;
import com.bittrade.c2c.dao.ITAdvertInfoDAO;
import com.bittrade.c2c.service.ITAdvertInfoService;
import com.bittrade.c2c.service.ITAdvertOrderService;
import com.bittrade.common.constant.ILegalCurrencyCoinConstants;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.model.TAdvertOrder;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.bittrade.pojo.vo.AdvertUserVO;
import com.bittrade.pojo.vo.QueryAdvertVO;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.common.bittrade.service.ITLegalCurrencyAccountService;
import com.common.bittrade.service.ITLegalCurrencyCoinService;
import com.core.tool.SnowFlake;
import com.core.web.constant.entity.LoginUser;
import com.core.web.constant.exception.BusinessException;

/**
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TAdvertInfoServiceImpl extends DefaultTAdvertInfoServiceImpl<ITAdvertInfoDAO, TAdvertInfo, TAdvertInfoDTO, TAdvertInfoVO> implements ITAdvertInfoService {

	@Resource
	private ITLegalCurrencyAccountService itLegalCurrencyAccountService;

	@Resource
	private ITLegalCurrencyCoinService itLegalCurrencyCoinService;

	@Resource
	private ITAdvertOrderService itAdvertOrderService;

	/**
	 * 发布广告
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/19 14:43
	 *
	 * @param user         {@link LoginUser}
	 * @param publishAdvertVO {@link PublishAdvertVO}
	 * @return result
	 */
	@Override
	public Boolean publishAdvert(LoginUser user, PublishAdvertVO publishAdvertVO) {
		// 一期只做固定价格，暂时注释，请勿删除
		/*if (publishAdvertVO.isFloatPricing()) {
			// 浮动定价方式
			if (Objects.isNull(publishAdvertVO.getFloatingRatio())) {
				throw new BusinessException("浮動比例必填");
			}
			if (Objects.isNull(publishAdvertVO.getHidePrice())) {
				throw new BusinessException("隱藏價格必填");
			}
			publishAdvertVO.setPrice(null);
		} else {*/
			// 固定价格方式
			publishAdvertVO.setFloatingRatio(null);
			publishAdvertVO.setHidePrice(null);
//		}

		// 获取对应虚拟币
		TLegalCurrencyCoin coin = itLegalCurrencyCoinService.getById(publishAdvertVO.getCoinId());
		if (coin.isDisable(coin.getStatus().intValue())) {
			throw new BusinessException(String.format("%s 已禁用，無法發布廣告", coin.getName()));
		}
		// 广告交易数量
		BigDecimal amount = publishAdvertVO.getAmount();
		// 发布广告最小数量
		if (amount.compareTo(coin.getMinQuota()) < 0) {
			throw new BusinessException(String.format("發布廣告數量最少需 %f 個", coin.getMinQuota()));
		}
		// 验证支付密码
		if (!user.checkPayPassWord(publishAdvertVO.getPayPassword())) {
			throw new BusinessException("支付密碼錯誤");
		}

		if (publishAdvertVO.isBuyType()) {
			// 发布购买，购买无付款时间限制
			publishAdvertVO.setPaymentTime(null);
		} else {
			// 发布出售
			// 法幣账户
			TLegalCurrencyAccount currencyAccount = itLegalCurrencyAccountService.getByUserIdAndCoinId(user.getUser_id(), coin.getId());
			// 账户可用数量
			if (amount.compareTo(currencyAccount.getBalanceAmount()) > 0) {
				throw new BusinessException("賬戶可用餘額不足");
			}
			try {
				// 扣除账户余额  -> 余额 - 冻结 +
				itLegalCurrencyAccountService.freezeAmount(user.getUser_id(), publishAdvertVO.getCoinId(), amount);
			} catch (Exception e) {
				log.error("发布广告异常：", e);
				throw new BusinessException("發布廣告失敗");
			}
		}

		try {
			TAdvertInfo advertInfo = TAdvertInfo.builder().build();
			BeanUtils.copyProperties(publishAdvertVO, advertInfo);
			// 填充其余参数
			advertInfo.setUserId(user.getUser_id())
					.setFloatingRatio(Objects.nonNull(publishAdvertVO.getFloatingRatio()) ? publishAdvertVO.getFloatingRatio().divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN) : null)
					.setBalanceAmount(amount)
					.setPaymentMethodId(StringUtils.join(publishAdvertVO.getPaymentMethodId().toArray(), ","))
					.setStatus(TAdvertInfo.StatusEnum.PROCESSING.getCode())
					.setPaymentTime(Objects.nonNull(publishAdvertVO.getPaymentTime()) ? publishAdvertVO.getPaymentTime() : null)
					.setRegisteredTime(publishAdvertVO.getRegisteredTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			return advertInfo.insert();
		} catch (Exception e) {
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
	 * @param page          : {@link Page}
	 * @param queryAdvertVO : {@link QueryAdvertVO}
	 * @param loginUser        : {@link LoginUser}
	 * @return result
	 */
	@Override
	public IPage<TAdvertInfo> listAdverts(Page<TAdvertInfo> page, QueryAdvertVO queryAdvertVO, LoginUser loginUser) {
		LambdaQueryWrapper<TAdvertInfo> lambdaQueryWrapper = new LambdaQueryWrapper<TAdvertInfo>()
				.eq(TAdvertInfo::getCoinId, queryAdvertVO.getCoinId())
				.eq(TAdvertInfo::getStatus, TAdvertInfo.StatusEnum.PROCESSING.getCode())
				.le(queryAdvertVO.isBuyType(), TAdvertInfo::getType, TAdvertInfo.AdvertTypeEnum.SELL.getCode())
				.le(queryAdvertVO.isSellType(), TAdvertInfo::getType, TAdvertInfo.AdvertTypeEnum.BUY.getCode())
				// 认证等级 <= 登录用户 TODO 暂时只有一级
				.le(queryAdvertVO.getOnlyShowCanTransaction(), TAdvertInfo::getCertificationLevel, loginUser.getCertificationLevel())
				// 注册时间 >= 用户注册时间 TODO 用户注册时间待获取
				.ge(queryAdvertVO.getOnlyShowCanTransaction(), TAdvertInfo::getRegisteredTime, LocalDateTime.now().minusYears(10))
				// 最小目标金额
				.le(Objects.nonNull(queryAdvertVO.getMinTargetAmount()), TAdvertInfo::getMinLimit, queryAdvertVO.getMinTargetAmount())
				// 收款方式
				.eq(Objects.nonNull(queryAdvertVO.getReceiptWay()), TAdvertInfo::getPaymentMethodId, queryAdvertVO.getReceiptWay());
		IPage<TAdvertInfo> advertInfoPage = baseMapper.selectPage(page.setSearchCount(false), lambdaQueryWrapper);
		// 广告列表
		List<TAdvertInfo> advertInfos = advertInfoPage.getRecords();
		if (!CollectionUtils.isEmpty(advertInfos)) {
			// TODO 获取当前盘口价格
			// 一期暂时不做浮动价格，暂时注释，请勿删除
			/*BigDecimal currentHandicapPrice = BigDecimal.valueOf(10.1);
			Iterator<TAdvertInfo> iterator = advertInfos.iterator();
			while (iterator.hasNext()) {
				TAdvertInfo advertInfo = iterator.next();
				if (TAdvertInfo.PricingModeEnum.FLOAT.getCode().equals(advertInfo.getPricingMode())) {
					// 浮动交易价格 = 盘口价格 * 浮动比例
					advertInfo.setPrice(currentHandicapPrice.multiply(advertInfo.getFloatingRatio()));
					if (TAdvertInfo.AdvertTypeEnum.BUY.getCode().equals(advertInfo.getType())) {
						if (advertInfo.getPrice().compareTo(advertInfo.getHidePrice()) <= 0) {
							// 剔除 浮动交易价格 <= 隐藏价格 的广告
							iterator.remove();
						}
					}
					if (TAdvertInfo.AdvertTypeEnum.SELL.getCode().equals(advertInfo.getType())) {
						if (advertInfo.getPrice().compareTo(advertInfo.getHidePrice()) >= 0) {
							// 剔除 浮动交易价格 >= 隐藏价格 的广告
							iterator.remove();
						}
					}
				}
			}*/
			// TODO 构建用户信息，远程调 jd 项目
			advertInfos.forEach(advertInfo -> {
				// 构建成交量、成交率
				buildVolumeAndRate(advertInfo);
				advertInfo.setCoinName(itLegalCurrencyCoinService.getById(advertInfo.getCoinId()).getName());
			});
		}
		return advertInfoPage;
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
		IPage<AdvertUserVO> advertsUsers = baseDAO.listAdvertsUsers(page.setSearchCount(false), coinId, loginUser.getUser_id());
		// 一期暂时不做浮动价格，暂时注释，请勿删除
		// TODO 获取当前盘口价格
		/*BigDecimal currentHandicapPrice = BigDecimal.valueOf(10.1);
		List<AdvertUserVO> collect = advertsUsers.getRecords().stream()
				.map(au -> au.isFloatingPrice() ? au.setPrice(currentHandicapPrice) : au).collect(Collectors.toList());
		page.setRecords(collect);*/
		return advertsUsers;
	}

	/**
	 * 暂停广告
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/21 17:07
	 *
	 * @param advertIds : 多个广告 ids
	 * @param loginUser : {@link LoginUser}
	 * @return result
	 */
	@Override
	public Boolean suspendAdverts(List<Long> advertIds, LoginUser loginUser) {
		// 是否存在未完成的订单
		if (advertIds.size() == 1) {
			boolean r = itAdvertOrderService.existenceNoCompleteOrders(advertIds.get(0));
			if (r) {
				throw new BusinessException("廣告存在爲完成的訂單，無法暫停");
			}
		}
		advertIds.forEach(advertId -> {
			boolean r = itAdvertOrderService.existenceNoCompleteOrders(advertId);
			if (r) {
				throw new BusinessException("部分廣告存在爲完成的訂單，無法暫停");
			}
		});
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
		// 是否存在未完成的订单
		if (itAdvertOrderService.existenceNoCompleteOrders(advertId)) {
			throw new BusinessException("廣告存在爲完成的訂單，無法撤銷");
		}
		if (TAdvertInfo.StatusEnum.revoked.getCode().equals(advertInfo.getStatus())) {
			throw new BusinessException("請勿重複撤銷操作");
		}
		boolean revokeResult = advertInfo.update(new LambdaUpdateWrapper<TAdvertInfo>()
				.set(TAdvertInfo::getStatus, TAdvertInfo.StatusEnum.revoked.getCode())
				.eq(TAdvertInfo::getId, advertId)
		);
		// 出售需退还广告余额
		if (TAdvertInfo.AdvertTypeEnum.SELL.getCode().equals(advertInfo.getType())) {
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
		TAdvertInfo advertInfo = getProcessingAdvert(advertId);
		// 一期暂时不做浮动价格，暂时注释，请勿删除
		/*if (advertInfo.isFloatingPrice()) {
			// TODO 获取当前盘口价格
			BigDecimal currentHandicapPrice = BigDecimal.valueOf(10.1);
			// 浮动交易价格 = 盘口价格 * 浮动比例
			advertInfo.setPrice(currentHandicapPrice.multiply(advertInfo.getFloatingRatio()));
			if (advertInfo.isBuyType()) {
				if (advertInfo.getPrice().compareTo(advertInfo.getHidePrice()) >= 0) {
					throw new BusinessException("廣告不存在，請刷新重試");
				}
			}
			if (advertInfo.isSellType()) {
				if (advertInfo.getPrice().compareTo(advertInfo.getHidePrice()) <= 0) {
					throw new BusinessException("廣告不存在，請刷新重試");
				}
			}
		}*/
		// TODO 构建用户信息，远程调 jd 项目
		// ....
		// 构建成交量、成交率
		buildVolumeAndRate(advertInfo);
		advertInfo.setCoinName(itLegalCurrencyCoinService.getById(advertInfo.getCoinId()).getName());
		// 付款时效，放币时效
		// 卖单：放币时效  买单：付款时效， 单位：秒，前端处理格式
		Long paymentOrPutCoinAging = itAdvertOrderService.getPaymentOrPutCoinAging(advertInfo.getUserId(), advertInfo.getType(), TAdvertOrder.StatusEnum.ALREADY_COMPLETE.getCode());
		advertInfo.setPaymentOrPutCoinAging(paymentOrPutCoinAging);
		return advertInfo;
	}

	/**
	 * 购买、出售操作（下购买、出售订单）
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 14:35
	 * @param advertId : 广告id
	 * @param amount : 数量
	 * @param payPassWord : 资金密码
	 * @param loginUser : {@link LoginUser}
	 * @return {@link TAdvertOrder}
	 */
	@Override
	public TAdvertOrder placeAdvertOrder(Long advertId, BigDecimal amount, String payPassWord, LoginUser loginUser) {
		TAdvertInfo advert = getProcessingAdvert(advertId);
		if (loginUser.getUser_id().equals(advert.getUserId())) {
			throw new BusinessException("不允許操作自己發布的廣告");
		}
		if (amount.compareTo(advert.getBalanceAmount()) > 0) {
			throw new BusinessException("數量超出廣告餘額");
		}
		// 固定、浮动价格不一样 一期暂时不做浮动价格，暂时注释，请勿删除
		/*if (advert.isFloatingPrice()) {
			// TODO 获取当前盘口价格
			BigDecimal currentHandicapPrice = BigDecimal.valueOf(10.1);
			advert.setPrice(currentHandicapPrice);
		}*/
		if (advert.isSellType()) {
			TLegalCurrencyAccount account = itLegalCurrencyAccountService.getByUserIdAndCoinId(loginUser.getUser_id(), advert.getCoinId());
			if (account.getBalanceAmount().compareTo(amount) < 0) {
				throw new BusinessException("賬戶可用餘額不足");
			}
			if (amount.multiply(advert.getPrice()).compareTo(advert.getMinLimit()) < 0) {
				throw new BusinessException(String.format("廣告最小限額：%f", advert.getMinLimit()));
			}
			if (amount.multiply(advert.getPrice()).compareTo(advert.getMaxLimit()) > 0) {
				throw new BusinessException(String.format("廣告最大限額：%f", advert.getMaxLimit()));
			}
			if (StringUtils.isBlank(payPassWord)) {
				throw new BusinessException("資金密碼必填");
			}
			if (!loginUser.checkPayPassWord(payPassWord)) {
				throw new BusinessException("資金密碼錯誤");
			}
			// 冻结用户账户可用余额
			itLegalCurrencyAccountService.freezeAmount(loginUser.getUser_id(), advert.getCoinId(), amount);
		}
		SnowFlake snowFlake = new SnowFlake(1, 1);
		TAdvertOrder order = TAdvertOrder.builder()
				.advertId(advertId)
				.coinId(advert.getCoinId())
				.orderNumber("C2C" + snowFlake.nextId())
				.paymentLegalCurrency(ILegalCurrencyCoinConstants.LEGAL_CURRENCY_COIN_NAME)
				.advertType(advert.getType())
				.publisherId(advert.getUserId())
				.build();
		if (advert.isBuyType()) {
			order.setBuyerId(advert.getUserId()).setSellerId(loginUser.getUser_id());
		}
		if (advert.isSellType()) {
			order.setBuyerId(loginUser.getUser_id()).setSellerId(advert.getUserId());
		}
		order.setTransactionAmout(amount.multiply(advert.getPrice()))
				.setTransactionNum(amount)
				.setTransactionPrice(advert.getPrice())
				.setRate(BigDecimal.ZERO)
				.setStatus(TAdvertOrder.StatusEnum.ALREADY_AUCTION.getCode())
				.setCancelOrderDeadline(LocalDateTime.now().plusMinutes(TAdvertOrder.CANCEL_ORDER_DURATION))
				.setArbitStatus(TAdvertOrder.ArbitStatusEnum.NO_ARBITRATION.getCode())
				.setOverdueTime(Objects.nonNull(advert.getPaymentTime()) ? LocalDateTime.now().plusMinutes(advert.getPaymentTime()) : null);
		// 保存订单
		boolean saveAdvertOrderResult = itAdvertOrderService.save(order);
		// 冻结广告余额， 剩余减，冻结加
		boolean updateAdvertInfoResult = this.update(new LambdaUpdateWrapper<TAdvertInfo>()
				.set(TAdvertInfo::getBalanceAmount, advert.getBalanceAmount().subtract(amount))
				.set(TAdvertInfo::getFreezeAmount, advert.getFreezeAmount().add(amount))
				.eq(TAdvertInfo::getId, advert.getId())
		);
		// TODO 发短信通知买家、卖家
		if (saveAdvertOrderResult && updateAdvertInfoResult) {
			// 返回订单详情，给前端展示
			return itAdvertOrderService.getAdvertOrderDetails(order.getId());
		}
		return null;
	}


	/**
	 * 获取进行中的广告
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 14:34
	 * @return  {@link TAdvertInfo}
	 */
	private TAdvertInfo getProcessingAdvert(Long advertId) {
		LambdaQueryWrapper<TAdvertInfo> lambdaQueryWrapper = new LambdaQueryWrapper<TAdvertInfo>()
				.eq(TAdvertInfo::getId, advertId)
				.eq(TAdvertInfo::getStatus, TAdvertInfo.StatusEnum.PROCESSING.getCode());
		TAdvertInfo info = baseMapper.selectOne(lambdaQueryWrapper);
		if (Objects.isNull(info)) {
			throw new BusinessException("廣告不存在，請刷新重試");
		}
		return info;
	}

	/**
	 * 构建成交量、成交率
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 16:34
	 * @param advertInfo : {@link TAdvertInfo}
	 * @return
	 */
	private void buildVolumeAndRate(TAdvertInfo advertInfo) {
		TLegalCurrencyAccount account = itLegalCurrencyAccountService.getByUserIdAndCoinId(advertInfo.getUserId(), advertInfo.getCoinId());
		advertInfo.setC2cAlreadyDealCount(account.getC2cAlreadyDealCount());
		int c2cTotalCount = account.getC2cTotalCount();
		c2cTotalCount = c2cTotalCount == 0 ? 1 : c2cTotalCount;
		advertInfo.setC2cTurnoverRate(BigDecimal.valueOf(account.getC2cAlreadyDealCount()).divide(BigDecimal.valueOf(c2cTotalCount), 2, RoundingMode.DOWN));
	}
}
