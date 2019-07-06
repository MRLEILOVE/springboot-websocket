package com.bittrade.api.service;

import com.bittrade.api.__default.service.IDefaultTKlineService;
import com.bittrade.api.dao.ITKlineDAO;
import com.bittrade.pojo.dto.QueryKLineDto;
import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.vo.QueryKLineVO;
import com.bittrade.pojo.vo.TKlineVO;
import com.bittrade.pojo.model.TKline;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITKlineService extends IDefaultTKlineService<TKline, TKlineDTO, TKlineVO, ITKlineDAO> {

    /**
     * k线查询
     */
    List<QueryKLineVO> queryKLine(QueryKLineDto queryKLineDto);
}
