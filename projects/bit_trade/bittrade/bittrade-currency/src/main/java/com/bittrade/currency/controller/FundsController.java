package com.bittrade.currency.controller;

import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.pojo.vo.ConversionVo;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.web.common.entity.LoginUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

   /* @ApiOperation(value = "资产总览")
    @GetMapping(value = "/overview")
    @ResponseBody
    public ReturnDTO<List<ConversionVo>> overview(@ALoginUser LoginUser user) {
        if(user == null){
            //没有登陆
            List<ConversionVo> list = new ArrayList<>();
            list.add(ConversionVo.builder().USDT(BigDecimal.ZERO).CNY(BigDecimal.ZERO).account("资金账户").build());
            list.add(ConversionVo.builder().USDT(BigDecimal.ZERO).CNY(BigDecimal.ZERO).account("交易账户").build());
            list.add(ConversionVo.builder().USDT(BigDecimal.ZERO).CNY(BigDecimal.ZERO).account("法币账户").build());
            list.add(ConversionVo.builder().USDT(BigDecimal.ZERO).CNY(BigDecimal.ZERO).account("币币账户").build());
            return ReturnDTO.ok(list);
        }
        ReturnDTO<List<ConversionVo>> returnDTO = walletService.overview(user.getUser_id());
        return returnDTO;
    }*/
}
