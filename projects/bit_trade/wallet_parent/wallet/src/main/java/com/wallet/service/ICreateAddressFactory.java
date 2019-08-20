package com.wallet.service;

import java.util.List;

import com.wallet.dto.CreateAddressParamDto;
import com.wallet.dto.CreateAddressResultDto;

public interface ICreateAddressFactory {

    /**
     * 创建钱包
     *
     * @param paramDto
     * @return
     */
    List<CreateAddressResultDto> create(CreateAddressParamDto paramDto);
}
