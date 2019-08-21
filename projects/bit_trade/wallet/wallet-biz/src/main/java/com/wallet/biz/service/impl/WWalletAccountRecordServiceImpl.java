package com.wallet.biz.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.biz.api.service.IWWalletAccountRecordService;
import com.wallet.biz.dao.IWWalletAccountRecordDAO;
import com.wallet.biz.pojo.model.WWalletAccountRecord;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Component
public class WWalletAccountRecordServiceImpl extends ServiceImpl<IWWalletAccountRecordDAO, WWalletAccountRecord> implements IWWalletAccountRecordService {
	
}
