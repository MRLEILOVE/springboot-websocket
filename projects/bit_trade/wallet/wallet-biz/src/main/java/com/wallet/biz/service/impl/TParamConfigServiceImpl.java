package com.wallet.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.biz.api.service.ITParamConfigService;
import com.wallet.biz.dao.ITParamConfigDAO;
import com.wallet.biz.pojo.model.TParamConfig;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TParamConfigServiceImpl extends ServiceImpl<ITParamConfigDAO, TParamConfig> implements ITParamConfigService {
	
}
