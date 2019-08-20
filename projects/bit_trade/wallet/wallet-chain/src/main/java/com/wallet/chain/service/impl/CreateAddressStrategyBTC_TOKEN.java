package com.wallet.chain.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wallet.chain.dto.CreateAddressParamDto;
import com.wallet.chain.dto.CreateAddressResultDto;
import com.wallet.chain.enums.CoinTypeEnum;
import com.wallet.chain.service.ICreateAddressStrategy;

@Slf4j
@Service("createAddressStrategyBTC_TOKEN")
public class CreateAddressStrategyBTC_TOKEN implements ICreateAddressStrategy {

    @Autowired
    @Qualifier("createAddressStrategyBTC")
    private ICreateAddressStrategy createAddressStrategy;

    /**
     * 创建BTC_TOKEN钱包
     *
     * @param paramDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreateAddressResultDto execute(CoinTypeEnum coinTypeEnum, CreateAddressParamDto paramDto) {
        return createAddressStrategy.execute(coinTypeEnum, paramDto);
    }
}
