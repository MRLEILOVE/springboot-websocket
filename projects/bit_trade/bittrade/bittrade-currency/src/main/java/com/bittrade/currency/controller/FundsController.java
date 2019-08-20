package com.bittrade.currency.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.pojo.vo.ConversionVo;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.web.constant.entity.LoginUser;

import io.swagger.annotations.ApiOperation;

@Controller
@ResponseBody
@RequestMapping(value = { "/funds" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FundsController {
    @Autowired
    private ITWalletService walletService;

    @ApiOperation(value = "总净资产")
    @GetMapping(value = "/totalNetAssets")
    @ResponseBody
    public ReturnDTO<ConversionVo> totalNetAssets(@ALoginUser LoginUser user) {
        if(user == null){
            //没有登陆
            ConversionVo vo = ConversionVo.builder().USDT(BigDecimal.ZERO).CNY(BigDecimal.ZERO).build();
            return ReturnDTO.ok(vo);
        }
        ReturnDTO<ConversionVo> returnDTO = walletService.totalNetAssets(user.getUser_id());
        return returnDTO;
    }
}
