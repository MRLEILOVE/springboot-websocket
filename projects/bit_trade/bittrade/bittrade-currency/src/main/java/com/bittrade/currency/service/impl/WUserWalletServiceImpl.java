package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.api.service.IWUserWalletService;
import com.bittrade.currency.dao.WUserWalletMapper;
import com.bittrade.pojo.model.WUserWallet;

@Service
public class WUserWalletServiceImpl extends ServiceImpl<WUserWalletMapper, WUserWallet> implements IWUserWalletService {

}
