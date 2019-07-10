package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTTickerServiceImpl;
import com.bittrade.currency.api.service.ITTickerService;
import com.bittrade.currency.dao.ITTickerDAO;
import com.bittrade.pojo.dto.TTickerDTO;
import com.bittrade.pojo.vo.TTickerVO;
import com.bittrade.pojo.model.TTicker;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TTickerServiceImpl extends DefaultTTickerServiceImpl<ITTickerDAO, TTicker, TTickerDTO, TTickerVO> implements ITTickerService {
	
}
