package com.coin.wallet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coin.wallet.constant.DirectionConstant;
import com.coin.wallet.constant.FlagConstant;
import com.coin.wallet.constant.WalletTypeConstant;
import com.coin.wallet.entity.CoinConfig;
import com.coin.wallet.entity.ConfigWallet;
import com.coin.wallet.entity.UserWallet;
import com.coin.wallet.entity.UserWalletBill;
import com.coin.wallet.service.*;
import com.coin.wallet.utils.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service("collectionStrategyBTC_TOKEN")
public class CollectionStrategyBTC_TOKEN implements ICollectionStrategy {

    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private UserWalletBillService userWalletBillService;
    @Autowired
    private ConfigWalletService configWalletService;
    @Autowired
    private UserWalletService userWalletService;

    @Override
    public void execute(CoinConfig coinConfig) {

        //查询需要归集的数据
        List<UserWalletBill> needCollectionList = userWalletBillService.list(new QueryWrapper<>(UserWalletBill.builder()
                .coinType(coinConfig.getCoinType())
                .direction(DirectionConstant.IN)
                .transferFlag(FlagConstant.UN_PROCESS)
                .build()));

        if (needCollectionList == null || needCollectionList.size() == 0) {
            return;
        }

        //相同地址合并
        Map<String, BigDecimal> map = needCollectionList.stream().collect(Collectors.toMap(UserWalletBill::getReceiverAddress, UserWalletBill::getAmount, BigDecimal::add));

        //查询归集地址
        ValidatorUtils.isEmptyThrow(coinConfig.getBossAddress(), "冷地址未配置");

        //手续费地址
        ConfigWallet feeWallet = configWalletService.getOne(
                new QueryWrapper<>(ConfigWallet.builder().coinType(coinConfig.getCoinType())
                        .walletType(WalletTypeConstant.FEE).build()), true);
        ValidatorUtils.isNullThrow(feeWallet, "手续费地址未配置");

        //归集处理
        for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
            BigDecimal amount = entry.getValue();
            String senderAddress = entry.getKey();

            //最低归集金额校验
            if (coinConfig.getMinCollectionAmount().compareTo(amount) > 0) continue;

            UserWallet userWallet = userWalletService.cacheUserWallet(coinConfig.getCoinType(), senderAddress);
            transactionService.btcTokenCollection(coinConfig, userWallet, feeWallet, amount);

            needCollectionList.forEach(walletBill -> {
                if (walletBill.getReceiverAddress().equalsIgnoreCase(senderAddress)) {
                    userWalletBillService.update(
                            UserWalletBill.builder()
                                    .transferFlag(FlagConstant.PROCESSED)
                                    .build(),
                            new QueryWrapper<>(UserWalletBill.builder()
                                    .id(walletBill.getId())
                                    .transferFlag(FlagConstant.UN_PROCESS)
                                    .build())
                    );
                }
            });
        }
    }
}
