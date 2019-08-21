package com.wallet.chain.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wallet.chain.constant.TradeStepConstant;
import com.wallet.chain.dto.TransferDto;
import com.wallet.chain.entity.CoinConfig;
import com.wallet.chain.entity.WalletBill;
import com.wallet.chain.entity.WithdrawWalletBill;
import com.wallet.chain.service.*;
import com.wallet.chain.utils.StringTemplateUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("withdrawStrategyBTC")
public class WithdrawStrategyBTC implements IWithdrawStrategy {

    @Autowired
    private CoinConfigService coinConfigService;
    @Autowired
    private WalletBillService service;
    @Autowired
    private ITransactionService transactionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void withdraw(WalletBill walletBill) {

        //查询配置
        CoinConfig coinConfig = coinConfigService.cacheCoinConfig(walletBill.getCoinType(), walletBill.getToken());

        //先改状态锁行,保证只会执行一次
        if (service.update(WalletBill.builder().tradeStep(TradeStepConstant.PRE_BROADCAST).build(),
                new QueryWrapper<>(WalletBill.builder()
                        .id(walletBill.getId())
                        .tradeStep(TradeStepConstant.AUDIT_PASS)
                        .build()))) {
            try {
                TransferDto transferDto = transactionService.withdraw(coinConfig, walletBill);

                if (!service.update(WalletBill.builder()
                                .tradeStep(TradeStepConstant.BROADCAST)
                                .senderAddress(transferDto.getSenderAddress())
                                .tx(transferDto.getTx())
                                .build(),
                        new QueryWrapper<>(WalletBill.builder()
                                .id(walletBill.getId())
                                .tradeStep(TradeStepConstant.PRE_BROADCAST)
                                .build()))) {
                    log.error("WithdrawStrategyBTC.execute订单已广播,但更新数据状态出问题,请检查{}", JSON.toJSONString(walletBill));
                }
            } catch (Exception e) {
                log.error("WithdrawStrategyBTC.CommonException.广播交易出错 param={}", JSON.toJSONString(walletBill), e);
                service.update(WalletBill.builder()
                                .tradeStep(TradeStepConstant.AUDIT_PASS)
                                .remark(StringTemplateUtils.limit(e.getMessage(), 200))
                                .build(),
                        new QueryWrapper<>(WalletBill.builder()
                                .id(walletBill.getId())
                                .tradeStep(TradeStepConstant.PRE_BROADCAST)
                                .build()));
            }
        }
    }
}
