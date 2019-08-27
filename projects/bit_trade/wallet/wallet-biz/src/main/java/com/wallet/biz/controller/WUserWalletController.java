package com.wallet.biz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.vo.CoinTypeVO;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.web.constant.entity.LoginUser;
import com.wallet.biz.api.service.IwalletCaseService;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wUserWallet" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WUserWalletController {

    @Autowired
    private IwalletCaseService caseService;

    @PostMapping("chongbi")
    @ApiOperation(value = "充币")
    public ReturnDTO chongbi(@RequestBody @Validated CoinTypeVO coinTypeVO, @ALoginUser LoginUser user){
        Long userId = user == null ? null : user.getUser_id();
        if(userId == null){
            return ReturnDTO.error("用户未登录");
        }
        return caseService.chongbi(userId, coinTypeVO);
    }

    @PostMapping("qrCode")
    @ApiOperation(value = "二维码", notes = "二维码")
    public ReturnDTO qrCode(@RequestBody @Validated CoinTypeVO coinTypeVO, @ALoginUser LoginUser user) {
        Long userId = user == null ? null : user.getUser_id();
        if (userId == null) {
            return ReturnDTO.error("用户未登录");
        }
        return caseService.qrCode(userId, coinTypeVO);
    }
}

