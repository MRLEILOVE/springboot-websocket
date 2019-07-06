package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.DAO.IDefaultTCurrencyAddressDAO;
import com.bittrade.api.__default.service.impl.DefaultTCurrencyAddressServiceImpl;
import com.bittrade.api.service.ITCurrencyAddressService;
import com.bittrade.pojo.dto.TCurrencyAddressDTO;
import com.bittrade.pojo.model.TCurrencyAddress;
import com.bittrade.pojo.vo.TCurrencyAddressVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TCurrencyAddressServiceImpl extends DefaultTCurrencyAddressServiceImpl<IDefaultTCurrencyAddressDAO, TCurrencyAddress, TCurrencyAddressDTO, TCurrencyAddressVO> implements ITCurrencyAddressService {
	
}
