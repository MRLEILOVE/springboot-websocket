package com.wallet.chain.service;

import com.wallet.chain.entity.CoinConfig;

public interface ICollectionStrategy {

    /**
     * 归集
     *
     * @param coinConfig
     * @return
     */
    void execute(CoinConfig coinConfig);
}
