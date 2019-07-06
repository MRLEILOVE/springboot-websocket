package com.coin.wallet.service.impl;

import com.coin.wallet.entity.WithdrawWalletBill;
import com.coin.wallet.service.IWithdrawStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("withdrawStrategyBTC_TOKEN")
public class WithdrawStrategyBTC_TOKEN implements IWithdrawStrategy {

    @Autowired
    @Qualifier("withdrawStrategyBTC")
    private IWithdrawStrategy withdrawStrategy;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void withdraw(WithdrawWalletBill withdrawWalletBill) {
        withdrawStrategy.withdraw(withdrawWalletBill);
    }
}
