package com.bittrade.currency.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTKlineServiceImpl;
import com.bittrade.currency.api.service.ITKlineService;
import com.bittrade.currency.dao.ITKlineDAO;
import com.bittrade.pojo.dto.QueryKLineDto;
import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.model.TKline;
import com.bittrade.pojo.vo.QueryKLineVO;
import com.bittrade.pojo.vo.TKlineVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TKlineServiceImpl extends DefaultTKlineServiceImpl<ITKlineDAO, TKline, TKlineDTO, TKlineVO>
		implements ITKlineService {

	@Autowired
	private ITKlineDAO klineDAO;

	/**
	 * k线查询
	 */
	@Override
	public List<QueryKLineVO> queryKLine(QueryKLineDto queryKLineDto) {
		return klineDAO.queryKLine(queryKLineDto);
	}
}
