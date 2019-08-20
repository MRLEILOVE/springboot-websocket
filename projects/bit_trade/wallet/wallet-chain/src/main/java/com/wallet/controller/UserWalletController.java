package com.wallet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.dto.CommonResultDto;
import com.wallet.dto.CreateAddressParamDto;
import com.wallet.dto.CreateAddressResultDto;
import com.wallet.service.IJsonRpcService;
import com.wallet.service.UserWalletService;
import com.wallet.service.impl.CreateAddressFactory;
import com.wallet.utils.AesUtils;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 钱包 前端控制器
 * </p>
 *
 * @author xxx
 * @since 2019-06-27
 */
@RestController
@RequestMapping
public class UserWalletController {

    @Autowired
    private CreateAddressFactory createAddressFactory;

    @Autowired
    private IJsonRpcService jsonRpcService;

    @PostMapping("/create")
    public CommonResultDto<List<CreateAddressResultDto>> create(@RequestBody @Valid CreateAddressParamDto paramDto) {


        return CommonResultDto.SUCCESS_DATA(createAddressFactory.create(paramDto));
    }
}

