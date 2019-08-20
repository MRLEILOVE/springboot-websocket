package com.wallet.service;

import com.wallet.entity.CoinConfig;

public interface IRechargeStrategy {

    /**
     * 充币数据扫描
     *
     * @param coinConfig
     * @return
     */
    void execute(CoinConfig coinConfig);
}
