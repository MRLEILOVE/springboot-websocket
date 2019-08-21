package com.wallet.biz.controller;

import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.web.constant.entity.LoginUser;
import com.wallet.biz.api.service.IWWalletAccountService;
import com.wallet.biz.pojo.vo.AssetsVO;
import com.wallet.biz.pojo.vo.ConversionVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wWalletAccount" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WWalletAccountController {
    @Autowired
    private IWWalletAccountService walletAccountService;

    @GetMapping("conversionTotal")
    @ApiOperation(value = "总资金折合", notes = "总资金折合")
    public ReturnDTO<ConversionVo> totalConversion(@ALoginUser LoginUser user){
        if (user == null || user.getUser_id() == null) {
            return ReturnDTO.ok(ConversionVo.builder().CNY(BigDecimal.ZERO).USDT(BigDecimal.ZERO).build());
        }
        ConversionVo conversionVo = walletAccountService.totalConversion(user.getUser_id());
        return ReturnDTO.ok(conversionVo);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "用户钱包列表", notes = "用户钱包列表")
    public ReturnDTO<List<AssetsVO>> detail(@ALoginUser LoginUser user){
        Long userId = user == null ? null : user.getUser_id();
        if(userId == null){
            return ReturnDTO.ok(null);
        }
        return ReturnDTO.ok(walletAccountService.detail(userId));
    }
}
