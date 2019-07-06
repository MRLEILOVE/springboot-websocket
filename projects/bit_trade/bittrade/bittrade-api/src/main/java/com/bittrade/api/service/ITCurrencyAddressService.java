package com.bittrade.api.service;

import com.bittrade.api.__default.service.IDefaultTCurrencyAddressService;
import com.bittrade.api.__default.DAO.IDefaultTCurrencyAddressDAO;
import com.bittrade.pojo.dto.TCurrencyAddressDTO;
import com.bittrade.pojo.vo.TCurrencyAddressVO;
import com.bittrade.pojo.model.TCurrencyAddress;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyAddressService extends IDefaultTCurrencyAddressService<TCurrencyAddress, TCurrencyAddressDTO, TCurrencyAddressVO, IDefaultTCurrencyAddressDAO> {
	
}
