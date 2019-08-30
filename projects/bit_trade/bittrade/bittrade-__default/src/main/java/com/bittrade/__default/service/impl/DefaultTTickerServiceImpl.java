/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.TTicker;
import com.bittrade.pojo.dto.TTickerDTO;
import com.bittrade.pojo.vo.TTickerVO;
import com.bittrade.__default.service.IDefaultTTickerService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultTTickerServiceImpl extends BaseServiceImpl<TTicker, TTickerDTO, TTickerVO, ITTickerDAO> implements IDefaultTTickerService {
public abstract class DefaultTTickerServiceImpl<DAO extends IBaseDAO<TTicker, TTickerDTO, TTickerVO>> extends BaseServiceImpl<TTicker, TTickerDTO, TTickerVO, DAO> implements IDefaultTTickerService {
	
}
