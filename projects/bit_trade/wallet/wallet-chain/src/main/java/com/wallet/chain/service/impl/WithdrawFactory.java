package com.wallet.chain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wallet.chain.constant.DirectionConstant;
import com.wallet.chain.constant.FlagConstant;
import com.wallet.chain.constant.TradeStepConstant;
import com.wallet.chain.dto.WithDrawParamDto;
import com.wallet.chain.entity.CoinConfig;
import com.wallet.chain.entity.WalletBill;
import com.wallet.chain.entity.WithdrawWalletBill;
import com.wallet.chain.service.*;
import com.wallet.chain.utils.ValidatorUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WithdrawFactory implements IWithdrawFactory {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private CoinConfigService coinConfigService;
    @Autowired
    private WithdrawWalletBillService withdrawWalletBillService;
    @Autowired
    private WalletBillService walletBillService;

    @Override
    public void addOrder(WithDrawParamDto paramDto) {
        CoinConfig coinConfig = coinConfigService.getOne(new QueryWrapper<>(CoinConfig.builder()
                .coinType(paramDto.getCoinType())
                .token(paramDto.getToken())
                .valid("E")
                .build()), true);
        ValidatorUtils.isNullThrow(coinConfig, "暂时不支持该币种提币");

        WalletBill checke = walletBillService.getOne(new QueryWrapper<>(WalletBill.builder().orderId(paramDto.getOrderId()).build()), true);
        ValidatorUtils.noNullThrow(checke, "该订单已存在，不要重复提交");

        WalletBill Bill = WalletBill.builder()
                .userId(paramDto.getUserId())
                .orderId(paramDto.getOrderId())
                .direction(DirectionConstant.OUT)
                .coinType(coinConfig.getCoinType())
                .token(coinConfig.getToken())
                .contract(coinConfig.getContract())
                .receiverAddress(paramDto.getReceiverAddress())
                .transferFlag(FlagConstant.SKIP)
                .amount(paramDto.getAmount())
                .tx(paramDto.getOrderId())
                .build();
        walletBillService.save(Bill);
    }

    @Override
    public void execute() {

        //查询待提币数据
        List<WalletBill> Bills = walletBillService.list(new QueryWrapper<>(WalletBill.builder()
                .tradeStep(TradeStepConstant.AUDIT_PASS)
                .build()));

        for (WalletBill Bill : Bills) {
            try {
                IWithdrawStrategy withdrawStrategy = applicationContext.getBean("withdrawStrategy" + Bill.getCoinType(), IWithdrawStrategy.class);
                withdrawStrategy.withdraw(Bill);
            } catch (Exception e) {
                log.error("WithdrawFactory.execute", e);
            }
        }
    }
}
