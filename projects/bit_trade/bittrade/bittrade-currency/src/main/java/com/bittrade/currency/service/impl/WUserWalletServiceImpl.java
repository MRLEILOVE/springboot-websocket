package com.bittrade.currency.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.api.dao.WUserWalletMapper;
import com.bittrade.api.service.IWUserWalletService;
import com.bittrade.pojo.model.WUserWallet;
import org.springframework.stereotype.Service;

@Service
public class WUserWalletServiceImpl extends ServiceImpl<WUserWalletMapper, WUserWallet> implements IWUserWalletService {

}
