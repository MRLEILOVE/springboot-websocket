package com.wallet.chain.service;

import com.wallet.chain.entity.CoinConfig;

public interface IQueryPackageStrategy {

    /**
     * 查询打包高度
     *
     * @param coinConfig
     * @return
     */
    void execute(CoinConfig coinConfig);
}
