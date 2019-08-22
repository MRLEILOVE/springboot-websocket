package com.bittrade.c2c.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bittrade.__default.service.impl.DefaultTAdvertOrderServiceImpl;
import com.bittrade.c2c.dao.ITAdvertOrderDAO;
import com.bittrade.c2c.service.ITAdvertOrderService;
import com.bittrade.pojo.dto.TAdvertOrderDTO;
import com.bittrade.pojo.model.TAdvertOrder;
import com.bittrade.pojo.vo.TAdvertOrderVO;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TAdvertOrderServiceImpl extends DefaultTAdvertOrderServiceImpl<ITAdvertOrderDAO, TAdvertOrder, TAdvertOrderDTO, TAdvertOrderVO> implements ITAdvertOrderService {

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
						.eq(TAdvertOrder::getStatus, TAdvertOrder.StatusEnum.ALREADY_AUCTION.getCode())
						.or()
						.eq(TAdvertOrder::getStatus, TAdvertOrder.StatusEnum.ALREADY_PAID.getCode())
						.or()
						.eq(TAdvertOrder::getStatus, TAdvertOrder.StatusEnum.ALREADY_RECEIPT.getCode())
				)
		);
		return count > 0;
	}

}
