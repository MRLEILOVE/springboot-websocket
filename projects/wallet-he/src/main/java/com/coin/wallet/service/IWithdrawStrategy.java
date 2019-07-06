package com.coin.wallet.service;

import com.coin.wallet.dto.WithDrawResultDto;
import com.coin.wallet.entity.WithdrawWalletBill;

public interface IWithdrawStrategy {

    /**
     * 提币【提币钱包到目标钱包地址】
     *
     * @param withdrawWalletBill
     * @return
     */
    void withdraw(WithdrawWalletBill withdrawWalletBill);
}
