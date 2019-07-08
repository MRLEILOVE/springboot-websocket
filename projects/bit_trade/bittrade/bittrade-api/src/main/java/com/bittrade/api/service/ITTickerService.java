package com.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;

import com.bittrade.api.__default.service.IDefaultTTickerService;
import com.bittrade.pojo.dto.TTickerDTO;
import com.bittrade.pojo.vo.TTickerVO;
import com.bittrade.pojo.model.TTicker;

/**
 * 
 * @author Administrator
 *
 */
public interface ITTickerService<DAO extends IBaseDAO<TTicker, TTickerDTO, TTickerVO>> extends IDefaultTTickerService<TTicker, TTickerDTO, TTickerVO, DAO> {
	
}
