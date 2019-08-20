package com.wallet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wallet.constant.TradeStepConstant;
import com.wallet.dto.BlockInfoDto;
import com.wallet.dto.RawtransactionDto;
import com.wallet.entity.CoinConfig;
import com.wallet.entity.WithdrawWalletBill;
import com.wallet.service.IJsonRpcService;
import com.wallet.service.IQueryPackageStrategy;
import com.wallet.service.WithdrawWalletBillService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@Service("queryPackageStrategyBTC")
public class QueryPackageStrategyBTC implements IQueryPackageStrategy {

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

            RawtransactionDto rawtransactionDto = jsonRpcService.getRawTransaction(walletBill.getTx());
            if (rawtransactionDto.getConfirmations() != null && BigInteger.ZERO.compareTo(rawtransactionDto.getConfirmations()) < 0) {
                BlockInfoDto blockInfoDto = jsonRpcService.getBlockInfo(rawtransactionDto.getBlockhash());

                withdrawWalletBillService.update(
                        WithdrawWalletBill.builder()
                                .block(blockInfoDto.getHeight())
                                .tradeStep(TradeStepConstant.PACKAGED).build(),
                        new QueryWrapper<>(WithdrawWalletBill.builder()
                                .id(walletBill.getId())
                                .tradeStep(TradeStepConstant.BROADCAST).build()));
            }
        });
    }
}
