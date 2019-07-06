package com.coin.wallet.service;

import com.coin.wallet.entity.CoinConfig;

public interface ICollectionStrategy {

    /**
     * 归集
     *
     * @param coinConfig
     * @return
     */
    void execute(CoinConfig coinConfig);
}
