package com.wallet.chain.service;

import com.wallet.chain.dto.WithDrawResultDto;
import com.wallet.chain.entity.WithdrawWalletBill;

public interface IWithdrawStrategy {

    /**
     * 提币【提币钱包到目标钱包地址】
     *
     * @param withdrawWalletBill
     * @return
     */
    void withdraw(WithdrawWalletBill withdrawWalletBill);
}
