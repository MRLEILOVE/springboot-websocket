package com.bittrade.currency.api.service;

import com.bittrade.__default.service.IDefaultTWalletService;
import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.pojo.vo.CoinAccountVO;
import com.bittrade.pojo.vo.TWalletVO;
import com.bittrade.pojo.vo.UserWalletVO;


/**
 * <p>
 * 虚拟币钱包表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-07-05
 */
public interface ITWalletService extends IDefaultTWalletService<TWallet, TWalletDTO, TWalletVO> {

    /**
     * 查询用户的币币账户
     * @param userId 用户id
     */
    CoinAccountVO queryCoinAccountByUserId(Integer userId);

    /**
     * 查询用户钱包
     * @param userId 用户id
     * @param currencyTradeId 交易对id
     */
    UserWalletVO queryUserWallet(Integer userId, Integer currencyTradeId);
}
