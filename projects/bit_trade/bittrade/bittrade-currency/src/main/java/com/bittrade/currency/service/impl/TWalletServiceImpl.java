package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.currency.dao.TWalletMapper;
import com.bittrade.pojo.model.TWallet;

/**
 * <p>
 * 虚拟币钱包表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-07-05
 */
@Service
public class TWalletServiceImpl extends ServiceImpl<TWalletMapper, TWallet> implements IService<TWallet> {

}
