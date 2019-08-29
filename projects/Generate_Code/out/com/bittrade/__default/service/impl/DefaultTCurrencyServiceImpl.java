/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.TCurrency;
import com.bittrade.pojo.dto.TCurrencyDTO;
import com.bittrade.pojo.vo.TCurrencyVO;
import com.bittrade.__default.service.IDefaultTCurrencyService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultTCurrencyServiceImpl extends BaseServiceImpl<TCurrency, TCurrencyDTO, TCurrencyVO, ITCurrencyDAO> implements IDefaultTCurrencyService {
public abstract class DefaultTCurrencyServiceImpl<DAO extends IBaseDAO<TCurrency, TCurrencyDTO, TCurrencyVO>> extends BaseServiceImpl<TCurrency, TCurrencyDTO, TCurrencyVO, DAO> implements IDefaultTCurrencyService {
	
}
