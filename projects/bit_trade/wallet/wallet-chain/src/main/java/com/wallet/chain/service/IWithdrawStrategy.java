package com.wallet.chain.service;

import com.wallet.chain.dto.WithDrawResultDto;
import com.wallet.chain.entity.WalletBill;
import com.wallet.chain.entity.WithdrawWalletBill;

public interface IWithdrawStrategy {

    /**
     * 提币【提币钱包到目标钱包地址】
     *
     * @param walletBill
     * @return
     */
    void withdraw(WalletBill walletBill);
}
