package com.wallet.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wallet.dto.CreateAddressParamDto;
import com.wallet.dto.CreateAddressResultDto;
import com.wallet.entity.UserWallet;
import com.wallet.enums.CoinTypeEnum;
import com.wallet.exception.CommonException;
import com.wallet.service.ICreateAddressStrategy;
import com.wallet.service.UserWalletService;
import com.wallet.utils.AesUtils;

import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("createAddressStrategyBTC")
public class CreateAddressStrategyBTC implements ICreateAddressStrategy {

    @Autowired
    private UserWalletService userWalletService;
    @Autowired
    private NetworkParameters networkParameters;
    @Value("${btc.user-wallet.encrypt-key}")
    private String encryptKey;

    /**
     * 创建BTC钱包
     *
     * @param paramDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreateAddressResultDto execute(CoinTypeEnum coinTypeEnum, CreateAddressParamDto paramDto) {
        UserWallet userWallet = userWalletService.getOne(new QueryWrapper<>(UserWallet.builder()
                .userId(paramDto.getUserId())
                .coinType(coinTypeEnum.getCoinType())
                .build()), true);

        if (userWallet == null) {
            //创建钱包
            try {
                ECKey key = new ECKey();
                userWallet = UserWallet.builder()
                        .userId(paramDto.getUserId())
                        .coinType(coinTypeEnum.getCoinType())
                        .address(key.toAddress(networkParameters).toBase58())
                        .privateKey(AesUtils.aesEncrypt(key.getPrivateKeyAsWiF(networkParameters), encryptKey))
                        .flag(true)
                        .build();
                userWalletService.save(userWallet);
            } catch (Exception e) {
                log.error("CreateAddressStrategyBTC.execute={}", JSON.toJSONString(paramDto), e);
                throw CommonException.FAILURE("生成钱包地址失败，请重试");
            }
        }

        return CreateAddressResultDto.builder()
                .userId(userWallet.getUserId())
                .coinType(userWallet.getCoinType())
                .address(userWallet.getAddress())
                .build();
    }
}
