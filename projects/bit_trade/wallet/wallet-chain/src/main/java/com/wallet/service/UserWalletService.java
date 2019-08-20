package com.wallet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wallet.entity.UserWallet;

/**
 * <p>
 * 钱包 服务类
 * </p>
 *
 * @author xxx
 * @since 2019-06-27
 */
public interface UserWalletService extends IService<UserWallet> {

    /**
     * 导入钱包
     */
    void importAddress();

    boolean isMineAddressCache(String coinType, String address);

    UserWallet cacheUserWallet(String coinType, String address);
}
