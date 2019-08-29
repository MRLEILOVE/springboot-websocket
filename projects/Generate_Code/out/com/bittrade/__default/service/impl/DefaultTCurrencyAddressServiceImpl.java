/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.TCurrencyAddress;
import com.bittrade.pojo.dto.TCurrencyAddressDTO;
import com.bittrade.pojo.vo.TCurrencyAddressVO;
import com.bittrade.__default.service.IDefaultTCurrencyAddressService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultTCurrencyAddressServiceImpl extends BaseServiceImpl<TCurrencyAddress, TCurrencyAddressDTO, TCurrencyAddressVO, ITCurrencyAddressDAO> implements IDefaultTCurrencyAddressService {
public abstract class DefaultTCurrencyAddressServiceImpl<DAO extends IBaseDAO<TCurrencyAddress, TCurrencyAddressDTO, TCurrencyAddressVO>> extends BaseServiceImpl<TCurrencyAddress, TCurrencyAddressDTO, TCurrencyAddressVO, DAO> implements IDefaultTCurrencyAddressService {
	
}
