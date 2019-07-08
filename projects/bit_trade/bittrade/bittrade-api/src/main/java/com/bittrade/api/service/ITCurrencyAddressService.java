package com.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;

import com.bittrade.api.__default.service.IDefaultTCurrencyAddressService;
import com.bittrade.pojo.dto.TCurrencyAddressDTO;
import com.bittrade.pojo.vo.TCurrencyAddressVO;
import com.bittrade.pojo.model.TCurrencyAddress;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyAddressService<DAO extends IBaseDAO<TCurrencyAddress, TCurrencyAddressDTO, TCurrencyAddressVO>> extends IDefaultTCurrencyAddressService<TCurrencyAddress, TCurrencyAddressDTO, TCurrencyAddressVO, DAO> {
	
}
