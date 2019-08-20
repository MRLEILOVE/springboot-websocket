package com.wallet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wallet.constant.FlagConstant;
import com.wallet.constant.TradeStepConstant;
import com.wallet.dto.RawtransactionDto;
import com.wallet.entity.CoinConfig;
import com.wallet.entity.UserWalletBill;
import com.wallet.entity.WithdrawWalletBill;
import com.wallet.service.IConfirmStrategy;
import com.wallet.service.IJsonRpcService;
import com.wallet.service.UserWalletBillService;
import com.wallet.service.WithdrawWalletBillService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("confirmStrategyBTC")
public class ConfirmStrategyBTC implements IConfirmStrategy {
    @Autowired
    private IJsonRpcService jsonRpcService;
    @Autowired
    private UserWalletBillService userWalletBillService;
    @Autowired
    private WithdrawWalletBillService withdrawWalletBillService;

    @Override
    @Transactional(rollbackFor =Exception.class )
    public void execute(CoinConfig coinConfig) {

        //用户钱包数据
        List<UserWalletBill> userWalletBillList = userWalletBillService.list(new QueryWrapper<>(UserWalletBill.builder()
                .coinType(coinConfig.getCoinType())
                .tradeStep(TradeStepConstant.PACKAGED).build()));
        userWalletBillList.forEach(walletBill -> {
            RawtransactionDto rawtransactionDto = jsonRpcService.getRawTransaction(walletBill.getTx());

            if (rawtransactionDto.getConfirmations().compareTo(coinConfig.getMinConfirm()) >= 0) {

                userWalletBillService.update(UserWalletBill.builder().tradeStep(TradeStepConstant.CONFIRM).build(),
                        new QueryWrapper<>(UserWalletBill.builder()
                                .id(walletBill.getId())
                                .tradeStep(TradeStepConstant.PACKAGED)
                                .build()));
                userWalletBillService.update(UserWalletBill.builder().transferFlag(FlagConstant.UN_PROCESS).build(),
                        new QueryWrapper<>(UserWalletBill.builder()
                                .id(walletBill.getId())
                                .transferFlag(FlagConstant.INIT)
                                .build()));
                userWalletBillService.update(UserWalletBill.builder().flag(FlagConstant.UN_PROCESS).build(),
                        new QueryWrapper<>(UserWalletBill.builder()
                                .id(walletBill.getId())
                                .flag(FlagConstant.INIT)
                                .build()));
            }
        });

        //提现钱包数据
        List<WithdrawWalletBill> withdrawWalletBillList = withdrawWalletBillService.list(new QueryWrapper<>(WithdrawWalletBill.builder()
                .coinType(coinConfig.getCoinType())
                .tradeStep(TradeStepConstant.PACKAGED).build()));
        withdrawWalletBillList.forEach(walletBill -> {
            RawtransactionDto rawtransactionDto = jsonRpcService.getRawTransaction(walletBill.getTx());

            if (rawtransactionDto.getConfirmations().compareTo(coinConfig.getMinConfirm()) >= 0) {
                withdrawWalletBillService.update(WithdrawWalletBill.builder().tradeStep(TradeStepConstant.CONFIRM).build(),
                        new QueryWrapper<>(WithdrawWalletBill.builder()
                                .id(walletBill.getId())
                                .tradeStep(TradeStepConstant.PACKAGED)
                                .build()));
                withdrawWalletBillService.update(WithdrawWalletBill.builder().transferFlag(FlagConstant.UN_PROCESS).build(),
                        new QueryWrapper<>(WithdrawWalletBill.builder()
                                .id(walletBill.getId())
                                .transferFlag(FlagConstant.INIT)
                                .build()));
                withdrawWalletBillService.update(WithdrawWalletBill.builder().flag(FlagConstant.UN_PROCESS).build(),
                        new QueryWrapper<>(WithdrawWalletBill.builder()
                                .id(walletBill.getId())
                                .flag(FlagConstant.INIT)
                                .build()));
            }
        });
    }
}