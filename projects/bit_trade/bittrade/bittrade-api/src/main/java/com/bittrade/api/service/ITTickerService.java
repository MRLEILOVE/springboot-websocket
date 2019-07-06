package com.bittrade.api.service;

import com.bittrade.api.__default.service.IDefaultTTickerService;
import com.bittrade.api.__default.DAO.IDefaultTTickerDAO;
import com.bittrade.pojo.dto.TTickerDTO;
import com.bittrade.pojo.vo.TTickerVO;
import com.bittrade.pojo.model.TTicker;

/**
 * 
 * @author Administrator
 *
 */
public interface ITTickerService extends IDefaultTTickerService<TTicker, TTickerDTO, TTickerVO, IDefaultTTickerDAO> {
	
}
