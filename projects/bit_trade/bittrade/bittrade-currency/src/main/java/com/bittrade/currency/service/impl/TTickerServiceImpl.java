package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.service.impl.DefaultTTickerServiceImpl;
import com.bittrade.api.dao.ITTickerDAO;
import com.bittrade.pojo.dto.TTickerDTO;
import com.bittrade.pojo.vo.TTickerVO;
import com.bittrade.pojo.model.TTicker;
import com.bittrade.api.service.ITTickerService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TTickerServiceImpl extends DefaultTTickerServiceImpl<ITTickerDAO, TTicker, TTickerDTO, TTickerVO> implements ITTickerService {
	
}
