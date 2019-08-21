package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTAdvertOrderServiceImpl;
import com.bittrade.currency.dao.ITAdvertOrderDAO;
import com.bittrade.pojo.dto.TAdvertOrderDTO;
import com.bittrade.pojo.vo.TAdvertOrderVO;
import com.bittrade.pojo.model.TAdvertOrder;
import com.bittrade.currency.api.service.ITAdvertOrderService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TAdvertOrderServiceImpl extends DefaultTAdvertOrderServiceImpl<ITAdvertOrderDAO, TAdvertOrder, TAdvertOrderDTO, TAdvertOrderVO> implements ITAdvertOrderService {
	
}
