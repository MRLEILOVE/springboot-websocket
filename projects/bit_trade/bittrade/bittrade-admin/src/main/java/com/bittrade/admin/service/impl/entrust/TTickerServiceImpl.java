package com.bittrade.admin.service.impl.entrust;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTTickerServiceImpl;
import com.bittrade.pojo.dto.TTickerDTO;
import com.bittrade.pojo.vo.TTickerVO;
import com.bittrade.pojo.model.TTicker;
import com.bittrade.admin.dao.entrust.ITTickerDAO;
import com.bittrade.admin.service.entrust.ITTickerService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TTickerServiceImpl extends DefaultTTickerServiceImpl<ITTickerDAO, TTicker, TTickerDTO, TTickerVO> implements ITTickerService {
	
}
