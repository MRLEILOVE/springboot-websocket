package com.jdcloud.provider.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.WConfigWallet;
import com.jdcloud.provider.web.base.BaseEntity;
import com.jdcloud.util.wrapper.Wrapper;

import java.util.List;

/**
 * <p>
 * 配置钱包 服务类
 * </p>
 *
 * @author helen
 * @since 2019-07-17
 */
public interface WConfigWalletService extends IService<WConfigWallet> {

    Wrapper<String> saveOrUpdate(WConfigWallet wConfigWallet);

	Page<WConfigWallet> listHotAddress(Page<WConfigWallet> page, BaseEntity baseEntity);
}
