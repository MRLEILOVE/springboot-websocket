package com.wallet.biz.controller;

import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.web.constant.entity.LoginUser;
import com.wallet.biz.api.service.IwalletCaseService;
import com.wallet.biz.pojo.vo.AddressParamDto;
import com.wallet.biz.pojo.vo.WithdrawBillParamVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ReturnDTO chongbi(@RequestBody @Validated AddressParamDto addressParamDto, @ALoginUser LoginUser user){
        Long userId = user == null ? null : user.getUser_id();
        if(userId == null){
            return ReturnDTO.error("用户未登录");
        }
        return caseService.chongbi(userId,addressParamDto);
    }
/*
    public String chongbi() throws FlowException {

        CreateAddressParamDto paramDto = new CreateAddressParamDto();
        paramDto.setUserId(userId);
        String s = createAddressFactory.create(paramDto);
        System.out.println(s);
        if (s.contains("BTC_TOKEN")) {
            s = s.replaceAll("BTC_TOKEN", "USDT");
        }
        return s;
    }
   /*
    @PostMapping("qrCode")
    @ApiOperation(value = "二维码", notes = "二维码")
    public Wrapper qrCode(@RequestBody @Validated CoinTypeDto coinTypeDto) throws FlowException, FileNotFoundException {
        Long userId = RequestUtil.getCurrentUser().getUser_id();
        if (userId == null) {
            throw new FlowException("用户未登录");
        }
        EntityWrapper<WUserWallet>entityWrapper=new EntityWrapper<>();
        if(CoinType.USDT.toString().equals(coinTypeDto.getToken())){
            coinTypeDto.setToken("BTC_TOKEN");
        }
        entityWrapper.eq("user_id",userId).eq("coin_type",coinTypeDto.getToken());
        WUserWallet wUserWallet1 = wUserWalletService.selectOne(entityWrapper);
        if (null==wUserWallet1){
            return WrapMapper.error("该用户还没有钱包地址");
        }
        if (null!=coinTypeDto.getUrl()&&!"".equals(coinTypeDto.getUrl())){
            wUserWallet1.setCodeQr(coinTypeDto.getUrl());
            boolean b = wUserWalletService.updateById(wUserWallet1);
            if (b){
                return WrapMapper.ok("success");
            }
            return WrapMapper.error("error");
        }
        if (null==wUserWallet1.getCodeQr()||"".equals(wUserWallet1.getCodeQr())){
            return WrapMapper.error("该用户还没有二维码");
        }
        return WrapMapper.ok(wUserWallet1.getCodeQr());
    }*/
	
}
