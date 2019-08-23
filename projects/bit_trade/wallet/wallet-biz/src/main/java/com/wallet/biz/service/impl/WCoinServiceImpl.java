package com.wallet.biz.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.biz.api.service.IWCoinService;
import com.wallet.biz.dao.IWCoinDAO;
import com.wallet.biz.pojo.model.WCoin;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Component
public class WCoinServiceImpl extends ServiceImpl<IWCoinDAO, WCoin> implements IWCoinService {
	
}
