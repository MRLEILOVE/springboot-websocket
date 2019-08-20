package com.wallet.chain.service;

import com.wallet.chain.entity.CoinConfig;

public interface IRechargeStrategy {

    /**
     * 充币数据扫描
     *
     * @param coinConfig
     * @return
     */
    void execute(CoinConfig coinConfig);
}
