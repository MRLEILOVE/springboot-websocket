package com.wallet.biz.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.biz.api.service.IWWalletAccountService;
import com.wallet.biz.dao.IWWalletAccountDAO;
import com.wallet.biz.pojo.model.WWalletAccount;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WWalletAccountServiceImpl extends ServiceImpl<IWWalletAccountDAO, WWalletAccount> implements IWWalletAccountService {
	
}
