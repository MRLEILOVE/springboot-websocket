package com.wallet.chain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wallet.chain.constant.DirectionConstant;
import com.wallet.chain.constant.FlagConstant;
import com.wallet.chain.constant.TradeStepConstant;
import com.wallet.chain.dto.WithDrawParamDto;
import com.wallet.chain.entity.CoinConfig;
import com.wallet.chain.entity.WithdrawWalletBill;
import com.wallet.chain.service.CoinConfigService;
import com.wallet.chain.service.IWithdrawFactory;
import com.wallet.chain.service.IWithdrawStrategy;
import com.wallet.chain.service.WithdrawWalletBillService;
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

    @Override
    public void addOrder(WithDrawParamDto paramDto) {
        CoinConfig coinConfig = coinConfigService.getOne(new QueryWrapper<>(CoinConfig.builder()
                .coinType(paramDto.getCoinType())
                .token(paramDto.getToken())
                .valid("E")
                .build()), true);
        ValidatorUtils.isNullThrow(coinConfig, "暂时不支持该币种提币");

        WithdrawWalletBill checke = withdrawWalletBillService.getOne(new QueryWrapper<>(WithdrawWalletBill.builder().orderId(paramDto.getOrderId()).build()), true);
        ValidatorUtils.noNullThrow(checke, "该订单已存在，不要重复提交");

        WithdrawWalletBill withdrawWalletBill = WithdrawWalletBill.builder()
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
        withdrawWalletBillService.save(withdrawWalletBill);
    }

    @Override
    public void execute() {

        //查询待提币数据
        List<WithdrawWalletBill> withdrawWalletBills = withdrawWalletBillService.list(new QueryWrapper<>(WithdrawWalletBill.builder()
                .tradeStep(TradeStepConstant.AUDIT_PASS)
                .build()));

        for (WithdrawWalletBill withdrawWalletBill : withdrawWalletBills) {
            try {
                IWithdrawStrategy withdrawStrategy = applicationContext.getBean("withdrawStrategy" + withdrawWalletBill.getCoinType(), IWithdrawStrategy.class);
                withdrawStrategy.withdraw(withdrawWalletBill);
            } catch (Exception e) {
                log.error("WithdrawFactory.execute", e);
            }
        }
    }
}
