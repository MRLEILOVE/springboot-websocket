package com.coin.wallet.service;

import com.coin.wallet.dto.CreateAddressParamDto;
import com.coin.wallet.dto.CreateAddressResultDto;

import java.util.List;

public interface ICreateAddressFactory {

    /**
     * 创建钱包
     *
     * @param paramDto
     * @return
     */
    List<CreateAddressResultDto> create(CreateAddressParamDto paramDto);
}
