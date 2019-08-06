package com.bittrade.currency.controller;

import com.bittrade.currency.api.service.ITransferService;
import com.bittrade.pojo.dto.TransferDto;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.web.common.entity.LoginUser;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/tf")
public class TransferController {
    @Autowired
    private ITransferService transferService;

    @PostMapping("/transferOfFundsB2C")
    @ApiOperation(value="资金划转", notes="币币账户划转c2c账户")
    @ResponseBody
    public ReturnDTO transferOfFundsB2C(@ALoginUser LoginUser user,@RequestBody TransferDto transferDto){
        if(user == null || user.getUser_id() == null){
            return ReturnDTO.error("用户未登录");
        }else{
            transferDto.setUserId(user.getUser_id());
        }
        if(StringUtils.isEmpty(transferDto.getCurrency())){
            return ReturnDTO.error("币种不能为空");
        }
        if(null == transferDto.getNum() || transferDto.getNum().compareTo(BigDecimal.ZERO) <= 0){
            return ReturnDTO.error("划转数量必须正整数");
        }
        try{
            return transferService.transferOfFundsB2C(transferDto);
        }catch (Exception e){
            e.printStackTrace();
            return ReturnDTO.error("服务器繁忙，请稍后重试");
        }
    }
}
