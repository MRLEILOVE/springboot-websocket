package com.bittrade.entrust.dao;

import java.util.List;

import com.bittrade.__default.DAO.IDefaultTKlineDAO;
import com.bittrade.pojo.dto.QueryKLineDto;
import com.bittrade.pojo.vo.QueryKLineVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITKlineDAO extends IDefaultTKlineDAO {

    /**
     * k线查询
     */
    List<QueryKLineVO> queryKLine(QueryKLineDto queryKLineDto);
}
