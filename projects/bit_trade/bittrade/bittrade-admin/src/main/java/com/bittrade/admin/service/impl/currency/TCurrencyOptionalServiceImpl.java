package com.bittrade.admin.service.impl.currency;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTCurrencyOptionalServiceImpl;
import com.bittrade.pojo.dto.TCurrencyOptionalDTO;
import com.bittrade.pojo.vo.TCurrencyOptionalVO;
import com.bittrade.pojo.model.TCurrencyOptional;
import com.bittrade.admin.dao.currency.ITCurrencyOptionalDAO;
import com.bittrade.admin.service.currency.ITCurrencyOptionalService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TCurrencyOptionalServiceImpl extends DefaultTCurrencyOptionalServiceImpl<ITCurrencyOptionalDAO, TCurrencyOptional, TCurrencyOptionalDTO, TCurrencyOptionalVO> implements ITCurrencyOptionalService {
	
}
