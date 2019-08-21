package com.wallet.chain.service.impl;

import com.wallet.chain.entity.WalletBill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wallet.chain.entity.WithdrawWalletBill;
import com.wallet.chain.service.IWithdrawStrategy;

@Slf4j
@Service("withdrawStrategyBTC_TOKEN")
public class WithdrawStrategyBTC_TOKEN implements IWithdrawStrategy {

    @Autowired
    @Qualifier("withdrawStrategyBTC")
    private IWithdrawStrategy withdrawStrategy;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void withdraw(WalletBill walletBill) {
        withdrawStrategy.withdraw(walletBill);
    }
}
