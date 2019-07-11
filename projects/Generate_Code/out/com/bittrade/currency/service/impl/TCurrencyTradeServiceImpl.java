package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTCurrencyTradeServiceImpl;
import com.bittrade.currency.dao.ITCurrencyTradeDAO;
import com.bittrade.pojo.dto.TCurrencyTradeDTO;
import com.bittrade.pojo.vo.TCurrencyTradeVO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.currency.api.service.ITCurrencyTradeService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TCurrencyTradeServiceImpl extends DefaultTCurrencyTradeServiceImpl<ITCurrencyTradeDAO, TCurrencyTrade, TCurrencyTradeDTO, TCurrencyTradeVO> implements ITCurrencyTradeService {
	
}
