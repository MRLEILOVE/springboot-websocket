package com.bittrade.c2c.dao;

import com.bittrade.__default.DAO.IDefaultTLegalCurrencyAccountDAO;
import com.bittrade.pojo.vo.AssetsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITLegalCurrencyAccountDAO extends IDefaultTLegalCurrencyAccountDAO {

    /**
     * 获取用户所有的法币钱包
     * @param userId 用户id
     * @return
     */
    List<AssetsVO> getAssets(@Param("userId") Long userId);

    /**
     * 查询用户钱包可用余额
     * @param userId 用户id
     * @param coinName 币种名称
     * @return
     */
    String availableBalance(@Param("userId") Long userId, @Param("coinName") String coinName);
}
