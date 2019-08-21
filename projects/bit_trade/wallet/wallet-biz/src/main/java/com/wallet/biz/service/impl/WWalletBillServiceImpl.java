package com.wallet.biz.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.biz.api.service.IWWalletBillService;
import com.wallet.biz.dao.IWWalletBillDAO;
import com.wallet.biz.pojo.model.WWalletBill;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Component
public class WWalletBillServiceImpl extends ServiceImpl<IWWalletBillDAO, WWalletBill> implements IWWalletBillService {
	
}