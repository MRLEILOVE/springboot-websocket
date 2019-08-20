package com.wallet.service;

import com.wallet.dto.WithDrawResultDto;
import com.wallet.entity.WithdrawWalletBill;

public interface IWithdrawStrategy {

    /**
     * 提币【提币钱包到目标钱包地址】
     *
     * @param withdrawWalletBill
     * @return
     */
    void withdraw(WithdrawWalletBill withdrawWalletBill);
}
