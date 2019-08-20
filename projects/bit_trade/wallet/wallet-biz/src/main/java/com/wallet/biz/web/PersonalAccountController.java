package com.wallet.biz.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.common.DTO.ReturnDTO;
import com.core.web.tool.WebUtil;
import com.wallet.biz.Exception.FlowException;
import com.wallet.biz.service.PersonalAccountService;
import com.wallet.biz.vo.ConversionVo;
import com.wallet.biz.vo.PersonalAccountVO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/personalAcoount")
public class PersonalAccountController {
    @Autowired
    private PersonalAccountService personalaccountservice;

    @PostMapping("/conversionTotal")
    @ApiOperation(value = "法币账户总资金折合", notes = "总资金折合")
    public ReturnDTO<ConversionVo> totalConversion() throws FlowException {
        Long userId = WebUtil.getLoginUser().getUser_id();
        if (userId == null) {
            throw new FlowException("用户未登录");
        }
        ConversionVo conversionVo = personalaccountservice.totalConversion(userId);
        return ReturnDTO.ok(conversionVo);
    }

    @PostMapping("/list")
    @ApiOperation(value = "查询当前用户的法币账户币种余额列表", notes = "查询当前用户的法币账户币种余额列表")
    public ReturnDTO<List<PersonalAccountVO>> list() throws FlowException {
        Long userId = WebUtil.getLoginUser().getUser_id();
        if (userId == null) {
            throw new FlowException("用户未登录");
        }
        List<PersonalAccountVO> betaAccounts = personalaccountservice.list(userId);
        return ReturnDTO.ok(betaAccounts);
    }
}
