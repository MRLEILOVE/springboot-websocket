package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.api.service.IWWithdrawWalletBillService;
import com.bittrade.currency.dao.WWithdrawWalletBillMapper;
import com.bittrade.pojo.model.WWithdrawWalletBill;

/**
 * <p>
 * 提币钱包账单 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-07-04
 */
@Service
public class WWithdrawWalletBillServiceImpl extends ServiceImpl<WWithdrawWalletBillMapper, WWithdrawWalletBill> implements IWWithdrawWalletBillService {

}
