package com.wallet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.dto.CommonResultDto;
import com.wallet.dto.WithDrawParamDto;
import com.wallet.service.IWithdrawFactory;

import javax.validation.Valid;

/**
 * <p>
 * 提币钱包账单 前端控制器
 * </p>
 *
 * @author xxx
 * @since 2019-06-27
 */
@RestController
@RequestMapping
public class WithdrawWalletBillController {

    @Autowired
    private IWithdrawFactory withdrawFactory;

    /**
     * 提币
     *
     * @param paramDto
     * @return
     */
    @PostMapping("/withdraw")
    public CommonResultDto<String> create(@RequestBody @Valid WithDrawParamDto paramDto) {
        withdrawFactory.addOrder(paramDto);
        return CommonResultDto.SUCCESS();
    }
}

