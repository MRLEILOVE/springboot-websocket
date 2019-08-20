package com.wallet.chain.service;

import com.wallet.chain.entity.CoinConfig;

public interface IConfirmStrategy {

    /**
     * 确认高度
     *
     * @param coinConfig
     * @return
     */
    void execute(CoinConfig coinConfig);
}
