package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.DAO.IDefaultTCurrencyOptionalDAO;
import com.bittrade.api.__default.service.impl.DefaultTCurrencyOptionalServiceImpl;
import com.bittrade.api.service.ITCurrencyOptionalService;
import com.bittrade.pojo.dto.TCurrencyOptionalDTO;
import com.bittrade.pojo.model.TCurrencyOptional;
import com.bittrade.pojo.vo.TCurrencyOptionalVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TCurrencyOptionalServiceImpl extends DefaultTCurrencyOptionalServiceImpl<IDefaultTCurrencyOptionalDAO, TCurrencyOptional, TCurrencyOptionalDTO, TCurrencyOptionalVO> implements ITCurrencyOptionalService {
	
}
