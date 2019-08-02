package com.bittrade.currency.dao;

import com.bittrade.__default.DAO.IDefaultTCurrencyTradeDAO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.vo.TransactionPairVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyTradeDAO extends IDefaultTCurrencyTradeDAO {
    /**
     * 根据法币id查找交易对
     */
    List<TransactionPairVO> findTradeByCurrencyId2(@Param("currencyId2") String currencyId2,@Param("userId") String userId);

    /**
     * 获取一条优先级最高的交易对信息
     */
    TCurrencyTrade getOneOrderBySort();
}
