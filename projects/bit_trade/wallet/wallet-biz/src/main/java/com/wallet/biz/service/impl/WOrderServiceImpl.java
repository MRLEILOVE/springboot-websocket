package com.wallet.biz.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.biz.api.service.IWOrderService;
import com.wallet.biz.dao.IWOrderDAO;
import com.wallet.biz.pojo.model.WOrder;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Component
public class WOrderServiceImpl extends ServiceImpl<IWOrderDAO, WOrder> implements IWOrderService {
	
}
