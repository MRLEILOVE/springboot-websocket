package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.api.service.IWUserWalletBillService;
import com.bittrade.currency.dao.WUserWalletBillMapper;
import com.bittrade.pojo.model.WUserWalletBill;

/**
 * <p>
 * 用户钱包账单 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-07-04
 */
@Service
public class WUserWalletBillServiceImpl extends ServiceImpl<WUserWalletBillMapper, WUserWalletBill> implements IWUserWalletBillService {

}
