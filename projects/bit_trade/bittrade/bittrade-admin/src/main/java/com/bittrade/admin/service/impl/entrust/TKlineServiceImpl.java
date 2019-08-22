package com.bittrade.admin.service.impl.entrust;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTKlineServiceImpl;
import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.vo.TKlineVO;
import com.bittrade.pojo.model.TKline;
import com.bittrade.admin.dao.entrust.ITKlineDAO;
import com.bittrade.admin.service.entrust.ITKlineService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TKlineServiceImpl extends DefaultTKlineServiceImpl<ITKlineDAO, TKline, TKlineDTO, TKlineVO> implements ITKlineService {
	
}
