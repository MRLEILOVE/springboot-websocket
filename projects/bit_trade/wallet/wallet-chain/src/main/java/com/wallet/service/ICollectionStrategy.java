package com.wallet.service;

import com.wallet.entity.CoinConfig;

public interface ICollectionStrategy {

    /**
     * 归集
     *
     * @param coinConfig
     * @return
     */
    void execute(CoinConfig coinConfig);
}
