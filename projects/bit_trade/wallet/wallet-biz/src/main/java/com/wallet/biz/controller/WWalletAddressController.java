package com.wallet.biz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.pojo.model.WWalletAddress;
import com.bittrade.pojo.vo.CoinTypeVO;
import com.bittrade.pojo.vo.WalletAddressVO;
import com.common.bittrade.service.IWCoinService;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.web.constant.entity.LoginUser;
import com.wallet.biz.api.service.IWWalletAddressService;
import com.wallet.biz.api.service.IwalletCaseService;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wWalletAddress" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WWalletAddressController {

    @Autowired
    IWWalletAddressService wWalletAddressService;
    @Autowired
    IWCoinService wCoinService;
    @Autowired
    private IwalletCaseService caseService;

    @GetMapping("/firstaddress")
    @ApiOperation(value = "第一提币地址", notes = "第一提币地址")
    public ReturnDTO firstaddress(@RequestBody @Validated CoinTypeVO coinTypeVO, @ALoginUser LoginUser user) {
        Long userId = user == null ? null : user.getUser_id();
        if (userId == null) {
            return ReturnDTO.error("用户未登录");
        }
        WWalletAddress wWalletAddress = wWalletAddressService.getOne(new QueryWrapper<>(WWalletAddress.builder()
                .userId(userId)
                .tokenType(coinTypeVO.getToken())
                .status((byte)1).build()));
        return ReturnDTO.ok(wWalletAddress.getAddress());
    }

    @GetMapping("/addresslist")
    @ApiOperation(value = "提币地址列表", notes = "提币地址列表")
    public ReturnDTO addresslist(@RequestBody @Validated CoinTypeVO coinTypeVO, @ALoginUser LoginUser user) {
        Long userId = user == null ? null : user.getUser_id();
        if (userId == null) {
            return ReturnDTO.error("用户未登录");
        }
        List<WWalletAddress> list = wWalletAddressService.list(new QueryWrapper<>(WWalletAddress.builder()
                .userId(userId)
                .tokenType(coinTypeVO.getToken())
                .status((byte)1).build()));
        return ReturnDTO.ok(list);
    }

    @PostMapping("/addaddress")
    @ApiOperation(value = "添加提币地址", notes = "添加提币地址")
    public ReturnDTO addaddress(@RequestBody @Validated WalletAddressVO walletAddressVO, @ALoginUser LoginUser user) {
        Long userId = user == null ? null : user.getUser_id();
        if (userId == null) {
            return ReturnDTO.error("用户未登录");
        }
        return caseService.addaddress(userId, walletAddressVO);
    }

}
