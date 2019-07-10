package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTCurrencyOptionalServiceImpl;
import com.bittrade.currency.dao.ITCurrencyOptionalDAO;
import com.bittrade.pojo.dto.TCurrencyOptionalDTO;
import com.bittrade.pojo.vo.TCurrencyOptionalVO;
import com.bittrade.pojo.model.TCurrencyOptional;
import com.bittrade.currency.api.service.ITCurrencyOptionalService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TCurrencyOptionalServiceImpl extends DefaultTCurrencyOptionalServiceImpl<ITCurrencyOptionalDAO, TCurrencyOptional, TCurrencyOptionalDTO, TCurrencyOptionalVO> implements ITCurrencyOptionalService {
	
}
