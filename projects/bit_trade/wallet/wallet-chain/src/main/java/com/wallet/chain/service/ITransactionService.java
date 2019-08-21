package com.wallet.chain.service;

import java.math.BigDecimal;

import com.wallet.chain.dto.TransferDto;
import com.wallet.chain.entity.*;

public interface ITransactionService {

    /**
     * 提币
     */
    TransferDto withdraw(CoinConfig coinConfig, WalletBill WalletBill);

    /**
     * 归集
     */
    TransferDto btcTokenCollection(CoinConfig coinConfig, UserWallet userWallet, ConfigWallet feeWallet, BigDecimal amount);

    /**
     * 归集
     */
    TransferDto btcCollection(CoinConfig coinConfig, UserWallet userWallet, ConfigWallet feeWallet);

}
