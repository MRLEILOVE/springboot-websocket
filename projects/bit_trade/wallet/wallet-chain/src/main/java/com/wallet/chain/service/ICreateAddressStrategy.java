package com.wallet.chain.service;

import com.wallet.chain.dto.CreateAddressParamDto;
import com.wallet.chain.dto.CreateAddressResultDto;
import com.wallet.chain.enums.CoinTypeEnum;

public interface ICreateAddressStrategy {

    /**
     * 创建钱包
     *
     * @param paramDto
     * @return
     */
    CreateAddressResultDto execute(CoinTypeEnum coinTypeEnum, CreateAddressParamDto paramDto);
}
