package com.wallet.chain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wallet.chain.constant.TradeStepConstant;
import com.wallet.chain.dto.BlockInfoDto;
import com.wallet.chain.dto.RawtransactionDto;
import com.wallet.chain.entity.CoinConfig;
import com.wallet.chain.entity.WalletBill;
import com.wallet.chain.entity.WithdrawWalletBill;
import com.wallet.chain.service.IJsonRpcService;
import com.wallet.chain.service.IQueryPackageStrategy;
import com.wallet.chain.service.WalletBillService;
import com.wallet.chain.service.WithdrawWalletBillService;

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
    @Autowired
    private WalletBillService walletBillService;

    @Override
    public void execute(CoinConfig coinConfig) {

        //查询需要确认打包区块高度的数据
        List<WalletBill> walletBillList = walletBillService.list(new QueryWrapper<>(WalletBill.builder()
                .coinType(coinConfig.getCoinType())
                .tradeStep(TradeStepConstant.BROADCAST).build()));

        walletBillList.forEach(Bill -> {

            RawtransactionDto rawtransactionDto = jsonRpcService.getRawTransaction(Bill.getTx());
            if (rawtransactionDto.getConfirmations() != null && BigInteger.ZERO.compareTo(rawtransactionDto.getConfirmations()) < 0) {
                BlockInfoDto blockInfoDto = jsonRpcService.getBlockInfo(rawtransactionDto.getBlockhash());

                walletBillService.update(
                        WalletBill.builder()
                                .block(blockInfoDto.getHeight())
                                .tradeStep(TradeStepConstant.PACKAGED).build(),
                        new QueryWrapper<>(WalletBill.builder()
                                .id(Bill.getId())
                                .tradeStep(TradeStepConstant.BROADCAST).build()));
            }
        });
    }
}
