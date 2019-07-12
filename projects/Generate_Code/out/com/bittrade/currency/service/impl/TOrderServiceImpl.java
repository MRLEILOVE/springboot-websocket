package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTOrderServiceImpl;
import com.bittrade.currency.dao.ITOrderDAO;
import com.bittrade.pojo.dto.TOrderDTO;
import com.bittrade.pojo.vo.TOrderVO;
import com.bittrade.pojo.model.TOrder;
import com.bittrade.currency.api.service.ITOrderService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TOrderServiceImpl extends DefaultTOrderServiceImpl<ITOrderDAO, TOrder, TOrderDTO, TOrderVO> implements ITOrderService {
	
}
