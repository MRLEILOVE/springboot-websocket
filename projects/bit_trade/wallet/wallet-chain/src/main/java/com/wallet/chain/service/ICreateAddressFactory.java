package com.wallet.chain.service;

import java.util.List;

import com.wallet.chain.dto.CreateAddressParamDto;
import com.wallet.chain.dto.CreateAddressResultDto;

public interface ICreateAddressFactory {

    /**
     * 创建钱包
     *
     * @param paramDto
     * @return
     */
    List<CreateAddressResultDto> create(CreateAddressParamDto paramDto);
}
