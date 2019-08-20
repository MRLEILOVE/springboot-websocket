package com.wallet.biz.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.biz.mapper.TAccountConfigMapper;
import com.wallet.biz.pojo.TAccountConfig;
import com.wallet.biz.service.ITAccountConfigService;

@Service
public class TAccountConfigServiceImpl extends ServiceImpl<TAccountConfigMapper, TAccountConfig> implements ITAccountConfigService {
}
