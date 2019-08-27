package com.bittrade.currency.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.pojo.vo.AssetsVO;
import com.bittrade.pojo.vo.CoinAccountVO;
import com.bittrade.pojo.vo.ConversionVo;
import com.bittrade.pojo.vo.TWalletVO;
import com.bittrade.pojo.vo.UserWalletVO;
import com.common.bittrade.service.ITWalletService;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.framework.base.controller.BaseController;
import com.core.web.constant.entity.LoginUser;

import io.swagger.annotations.ApiOperation;

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
    public ReturnDTO<ConversionVo> conversionTotal(@ALoginUser LoginUser user) {
        if(user == null || user.getUser_id() == null){
            //没有登录，返回余额为0
            return ReturnDTO.ok(ConversionVo.builder().USDT(BigDecimal.ZERO).CNY(BigDecimal.ZERO).build());
        }
        return ReturnDTO.ok(walletService.conversionTotal(user.getUser_id()));
    }

    @GetMapping("/detail")
    @ApiOperation(value = "用户钱包列表", notes = "用户钱包列表")
    public ReturnDTO<List<AssetsVO>> detail(@ALoginUser LoginUser user){
        Long userId = user == null ? null : user.getUser_id();
        if(userId == null){
            return ReturnDTO.ok(null);
        }
        return ReturnDTO.ok(walletService.detail(userId));
    }
}
