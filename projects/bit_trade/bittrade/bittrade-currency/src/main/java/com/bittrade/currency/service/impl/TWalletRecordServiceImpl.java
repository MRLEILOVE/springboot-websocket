package com.bittrade.currency.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.api.dao.TWalletRecordMapper;
import com.bittrade.api.service.ITWalletRecordService;
import com.bittrade.pojo.model.TWalletRecord;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 虚拟币钱包表日志表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-07-05
 */
@Service
public class TWalletRecordServiceImpl extends ServiceImpl<TWalletRecordMapper, TWalletRecord> implements ITWalletRecordService {

}
