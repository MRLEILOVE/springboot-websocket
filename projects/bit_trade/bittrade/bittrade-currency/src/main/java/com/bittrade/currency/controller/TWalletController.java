package com.bittrade.currency.controller;

import com.bittrade.pojo.vo.*;
import com.core.common.annotation.ALoginUser;
import com.core.web.common.entity.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.model.TWallet;
import com.core.common.DTO.ReturnDTO;
import com.core.framework.base.controller.BaseController;

import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tWallet" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TWalletController extends BaseController<TWallet, TWalletDTO, TWalletVO, ITWalletService> {
    @Autowired
    private ITWalletService walletService;

    @ApiOperation(value="查询用户的币币账户", notes="查询用户的币币账户")
    @GetMapping(value = "/queryCoinAccountByUserId/{userId}")
    @ResponseBody
    public ReturnDTO<CoinAccountVO> queryCoinAccountByUserId(@PathVariable("userId")Integer userId){
        try{
            return ReturnDTO.ok(walletService.queryCoinAccountByUserId(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnDTO.error("服务器异常");
        }

    }

    @ApiOperation(value="查询用户钱包", notes="传用户id，交易对id")
    @GetMapping(value = "/queryUserWallet/{userId}/{currencyTradeId}")
    @ResponseBody
    public ReturnDTO<UserWalletVO> queryUserWallet(@PathVariable("userId")Integer userId, @PathVariable("currencyTradeId")Integer currencyTradeId){
        try {
            return ReturnDTO.ok(walletService.queryUserWallet(userId,currencyTradeId));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnDTO.error("服务器异常");
        }
    }

    @GetMapping("/conversionTotal")
    @ApiOperation(value = "用户的币币账户总资金折合", notes = "用户的币币账户总资金折合")
    public ReturnDTO<ConversionVo> totalConversion(@ALoginUser LoginUser user) {
        if(user == null || user.getUser_id() == null){
            //没有登录，返回余额为0
            return ReturnDTO.ok(ConversionVo.builder().USDT(BigDecimal.ZERO).CNY(BigDecimal.ZERO).build());
        }
        return ReturnDTO.ok(walletService.totalConversion(user.getUser_id()));
    }

    @PostMapping("/detail")
    @ApiOperation(value = "查询当前用户的币币账户币种余额列表", notes = "查询当前用户的币币账户币种余额列表")
    public ReturnDTO<List<AccountVO>> detail(@ALoginUser LoginUser user){
        Long userId = user == null ? null : user.getUser_id();
        return ReturnDTO.ok(walletService.detail(userId));
    }
}
