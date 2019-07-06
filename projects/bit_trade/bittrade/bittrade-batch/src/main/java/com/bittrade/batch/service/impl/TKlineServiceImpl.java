package com.bittrade.batch.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.dao.ITKlineDAO;
import com.bittrade.api.service.ITKlineService;
import com.bittrade.api.service.abs.AbstractTKlineServiceImpl;
import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.model.TKline;
import com.bittrade.pojo.vo.TKlineVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TKlineServiceImpl extends AbstractTKlineServiceImpl<ITKlineDAO, TKline, TKlineDTO, TKlineVO> implements ITKlineService {

}
