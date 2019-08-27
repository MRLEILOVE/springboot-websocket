package com.wallet.biz.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.pojo.model.WConfigWallet;
import com.wallet.biz.api.service.IWConfigWalletService;
import com.wallet.biz.dao.IWConfigWalletDAO;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Component
public class WConfigWalletServiceImpl extends ServiceImpl<IWConfigWalletDAO, WConfigWallet> implements IWConfigWalletService {
	
}
