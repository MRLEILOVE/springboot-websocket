package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.DAO.IDefaultTTickerDAO;
import com.bittrade.api.__default.service.impl.DefaultTTickerServiceImpl;
import com.bittrade.api.service.ITTickerService;
import com.bittrade.pojo.dto.TTickerDTO;
import com.bittrade.pojo.model.TTicker;
import com.bittrade.pojo.vo.TTickerVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TTickerServiceImpl extends DefaultTTickerServiceImpl<IDefaultTTickerDAO, TTicker, TTickerDTO, TTickerVO> implements ITTickerService {
	
}
