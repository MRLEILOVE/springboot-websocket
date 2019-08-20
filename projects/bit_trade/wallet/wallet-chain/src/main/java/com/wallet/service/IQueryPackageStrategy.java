package com.wallet.service;

import com.wallet.entity.CoinConfig;

public interface IQueryPackageStrategy {

    /**
     * 查询打包高度
     *
     * @param coinConfig
     * @return
     */
    void execute(CoinConfig coinConfig);
}
