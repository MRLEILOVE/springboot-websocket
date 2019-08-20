package com.wallet.service;

import com.wallet.dto.CreateAddressParamDto;
import com.wallet.dto.CreateAddressResultDto;
import com.wallet.enums.CoinTypeEnum;

public interface ICreateAddressStrategy {

    /**
     * 创建钱包
     *
     * @param paramDto
     * @return
     */
    CreateAddressResultDto execute(CoinTypeEnum coinTypeEnum, CreateAddressParamDto paramDto);
}
