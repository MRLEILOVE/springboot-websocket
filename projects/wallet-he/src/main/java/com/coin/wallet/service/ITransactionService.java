package com.coin.wallet.service;

import com.coin.wallet.dto.TransferDto;
import com.coin.wallet.entity.CoinConfig;
import com.coin.wallet.entity.ConfigWallet;
import com.coin.wallet.entity.UserWallet;
import com.coin.wallet.entity.WithdrawWalletBill;

import java.math.BigDecimal;

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
