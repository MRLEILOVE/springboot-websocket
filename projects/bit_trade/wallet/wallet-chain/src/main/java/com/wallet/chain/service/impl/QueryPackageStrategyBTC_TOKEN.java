package com.wallet.chain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wallet.chain.constant.TradeStepConstant;
import com.wallet.chain.dto.OmniTransactionResultDto;
import com.wallet.chain.entity.CoinConfig;
import com.wallet.chain.entity.WithdrawWalletBill;
import com.wallet.chain.service.IJsonRpcService;
import com.wallet.chain.service.IQueryPackageStrategy;
import com.wallet.chain.service.WithdrawWalletBillService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@Service("queryPackageStrategyBTC_TOKEN")
public class QueryPackageStrategyBTC_TOKEN implements IQueryPackageStrategy {

    @Autowired
    private IJsonRpcService jsonRpcService;
    @Autowired
    private WithdrawWalletBillService withdrawWalletBillService;

    @Override
    public void execute(CoinConfig coinConfig) {

        //查询需要确认打包区块高度的数据
        List<WithdrawWalletBill> walletBillList = withdrawWalletBillService.list(new QueryWrapper<>(WithdrawWalletBill.builder()
                .coinType(coinConfig.getCoinType())
                .tradeStep(TradeStepConstant.BROADCAST).build()));

        walletBillList.forEach(walletBill -> {

            OmniTransactionResultDto transactionResultDto = jsonRpcService.getTokenTransaction(walletBill.getTx());
            if (BigInteger.ZERO.compareTo(transactionResultDto.getConfirmations()) < 0) {

                withdrawWalletBillService.update(
                        WithdrawWalletBill.builder()
                                .block(transactionResultDto.getBlock())
                                .tradeStep(TradeStepConstant.PACKAGED).build(),
                        new QueryWrapper<>(WithdrawWalletBill.builder()
                                .id(walletBill.getId())
                                .tradeStep(TradeStepConstant.BROADCAST).build()));
            }
        });
    }
}
