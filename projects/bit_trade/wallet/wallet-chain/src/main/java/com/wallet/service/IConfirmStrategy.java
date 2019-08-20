package com.wallet.service;

import com.wallet.entity.CoinConfig;

public interface IConfirmStrategy {

    /**
     * 确认高度
     *
     * @param coinConfig
     * @return
     */
    void execute(CoinConfig coinConfig);
}
