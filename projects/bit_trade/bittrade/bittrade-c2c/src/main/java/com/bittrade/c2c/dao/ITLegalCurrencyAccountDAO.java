package com.bittrade.c2c.dao;

import com.bittrade.__default.DAO.IDefaultTLegalCurrencyAccountDAO;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.bittrade.pojo.vo.AssetsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface ITLegalCurrencyAccountDAO extends IDefaultTLegalCurrencyAccountDAO {

    /**
     * 获取用户所有的法币钱包
     *
     * @param userId 用户id
     * @return
     */
    List<AssetsVO> getAssets(@Param("userId") Long userId);

    /**
     * 查询用户钱包可用余额
     *
     * @param userId   用户id
     * @param coinName 币种名称
     * @return
     */
    String availableBalance(@Param("userId") Long userId, @Param("coinName") String coinName);

    /**
     * 根据 userId 、coinName 获取法币账户
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/8/20 14:19
     *
     * @param userId   : 用户id
     * @param coinName : 币名
     * @return 法币账户
     */
    TLegalCurrencyAccount getByUserIdAndCoinName(@Param("userId") Long userId, @Param("coinName") String coinName);
}
