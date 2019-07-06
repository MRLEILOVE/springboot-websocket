package com.coin.wallet.service.impl;

import com.coin.wallet.dto.CreateAddressParamDto;
import com.coin.wallet.dto.CreateAddressResultDto;
import com.coin.wallet.enums.CoinTypeEnum;
import com.coin.wallet.service.ICreateAddressFactory;
import com.coin.wallet.service.ICreateAddressStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

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
