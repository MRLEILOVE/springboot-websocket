package com.bittrade.admin.service.impl.currency;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTCurrencyTradeServiceImpl;
import com.bittrade.pojo.dto.TCurrencyTradeDTO;
import com.bittrade.pojo.vo.TCurrencyTradeVO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.admin.dao.currency.ITCurrencyTradeDAO;
import com.bittrade.admin.service.currency.ITCurrencyTradeService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TCurrencyTradeServiceImpl extends DefaultTCurrencyTradeServiceImpl<ITCurrencyTradeDAO, TCurrencyTrade, TCurrencyTradeDTO, TCurrencyTradeVO> implements ITCurrencyTradeService {
	
}
