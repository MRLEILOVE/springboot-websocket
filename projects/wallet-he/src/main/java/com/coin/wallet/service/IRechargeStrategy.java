package com.coin.wallet.service;

import com.coin.wallet.entity.CoinConfig;

public interface IRechargeStrategy {

    /**
     * 充币数据扫描
     *
     * @param coinConfig
     * @return
     */
    void execute(CoinConfig coinConfig);
}
