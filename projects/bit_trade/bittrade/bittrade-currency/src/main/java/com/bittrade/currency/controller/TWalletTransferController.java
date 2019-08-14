package com.bittrade.currency.controller;

import com.bittrade.pojo.dto.TransferDto;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.web.common.entity.LoginUser;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bittrade.currency.api.service.ITWalletTransferService;
import com.bittrade.pojo.dto.TWalletTransferDTO;
import com.bittrade.pojo.model.TWalletTransfer;
import com.bittrade.pojo.vo.TWalletTransferVO;
import com.core.framework.base.controller.BaseController;

import java.math.BigDecimal;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tWalletTransfer" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TWalletTransferController extends BaseController<TWalletTransfer, TWalletTransferDTO, TWalletTransferVO, ITWalletTransferService> {
    @Autowired
    private ITWalletTransferService walletTransferService;

    @PostMapping("/transferOfFundsB2C")
    @ApiOperation(value="资金划转", notes="币币账户划转c2c账户")
    @ResponseBody
    public ReturnDTO transferOfFundsB2C(@ALoginUser LoginUser user, @RequestBody TransferDto transferDto){
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
            return walletTransferService.transferOfFundsB2C(transferDto);
        }catch (Exception e){
            e.printStackTrace();
            return ReturnDTO.error("服务器繁忙，请稍后重试");
        }
    }

    @PostMapping("/biBiAccountEntry")
    @ApiOperation(value="币币账户充值", notes="币币账户充值")
    @ResponseBody
    public String biBiAccountEntry(@RequestBody TransferDto transferDto){
        System.out.println("用户id：" + transferDto.getUserId() + "币种： " +  transferDto.getCurrency() + "数量: " + transferDto.getNum());
        return walletTransferService.biBiAccountEntry(transferDto);
    }
}
