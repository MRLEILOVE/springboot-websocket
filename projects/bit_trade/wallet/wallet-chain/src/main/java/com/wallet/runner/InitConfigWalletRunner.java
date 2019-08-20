package com.wallet.runner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wallet.entity.ConfigWallet;
import com.wallet.enums.CoinTypeEnum;
import com.wallet.enums.WalletTypeEnum;
import com.wallet.exception.CommonException;
import com.wallet.service.ConfigWalletService;
import com.wallet.service.IJsonRpcService;
import com.wallet.utils.AesUtils;

import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 配置钱包初始化
 */
@Slf4j
@Component
public class InitConfigWalletRunner implements CommandLineRunner {

    @Autowired
    private IJsonRpcService jsonRpcService;
    @Autowired
    private ConfigWalletService configWalletService;
    @Autowired
    private NetworkParameters networkParameters;

    @Value("${btc.config-wallet.encrypt-key}")
    private String encryptKey;

    /**
     * 初始化配置钱包
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) {

        for (CoinTypeEnum coinTypeEnum : CoinTypeEnum.values()) {
            for (WalletTypeEnum walletTypeEnum : WalletTypeEnum.values()) {

                if (coinTypeEnum == CoinTypeEnum.BTC_TOKEN || coinTypeEnum == CoinTypeEnum.BTC) {
                    btcAndBtcToken(coinTypeEnum, walletTypeEnum);
                }

            }
        }
    }

    private void btcAndBtcToken(CoinTypeEnum coinTypeEnum, WalletTypeEnum walletTypeEnum) {
        ConfigWallet configWallet = configWalletService.getOne(new QueryWrapper<>(ConfigWallet.builder()
                .coinType(coinTypeEnum.getCoinType())
                .walletType(walletTypeEnum.getType())
                .build()), true);
        if (configWallet == null) {
            //创建配置钱包
            try {
                ECKey key = new ECKey();
                configWalletService.save(ConfigWallet.builder()
                        .coinType(coinTypeEnum.getCoinType())
                        .walletType(walletTypeEnum.getType())
                        .address(key.toAddress(networkParameters).toBase58())
                        .keystore(AesUtils.aesEncrypt(key.getPrivateKeyAsWiF(networkParameters), encryptKey))
                        .build());
                jsonRpcService.importAddress(key.toAddress(networkParameters).toBase58());
            } catch (Exception e) {
                log.error("InitConfigWalletRunner.btcAndBtcToken={}", e);
                throw CommonException.FAILURE("创建配置钱包出错");
            }
        }
    }
}
