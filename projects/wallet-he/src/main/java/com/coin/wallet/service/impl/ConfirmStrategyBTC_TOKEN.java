package com.coin.wallet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coin.wallet.constant.FlagConstant;
import com.coin.wallet.constant.TradeStepConstant;
import com.coin.wallet.dto.OmniTransactionResultDto;
import com.coin.wallet.entity.CoinConfig;
import com.coin.wallet.entity.UserWalletBill;
import com.coin.wallet.entity.WithdrawWalletBill;
import com.coin.wallet.service.IConfirmStrategy;
import com.coin.wallet.service.IJsonRpcService;
import com.coin.wallet.service.UserWalletBillService;
import com.coin.wallet.service.WithdrawWalletBillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("confirmStrategyBTC_TOKEN")
public class ConfirmStrategyBTC_TOKEN implements IConfirmStrategy {

    @Autowired
    private IJsonRpcService jsonRpcService;
    @Autowired
    private UserWalletBillService userWalletBillService;
    @Autowired
    private WithdrawWalletBillService withdrawWalletBillService;

    @Override
    public void execute(CoinConfig coinConfig) {

        //用户钱包数据
        List<UserWalletBill> userWalletBillList = userWalletBillService.list(new QueryWrapper<>(UserWalletBill.builder()
                .coinType(coinConfig.getCoinType())
                .tradeStep(TradeStepConstant.PACKAGED).build()));
        userWalletBillList.forEach(walletBill -> {
            OmniTransactionResultDto omniTransactionResultDto = jsonRpcService.getTokenTransaction(walletBill.getTx());

            if (omniTransactionResultDto.getConfirmations().compareTo(coinConfig.getMinConfirm()) >= 0) {

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
            OmniTransactionResultDto omniTransactionResultDto = jsonRpcService.getTokenTransaction(walletBill.getTx());

            if (omniTransactionResultDto.getConfirmations().compareTo(coinConfig.getMinConfirm()) >= 0) {
                withdrawWalletBillService.update(WithdrawWalletBill.builder().tradeStep(TradeStepConstant.CONFIRM).build(),
                        new QueryWrapper<>(WithdrawWalletBill.builder()
                                .id(walletBill.getId())
                                .tradeStep(TradeStepConstant.PACKAGED)
                                .build()));
            }
        });
    }
}
