package com.bittrade.c2c.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.service.impl.DefaultTAdvertOrderServiceImpl;
import com.bittrade.c2c.dao.ITAdvertOrderDAO;
import com.bittrade.c2c.service.ITAdvertInfoService;
import com.bittrade.c2c.service.ITAdvertOrderService;
import com.bittrade.pojo.dto.TAdvertOrderDTO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.model.TAdvertOrder;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.common.bittrade.service.ITLegalCurrencyAccountService;
import com.common.bittrade.service.ITLegalCurrencyCoinService;
import com.core.web.constant.entity.LoginUser;
import com.core.web.constant.exception.BusinessException;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TAdvertOrderServiceImpl extends DefaultTAdvertOrderServiceImpl<ITAdvertOrderDAO> implements ITAdvertOrderService {

	@Autowired
	private ITAdvertInfoService itAdvertInfoService;

	@Resource
	private ITLegalCurrencyCoinService itLegalCurrencyCoinService;

	@Resource
	private ITLegalCurrencyAccountService itLegalCurrencyAccountService;

	/**
	 * 获取用户付款时效，放币时效
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 09:49
	 * @param userId : 用户id
	 * @param type : {@link TAdvertOrder.AdvertTypeEnum}
	 * @param status : {@link TAdvertOrderDTO.StatusEnum}
	 * @return
	 */
	@Override
	public Long getPaymentOrPutCoinAging(Long userId, Integer type, Integer status) {
		return baseDAO.getPaymentOrPutCoinAging(userId, type, status);
	}

	/**
	 * 判断广告是否存在未完成的订单
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 16:14
	 * @param advertId : 广告id
	 * @return  if existence return true
	 */
	@Override
	public boolean existenceNoCompleteOrders(Long advertId) {
		Integer count = baseMapper.selectCount(new LambdaQueryWrapper<TAdvertOrder>()
				.eq(TAdvertOrder::getAdvertId, advertId)
				.and(advertOrder -> advertOrder
						.eq(TAdvertOrder::getStatus, TAdvertOrderDTO.StatusEnum.ALREADY_AUCTION.getCode())
						.or()
						.eq(TAdvertOrder::getStatus, TAdvertOrderDTO.StatusEnum.ALREADY_PAID.getCode())
						.or()
						.eq(TAdvertOrder::getStatus, TAdvertOrderDTO.StatusEnum.ALREADY_RECEIPT.getCode())
				)
		);
		return count > 0;
	}

	/**
	 * 获取订单详情
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 19:35
	 * @param orderId : 广告订单id
	 * @return  {@link TAdvertOrder}
	 */
	@Override
	public TAdvertOrder getAdvertOrderDetails(Long orderId) {
		TAdvertOrder advertOrder = baseMapper.getByPK(orderId);
		// 构建买家、卖家信息
		if (advertOrder.getAdvertType().equals(TAdvertOrderDTO.AdvertTypeEnum.SELL.getCode())) {
			// 卖家信息
			Long sellerId = advertOrder.getSellerId();
			// TODO 构建用户信息，远程调 jd 项目
		}
		if (advertOrder.getAdvertType().equals(TAdvertOrderDTO.AdvertTypeEnum.BUY.getCode())) {
			// 买家信息
			Long buyerId = advertOrder.getBuyerId();
			// TODO 构建用户信息，远程调 jd 项目
		}

		// 币名称
		advertOrder.setCoinName(itLegalCurrencyCoinService.getById(advertOrder.getCoinId()).getName());
		// 付款方式
		advertOrder.setPaymentMethodId(itAdvertInfoService.getById(advertOrder.getAdvertId()).getPaymentMethodId());
		return advertOrder;
	}

	/**
	 * 取消订单
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 22:15
	 * @param orderId : 广告订单id
	 * @return result
	 */
	@Override
	public boolean cancelAdvertOrder(Long orderId, LoginUser loginUser) {
		TAdvertOrder advertOrder = baseMapper.getByPK(orderId);
		if (TAdvertOrderDTO.StatusEnum.ALREADY_PAID.getCode().equals(advertOrder.getStatus())) {
			throw new BusinessException("訂單已付款，無法取消");
		}
		if (TAdvertOrderDTO.StatusEnum.ALREADY_COMPLETE.getCode().equals(advertOrder.getStatus())) {
			throw new BusinessException("訂單已完成，無法取消");
		}
		if (TAdvertOrderDTO.StatusEnum.ALREADY_CANCEL.getCode().equals(advertOrder.getStatus())) {
			throw new BusinessException("訂單已取消，請勿重複取消");
		}
		if (Objects.nonNull(advertOrder.getOverdueTime()) && LocalDateTime.now().isAfter(advertOrder.getOverdueTime())) {
			throw new BusinessException("訂單已超時自動取消，無法取消");
		}
		if (LocalDateTime.now().isAfter(advertOrder.getCancelOrderDeadline())) {
			throw new BusinessException("下單時間超過3分鐘，不可取消");
		}
		// 修改订单状态、取消者id
		boolean updateAdvertOrderStatusResult = this.update(new LambdaUpdateWrapper<TAdvertOrder>()
				.set(TAdvertOrder::getStatus, TAdvertOrderDTO.StatusEnum.ALREADY_CANCEL.getCode())
				.set(TAdvertOrder::getCancellerId, loginUser.getUser_id())
				.eq(TAdvertOrder::getId, advertOrder.getId())
		);
		// 订单交易数量
		BigDecimal transactionNum = advertOrder.getTransactionNum();

		/*解冻广告余额， 剩余加，冻结减*/
		TAdvertInfo advertInfo = itAdvertInfoService.getOne(new LambdaQueryWrapper<TAdvertInfo>()
				.select(TAdvertInfo::getBalanceAmount, TAdvertInfo::getFreezeAmount)
				.eq(TAdvertInfo::getId, advertOrder.getAdvertId())
		);
		boolean updateAdvertInfoResult = itAdvertInfoService.update(new LambdaUpdateWrapper<TAdvertInfo>()
				.set(TAdvertInfo::getBalanceAmount, advertInfo.getBalanceAmount().add(transactionNum))
				.set(TAdvertInfo::getFreezeAmount, advertInfo.getFreezeAmount().subtract(transactionNum))
				.eq(TAdvertInfo::getId, advertOrder.getAdvertId())
		);
		// 出售订单需解冻卖方用户可用余额
		if (advertOrder.isSellType()) {
			itLegalCurrencyAccountService.unFreezeAmount(advertOrder.getSellerId(), advertOrder.getCoinId(), transactionNum);
		}
		return updateAdvertOrderStatusResult && updateAdvertInfoResult;
	}


	/**
	 * 點擊已付款
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 22:15
	 * @param orderId : 广告订单id
	 * @return result
	 */
	@Override
	public boolean clickAlreadyPaid(Long orderId) {
		TAdvertOrder advertOrder = baseMapper.getByPK(orderId);
		if (TAdvertOrderDTO.StatusEnum.ALREADY_CANCEL.getCode().equals(advertOrder.getStatus())) {
			throw new BusinessException("訂單已取消，無法確認付款");
		}
		if (LocalDateTime.now().isAfter(advertOrder.getOverdueTime())) {
			throw new BusinessException("訂單已超時自動取消，無法確認付款");
		}
		if (TAdvertOrderDTO.StatusEnum.ALREADY_PAID.getCode().equals(advertOrder.getStatus())) {
			throw new BusinessException("已點擊付款，請勿重複操作");
		}
		TAdvertOrder order = TAdvertOrder.builder()
				.id(orderId)
				.status(TAdvertOrderDTO.StatusEnum.ALREADY_PAID.getCode()).build()
				.setPaymentTime(LocalDateTime.now());
		return updateById(order);
	}

	/**
	 * 點擊確認收款
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 22:15
	 * @param orderId : 广告订单id
	 * @return result
	 */
	@Override
	public boolean clickAlreadyReceipt(Long orderId) {
		TAdvertOrder advertOrder = baseMapper.getByPK(orderId);
		if (TAdvertOrderDTO.StatusEnum.ALREADY_CANCEL.getCode().equals(advertOrder.getStatus())) {
			throw new BusinessException("訂單已取消，無法確認收款");
		}
		if (LocalDateTime.now().isAfter(advertOrder.getOverdueTime())) {
			throw new BusinessException("訂單已超時自動取消，無法確認收款");
		}
		if (TAdvertOrderDTO.StatusEnum.ALREADY_COMPLETE.getCode().equals(advertOrder.getStatus())) {
			throw new BusinessException("訂單已完成，無法確認收款");
		}
		try {
			// 买家账户增加余额 记录 C2C已成交訂單数，总成交訂單数
			TLegalCurrencyAccount buyerAccount = itLegalCurrencyAccountService.getByUserIdAndCoinId(advertOrder.getBuyerId(), advertOrder.getCoinId());
			buyerAccount.setBalanceAmount(buyerAccount.getBalanceAmount().add(advertOrder.getTransactionNum()))
						.setC2cAlreadyDealCount(buyerAccount.getC2cAlreadyDealCount() + 1)
						.setC2cTotalCount(buyerAccount.getC2cTotalCount() + 1)
						.updateById();
			// 卖家账户减少冻结余额 记录 C2C已成交訂單数，总成交訂單数
			TLegalCurrencyAccount sellerAccount = itLegalCurrencyAccountService.getByUserIdAndCoinId(advertOrder.getSellerId(), advertOrder.getCoinId());
			sellerAccount.setFreezeAmount(sellerAccount.getFreezeAmount().subtract(advertOrder.getTransactionNum()))
						.setC2cAlreadyDealCount(sellerAccount.getC2cAlreadyDealCount() + 1)
						.setC2cTotalCount(sellerAccount.getC2cTotalCount() + 1)
						.updateById();
			// 广告冻结减少，已交易增加
			TAdvertInfo advertInfo = itAdvertInfoService.getByPK(advertOrder.getAdvertId());
			advertInfo.setFreezeAmount(advertInfo.getFreezeAmount().subtract(advertOrder.getTransactionNum()))
						.setAlreadyTransactionAmount(advertInfo.getAlreadyTransactionAmount().add(advertOrder.getTransactionNum()))
						.updateById();
			// 修改订单状态为已完成
			advertOrder.setStatus(TAdvertOrderDTO.StatusEnum.ALREADY_COMPLETE.getCode())
					.setGrantCoinTime(LocalDateTime.now())
					.updateById();
			// TODO 广告内余额为 0 怎么处理？？？
			return true;
		} catch (Exception e) {
			log.error("确认收款异常:", e);
			throw new BusinessException("收款失敗，請稍後重試");
		}
	}

	/**
	 * 获取订单列表
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 22:15
	 * @param page : {@link Page}
	 * @param loginUser : {@link LoginUser}
	 * @param status : {@link TAdvertOrderDTO.StatusEnum}
	 * @return result
	 */
	@Override
	public Page<TAdvertOrder> listAdvertOrders(Page<TAdvertOrder> page, LoginUser loginUser, Integer status) {
		return baseDAO.listAdvertOrders(page, loginUser.getUser_id(), status);
	}
}
