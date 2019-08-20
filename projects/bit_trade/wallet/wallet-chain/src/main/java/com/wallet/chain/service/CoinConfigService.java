package com.wallet.chain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wallet.chain.entity.CoinConfig;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xxx
 * @since 2019-06-27
 */
public interface CoinConfigService extends IService<CoinConfig> {

    CoinConfig cacheCoinConfig(String coinType, String token);
}
