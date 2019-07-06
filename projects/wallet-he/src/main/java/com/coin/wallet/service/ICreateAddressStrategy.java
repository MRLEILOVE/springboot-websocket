package com.coin.wallet.service;

import com.coin.wallet.dto.CreateAddressParamDto;
import com.coin.wallet.dto.CreateAddressResultDto;
import com.coin.wallet.enums.CoinTypeEnum;

public interface ICreateAddressStrategy {

    /**
     * 创建钱包
     *
     * @param paramDto
     * @return
     */
    CreateAddressResultDto execute(CoinTypeEnum coinTypeEnum, CreateAddressParamDto paramDto);
}
