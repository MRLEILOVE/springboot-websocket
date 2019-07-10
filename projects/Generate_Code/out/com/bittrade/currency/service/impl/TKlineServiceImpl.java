package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTKlineServiceImpl;
import com.bittrade.currency.dao.ITKlineDAO;
import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.vo.TKlineVO;
import com.bittrade.pojo.model.TKline;
import com.bittrade.currency.api.service.ITKlineService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TKlineServiceImpl extends DefaultTKlineServiceImpl<ITKlineDAO, TKline, TKlineDTO, TKlineVO> implements ITKlineService {
	
}
