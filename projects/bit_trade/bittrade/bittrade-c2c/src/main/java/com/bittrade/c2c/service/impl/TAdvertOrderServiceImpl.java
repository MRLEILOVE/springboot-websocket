package com.bittrade.c2c.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bittrade.__default.service.impl.DefaultTAdvertOrderServiceImpl;
import com.bittrade.c2c.dao.ITAdvertOrderDAO;
import com.bittrade.c2c.service.ITAdvertInfoService;
import com.bittrade.c2c.service.ITAdvertOrderService;
import com.bittrade.pojo.dto.TAdvertOrderDTO;
import com.bittrade.pojo.model.TAdvertOrder;
import com.bittrade.pojo.vo.TAdvertOrderVO;
import com.common.bittrade.service.ITLegalCurrencyCoinService;
import com.core.tool.BeanUtil;

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

	@Autowired
	private ITLegalCurrencyCoinService itLegalCurrencyCoinService;

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
	public Long getPaymentOrPutCoinAging(Long userId, Byte type, Byte status) {
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
		advertOrderDTO.setPaymentMethodId(itAdvertInfoService.getById(advertOrder.getAdvertId()).getPaymentMethodId());
		return advertOrderDTO;
	}

	/**
	 * 取消订单
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 22:15
	 * @param orderId : 广告订单id
	 * @return
	 */
	@Override
	public boolean cancelAdvertOrder(Long orderId) {
		/*
		* TODO 取消订单
		* 判断是否在取消时间内
		* 判断是否已付款
		* 判断是否已完成
		* 判断是否已取消
		* 解冻广告余额， 剩余加，冻结减
		* */
		return false;
	}

}
