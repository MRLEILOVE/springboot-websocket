package com.walletbiz.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.walletbiz.mapper.TWalletTransferMapper;
import com.walletbiz.pojo.TWalletTransfer;
import com.walletbiz.service.ITWalletTransferService;

/**
 * <p>
 * 钱包划转记录表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-08-08
 */
@Service
public class TWalletTransferServiceImpl extends ServiceImpl<TWalletTransferMapper, TWalletTransfer> implements ITWalletTransferService {

}
