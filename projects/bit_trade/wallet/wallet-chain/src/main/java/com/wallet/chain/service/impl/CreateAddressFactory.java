package com.wallet.chain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.wallet.chain.dto.CreateAddressParamDto;
import com.wallet.chain.dto.CreateAddressResultDto;
import com.wallet.chain.enums.CoinTypeEnum;
import com.wallet.chain.service.ICreateAddressFactory;
import com.wallet.chain.service.ICreateAddressStrategy;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateAddressFactory implements ICreateAddressFactory {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public List<CreateAddressResultDto> create(CreateAddressParamDto paramDto) {

        List<CreateAddressResultDto> list = new ArrayList<>();
        for (CoinTypeEnum coinTypeEnum : CoinTypeEnum.values()) {
            ICreateAddressStrategy createAddressService = applicationContext.getBean("createAddressStrategy" + coinTypeEnum.getCoinType(), ICreateAddressStrategy.class);
            CreateAddressResultDto createAddressResultDto = createAddressService.execute(coinTypeEnum,paramDto);
            list.add(createAddressResultDto);
        }
        return list;
    }
}
