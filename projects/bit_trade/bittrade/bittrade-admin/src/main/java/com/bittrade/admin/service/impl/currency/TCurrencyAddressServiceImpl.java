package com.bittrade.admin.service.impl.currency;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTCurrencyAddressServiceImpl;
import com.bittrade.pojo.dto.TCurrencyAddressDTO;
import com.bittrade.pojo.vo.TCurrencyAddressVO;
import com.bittrade.pojo.model.TCurrencyAddress;
import com.bittrade.admin.dao.currency.ITCurrencyAddressDAO;
import com.bittrade.admin.service.currency.ITCurrencyAddressService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TCurrencyAddressServiceImpl extends DefaultTCurrencyAddressServiceImpl<ITCurrencyAddressDAO, TCurrencyAddress, TCurrencyAddressDTO, TCurrencyAddressVO> implements ITCurrencyAddressService {
	
}
