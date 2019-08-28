package com.wallet.biz.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.pojo.model.WWalletAddress;
import com.wallet.biz.api.service.IWWalletAddressService;
import com.wallet.biz.dao.IWWalletAddressDAO;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Component
public class WWalletAddressServiceImpl extends ServiceImpl<IWWalletAddressDAO, WWalletAddress> implements IWWalletAddressService {
	
}
