package com.walletbiz.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.walletbiz.mapper.TAccountConfigMapper;
import com.walletbiz.pojo.TAccountConfig;
import com.walletbiz.service.ITAccountConfigService;

@Service
public class TAccountConfigServiceImpl extends ServiceImpl<TAccountConfigMapper, TAccountConfig> implements ITAccountConfigService {
}
