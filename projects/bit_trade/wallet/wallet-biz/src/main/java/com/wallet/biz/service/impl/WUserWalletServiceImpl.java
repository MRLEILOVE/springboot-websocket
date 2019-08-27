package com.wallet.biz.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.pojo.model.WUserWallet;
import com.wallet.biz.api.service.IWUserWalletService;
import com.wallet.biz.dao.IWUserWalletDAO;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Component
public class WUserWalletServiceImpl extends ServiceImpl<IWUserWalletDAO, WUserWallet> implements IWUserWalletService {
	
}
