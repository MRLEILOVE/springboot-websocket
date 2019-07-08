package com.bittrade.currency.dao;

import com.bittrade.api.__default.DAO.IDefaultTKlineDAO;
import com.bittrade.pojo.dto.QueryKLineDto;
import com.bittrade.pojo.vo.QueryKLineVO;
import org.springframework.stereotype.Repository;

import java.util.List;

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
