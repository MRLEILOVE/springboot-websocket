package com.walletbiz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walletbiz.service.ITwalletFundAccountService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/assets")
public class AssetsController {
    @Autowired
    private ITwalletFundAccountService walletFundAccountService;


    @GetMapping("/getAssets")
    @ApiOperation(value = "查询用户的所有的usdt（资金账户，beta账户，法币账户）", notes = "查询用户的所有的usdt（资金账户，beta账户，法币账户）")
    public String getAssets(@RequestParam("userId") Long userId)  {
        return walletFundAccountService.getAssets(userId);
    }
}
