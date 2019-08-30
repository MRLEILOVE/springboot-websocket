package com.bittrade.admin.service.impl.currency;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTCurrencyServiceImpl;
import com.bittrade.pojo.dto.TCurrencyDTO;
import com.bittrade.pojo.vo.TCurrencyVO;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.admin.dao.currency.ITCurrencyDAO;
import com.bittrade.admin.service.currency.ITCurrencyService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TCurrencyServiceImpl extends DefaultTCurrencyServiceImpl<ITCurrencyDAO, TCurrency, TCurrencyDTO, TCurrencyVO> implements ITCurrencyService {
	
}
