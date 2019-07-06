package com.coin.wallet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coin.wallet.dao.UserWalletDao;
import com.coin.wallet.entity.UserWallet;
import com.coin.wallet.enums.CoinTypeEnum;
import com.coin.wallet.service.IJsonRpcService;
import com.coin.wallet.service.UserWalletService;
import com.coin.wallet.utils.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 钱包 服务实现类
 * </p>
 *
 * @author xxx
 * @since 2019-06-27
 */
@Slf4j
@Service
public class UserWalletServiceImpl extends ServiceImpl<UserWalletDao, UserWallet> implements UserWalletService {

    @Autowired
    private IJsonRpcService jsonRpcService;

    @Override
    public void importAddress() {

        //查询需要导入的钱包地址。
        List<UserWallet> list = this.list(new QueryWrapper<>(
                UserWallet.builder().flag(true).build()
        ).in("coin_type", ListUtils.list(CoinTypeEnum.BTC.getCoinType(), CoinTypeEnum.BTC_TOKEN.getCoinType())));

        list.forEach(userWallet -> {
            try {
                jsonRpcService.importAddress(userWallet.getAddress());
                this.update(UserWallet.builder().flag(false).build(),
                        new QueryWrapper<>(UserWallet.builder().id(userWallet.getId()).build()));
            } catch (Exception e) {
                log.error("UserWalletServiceImpl.importAddress", e);
            }
        });
    }

    @Override
    @Cacheable(value = "user-wallet-judge", key = "#coinType+'_'+#address", unless = "#result == null")
    public boolean isMineAddressCache(String coinType, String address) {
        return this.getOne(new QueryWrapper<>(UserWallet.builder().coinType(coinType).address(address).build())) != null;
    }

    @Override
    @Cacheable(value = "user-wallet", key = "#coinType+'_'+#address", unless = "#result == null")
    public UserWallet cacheUserWallet(String coinType, String address) {
        return this.getOne(new QueryWrapper<>(UserWallet.builder().coinType(coinType).address(address).build()));
    }
}
