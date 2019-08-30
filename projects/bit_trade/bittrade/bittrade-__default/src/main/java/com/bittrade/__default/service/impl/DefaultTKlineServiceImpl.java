/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.TKline;
import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.vo.TKlineVO;
import com.bittrade.__default.service.IDefaultTKlineService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultTKlineServiceImpl extends BaseServiceImpl<TKline, TKlineDTO, TKlineVO, ITKlineDAO> implements IDefaultTKlineService {
public abstract class DefaultTKlineServiceImpl<DAO extends IBaseDAO<TKline, TKlineDTO, TKlineVO>> extends BaseServiceImpl<TKline, TKlineDTO, TKlineVO, DAO> implements IDefaultTKlineService {
	
}
