package com.bittrade.c2c.service.impl;

import com.common.bittrade.service.ITLegalCurrencyAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.bittrade.__default.service.impl.DefaultTAdvertOrderServiceImpl;
import com.bittrade.c2c.dao.ITAdvertOrderDAO;
import com.bittrade.c2c.service.ITAdvertInfoService;
import com.bittrade.c2c.service.ITAdvertOrderService;
import com.bittrade.pojo.dto.TAdvertOrderDTO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.model.TAdvertOrder;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.bittrade.pojo.vo.TAdvertOrderVO;
import com.core.web.constant.entity.LoginUser;
import com.core.web.constant.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.common.bittrade.service.ITLegalCurrencyCoinService;
import com.core.tool.BeanUtil;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TAdvertOrderServiceImpl extends DefaultTAdvertOrderServiceImpl<ITAdvertOrderDAO, TAdvertOrder, TAdvertOrderDTO, TAdvertOrderVO> implements ITAdvertOrderService {

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
	 * @param status : {@link TAdvertOrder.StatusEnum}
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
	public TAdvertOrderDTO getAdvertOrderDetails(Long orderId) {
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

		TAdvertOrderDTO advertOrderDTO = new TAdvertOrderDTO();
		BeanUtil.copyObj(advertOrder, advertOrderDTO);

		// 币名称
		advertOrderDTO.setCoinName(itLegalCurrencyCoinService.getById(advertOrder.getCoinId()).getName());
		// 付款方式
//		advertOrderDTO.setPaymentMethodId(itAdvertInfoService.getById(advertOrder.getAdvertId()).getPaymentMethodId());
		return advertOrderDTO;
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
	public boolean cancelAdvertOrder(Long orderId) {
		TAdvertOrder advertOrder = baseMapper.getByPK(orderId);
		if (LocalDateTime.now().isAfter(advertOrder.getCancelOrderDeadline())) {
			throw new BusinessException("下單時間超過3分鐘，不可取消");
		}
		if (advertOrder.isAlreadyPaid(advertOrder.getStatus())) {
			throw new BusinessException("訂單已付款，無法取消");
		}
		if (advertOrder.isAlreadyComplete(advertOrder.getStatus())) {
			throw new BusinessException("訂單已完成，無法取消");
		}
		if (advertOrder.isAlreadyCancel(advertOrder.getStatus())) {
			throw new BusinessException("訂單已取消，請勿重複取消");
		}
		if (LocalDateTime.now().isAfter(advertOrder.getOverdueTime())) {
			throw new BusinessException("訂單已超時自動取消，無法取消");
		}
		// 解冻广告余额， 剩余加，冻结减
		TAdvertInfo advertInfo = itAdvertInfoService.getByPK(advertOrder.getAdvertId());
		advertInfo.setBalanceAmount(advertInfo.getBalanceAmount().add(advertOrder.getTransactionNum()));
		advertInfo.setFreezeAmount(advertInfo.getFreezeAmount().subtract(advertOrder.getTransactionNum()));
		return itAdvertInfoService.updateById(advertInfo);
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
		if (advertOrder.isAlreadyCancel(advertOrder.getStatus())) {
			throw new BusinessException("訂單已取消，無法確認付款");
		}
		if (LocalDateTime.now().isAfter(advertOrder.getOverdueTime())) {
			throw new BusinessException("訂單已超時自動取消，無法確認付款");
		}
		TAdvertOrder order = TAdvertOrder.builder().id(orderId).status(TAdvertOrder.StatusEnum.ALREADY_PAID.getCode()).build();
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
		if (advertOrder.isAlreadyCancel(advertOrder.getStatus())) {
			throw new BusinessException("訂單已取消，無法確認收款");
		}
		if (LocalDateTime.now().isAfter(advertOrder.getOverdueTime())) {
			throw new BusinessException("訂單已超時自動取消，無法確認收款");
		}
		if (advertOrder.isAlreadyComplete(advertOrder.getStatus())) {
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
			advertOrder.setStatus(TAdvertOrder.StatusEnum.ALREADY_COMPLETE.getCode()).updateById();
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
	 * @param status : {@link TAdvertOrder.StatusEnum}
	 * @return result
	 */
	@Override
	public Page<TAdvertOrder> listAdvertOrders(Page<TAdvertOrder> page, LoginUser loginUser, Integer status) {
		return baseDAO.listAdvertOrders(page, loginUser.getUser_id(), status);
	}
}
