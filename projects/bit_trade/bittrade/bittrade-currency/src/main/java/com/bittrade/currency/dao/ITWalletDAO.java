package com.bittrade.currency.dao;

import java.util.List;

import com.bittrade.pojo.vo.UserWalletVO;
import org.apache.ibatis.annotations.Param;

import com.bittrade.__default.DAO.IDefaultTWalletDAO;
import com.bittrade.pojo.vo.QueryWalletVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITWalletDAO extends IDefaultTWalletDAO {

    /**
     * 查询用户钱包
     */
    List<QueryWalletVO> queryByUserId(@Param("userId") Integer userId);

    /**
     * 查询用户钱包
     */
    UserWalletVO queryUserWallet(@Param("userId") Integer userId, @Param("currencyTradeId") Integer currencyTradeId);
}
