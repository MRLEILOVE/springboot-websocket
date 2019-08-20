package com.wallet.chain.service;

import java.math.BigDecimal;

import com.wallet.chain.dto.TransferDto;
import com.wallet.chain.entity.CoinConfig;
import com.wallet.chain.entity.ConfigWallet;
import com.wallet.chain.entity.UserWallet;
import com.wallet.chain.entity.WithdrawWalletBill;

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
