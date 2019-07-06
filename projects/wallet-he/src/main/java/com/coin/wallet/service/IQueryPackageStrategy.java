package com.coin.wallet.service;

import com.coin.wallet.entity.CoinConfig;

public interface IQueryPackageStrategy {

    /**
     * 查询打包高度
     *
     * @param coinConfig
     * @return
     */
    void execute(CoinConfig coinConfig);
}
