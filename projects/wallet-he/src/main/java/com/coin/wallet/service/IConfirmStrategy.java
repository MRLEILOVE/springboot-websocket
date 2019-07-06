package com.coin.wallet.service;

import com.coin.wallet.entity.CoinConfig;

public interface IConfirmStrategy {

    /**
     * 确认高度
     *
     * @param coinConfig
     * @return
     */
    void execute(CoinConfig coinConfig);
}
