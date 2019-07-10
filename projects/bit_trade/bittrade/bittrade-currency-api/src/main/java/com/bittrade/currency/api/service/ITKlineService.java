package com.bittrade.currency.api.service;

import java.util.List;

import com.bittrade.__default.service.IDefaultTKlineService;
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
public interface ITKlineService extends IDefaultTKlineService<TKline, TKlineDTO, TKlineVO> {

    /**
     * k线查询
     */
    List<QueryKLineVO> queryKLine(QueryKLineDto queryKLineDto);
}