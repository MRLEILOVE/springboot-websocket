package com.wallet.chain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.chain.dao.CoinConfigDao;
import com.wallet.chain.entity.CoinConfig;
import com.wallet.chain.service.CoinConfigService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xxx
 * @since 2019-06-27
 */
@Service
public class CoinConfigServiceImpl extends ServiceImpl<CoinConfigDao, CoinConfig> implements CoinConfigService {

    @Override
    @Cacheable(value = "coin-config", key = "#coinType+'_'+#token", unless = "#result == null")
    public CoinConfig cacheCoinConfig(String coinType, String token) {
        return this.getOne(new QueryWrapper<>(CoinConfig.builder().coinType(coinType).token(token).build()), true);
    }
}
