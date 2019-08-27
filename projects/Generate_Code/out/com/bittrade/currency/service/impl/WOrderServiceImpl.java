package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWOrderServiceImpl;
import com.bittrade.currency.dao.IWOrderDAO;
import com.bittrade.pojo.dto.WOrderDTO;
import com.bittrade.pojo.vo.WOrderVO;
import com.bittrade.pojo.model.WOrder;
import com.bittrade.currency.api.service.IWOrderService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WOrderServiceImpl extends DefaultWOrderServiceImpl<IWOrderDAO, WOrder, WOrderDTO, WOrderVO> implements IWOrderService {
	
}
