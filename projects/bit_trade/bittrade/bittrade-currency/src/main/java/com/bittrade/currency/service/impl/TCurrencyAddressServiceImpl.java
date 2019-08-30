package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTCurrencyAddressServiceImpl;
import com.bittrade.currency.api.service.ITCurrencyAddressService;
import com.bittrade.currency.dao.ITCurrencyAddressDAO;
import com.bittrade.pojo.dto.TCurrencyAddressDTO;
import com.bittrade.pojo.vo.TCurrencyAddressVO;
import com.bittrade.pojo.model.TCurrencyAddress;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TCurrencyAddressServiceImpl extends DefaultTCurrencyAddressServiceImpl<ITCurrencyAddressDAO, TCurrencyAddress, TCurrencyAddressDTO, TCurrencyAddressVO> implements ITCurrencyAddressService {
	
}
