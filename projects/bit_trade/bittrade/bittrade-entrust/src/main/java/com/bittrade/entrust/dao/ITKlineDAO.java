package com.bittrade.entrust.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.bittrade.__default.DAO.IDefaultTKlineDAO;
import com.bittrade.pojo.dto.QueryKLineDto;
import com.bittrade.pojo.vo.QueryKLineVO;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 查询k线
     * @param symbol 交易对
     * @param granularity 粒度
     * @param time 时间
     * @return
     */
    QueryKLineVO queryKLineByCondition(@Param("symbol") String symbol,@Param("granularity") Integer granularity,@Param("time") LocalDateTime time);
}
