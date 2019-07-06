package com.bittrade.currency.dao;

import com.bittrade.api.__default.DAO.IDefaultTEntrustDAO;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.vo.TEntrustInfoVO;
import com.bittrade.pojo.vo.TEntrustVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITEntrustDAO extends IDefaultTEntrustDAO {

    /**
     * 查询用户当前委托
     */
    List<TEntrustVO> queryPresentEntrustByUserId(@Param("userId") String userId);

    /**
     * 查询用户历史委托
     */
    List<TEntrustVO> queryHistoryEntrustByUserId(@Param("userId") String userId);

    /**
     * 查询用户的委托单成交明细
     */
    TEntrustInfoVO queryEntrustInfoByUserId(@Param("userId") String userId, @Param("entrustId") String entrustId);

    /**
     * 撤单
     */
    int killOrder(TEntrust tEntrust);
}
