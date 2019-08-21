package com.wallet.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.core.tool.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.biz.api.service.IWWalletAccountService;
import com.wallet.biz.dao.IWWalletAccountDAO;
import com.wallet.biz.pojo.model.WWalletAccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Component
public class WWalletAccountServiceImpl extends ServiceImpl<IWWalletAccountDAO, WWalletAccount> implements IWWalletAccountService {
    private static final SnowFlake SNOW_FLAKE__ENTRUST	= new SnowFlake( 2, 2);
    @Autowired
    private IWWalletAccountDAO walletAccountDAO;

    /**
     * 通过用户ID跟币种id获取资金钱包
     * @param userId 用户id
     * @param currencyId 币种id
     * @return 资金账户钱包
     */
    @Override
    public WWalletAccount getAccount(Long userId, Integer currencyId) {
        QueryWrapper<WWalletAccount> wrapper = new QueryWrapper<>();
        wrapper.eq(WWalletAccount.FieldNames.USER_ID,userId)
                .eq(WWalletAccount.FieldNames.CURRENCY_ID,currencyId);
        WWalletAccount wWalletAccount = walletAccountDAO.selectOne(wrapper);

        if(wWalletAccount == null){
            wWalletAccount = createAccount(userId,currencyId);
        }
        return wWalletAccount;
    }

    /**
     * 资金账户入账
     * @param fundAccount 资金账户
     * @param num 数量
     * @return
     */
    @Override
    public Integer fundAccountIn(WWalletAccount fundAccount, BigDecimal num) {
        return walletAccountDAO.fundAccountIn(fundAccount.getId(),num,fundAccount.getVersion());
    }

    /**
     * 资金账户入账
     * @param fundAccount 资金账户
     * @param num 数量
     * @return
     */
    @Override
    public Integer fundAccountOut(WWalletAccount fundAccount, BigDecimal num) {
        return walletAccountDAO.fundAccountOut(fundAccount.getId(),num,fundAccount.getVersion());
    }

    /**
     * 为用户开通资金钱包
     * @param userId 用户id
     * @param currencyId 币种id
     * @return
     */
    private WWalletAccount createAccount(Long userId, Integer currencyId) {
        WWalletAccount account = WWalletAccount.builder()
                .id(SNOW_FLAKE__ENTRUST.nextId())
                .userId(userId)
                .currencyId(currencyId)
                .total(BigDecimal.ZERO)
                .tradeFrozen(BigDecimal.ZERO)
                .transferFrozen(BigDecimal.ZERO)
                .version(0)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        int insert = walletAccountDAO.insert(account);
        return account;
    }
}
