package com.bittrade.currency.dao;

import com.bittrade.__default.DAO.IDefaultTCurrencyOptionalDAO;
import com.bittrade.pojo.vo.TransactionPairVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyOptionalDAO extends IDefaultTCurrencyOptionalDAO {
    /**
     * 查询用户自选的交易对
     */
    List<TransactionPairVO> findOptionalByUserId(@Param("userId") String userId);
}
