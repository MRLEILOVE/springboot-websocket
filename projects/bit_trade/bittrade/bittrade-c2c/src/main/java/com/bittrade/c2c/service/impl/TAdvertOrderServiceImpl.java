package com.bittrade.c2c.service.impl;

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
}
