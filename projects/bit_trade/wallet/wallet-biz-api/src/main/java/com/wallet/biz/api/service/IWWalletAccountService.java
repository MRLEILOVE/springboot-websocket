package com.wallet.biz.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wallet.biz.pojo.model.WWalletAccount;

import java.math.BigDecimal;

/**
 * 
 * @author Administrator
 *
 */
public interface IWWalletAccountService extends IService<WWalletAccount> {

    /**
     * 通过用户ID跟币种id获取资金钱包
     * @param userId 用户id
     * @param currencyId 币种id
     * @return 资金账户钱包
     */
    WWalletAccount getAccount(Long userId, Integer currencyId);

    /**
     * 资金账户入账
     * @param fundAccount 资金账户
     * @param num 数量
     * @return
     */
    Integer fundAccountIn(WWalletAccount fundAccount, BigDecimal num);

    /**
     * 资金账户出账
     * @param fundAccount 资金账户
     * @param num 数量
     * @return
     */
    Integer fundAccountOut(WWalletAccount fundAccount, BigDecimal num);
}
