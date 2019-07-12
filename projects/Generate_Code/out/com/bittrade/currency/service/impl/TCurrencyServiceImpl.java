package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTCurrencyServiceImpl;
import com.bittrade.currency.dao.ITCurrencyDAO;
import com.bittrade.pojo.dto.TCurrencyDTO;
import com.bittrade.pojo.vo.TCurrencyVO;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.currency.api.service.ITCurrencyService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TCurrencyServiceImpl extends DefaultTCurrencyServiceImpl<ITCurrencyDAO, TCurrency, TCurrencyDTO, TCurrencyVO> implements ITCurrencyService {
	
}
