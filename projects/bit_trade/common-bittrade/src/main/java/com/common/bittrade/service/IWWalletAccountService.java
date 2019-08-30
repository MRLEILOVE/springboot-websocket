package com.common.bittrade.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.pojo.model.WWalletAccount;
import com.bittrade.pojo.vo.AssetsVO;
import com.bittrade.pojo.vo.ConversionVo;

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

    /**
     * 总资金折合
     * @param userId 用户Id
     * @return
     */
    ConversionVo totalConversion(Long userId);

    /**
     * 用户钱包列表
     * @param userId 用户id
     * @return 钱包列表
     */
    List<AssetsVO> detail(Long userId);
}
