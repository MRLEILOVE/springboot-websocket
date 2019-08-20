package com.wallet.service;

import java.math.BigDecimal;

import com.wallet.dto.TransferDto;
import com.wallet.entity.CoinConfig;
import com.wallet.entity.ConfigWallet;
import com.wallet.entity.UserWallet;
import com.wallet.entity.WithdrawWalletBill;

public interface ITransactionService {

    /**
     * 提币
     */
    TransferDto withdraw(CoinConfig coinConfig, WithdrawWalletBill withdrawWalletBill);

    /**
     * 归集
     */
    TransferDto btcTokenCollection(CoinConfig coinConfig, UserWallet userWallet, ConfigWallet feeWallet, BigDecimal amount);

    /**
     * 归集
     */
    TransferDto btcCollection(CoinConfig coinConfig, UserWallet userWallet, ConfigWallet feeWallet);

}
