package com.bittrade.currency.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.bittrade.pojo.model.TCurrency;
import com.bittrade.pojo.vo.CoinVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bittrade.currency.api.service.ITWalletTransferService;
import com.bittrade.pojo.dto.TWalletTransferDTO;
import com.bittrade.pojo.dto.TransferDto;
import com.bittrade.pojo.model.TWalletTransfer;
import com.bittrade.pojo.vo.TWalletTransferVO;
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
@RequestMapping(value = { "/tWalletTransfer" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TWalletTransferController extends BaseController<TWalletTransfer, TWalletTransferDTO, TWalletTransferVO, ITWalletTransferService> {
    @Autowired
    private ITWalletTransferService walletTransferService;

    @PostMapping("/transferOfFunds")
    @ApiOperation(value="资金划转", notes="资金划转")
    @ResponseBody
    public ReturnDTO transferOfFunds(@ALoginUser LoginUser user, @RequestBody TransferDto transferDto){
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
            return walletTransferService.transferOfFunds(transferDto);
        }catch (Exception e){
            e.printStackTrace();
            return ReturnDTO.error("服务器繁忙，请稍后重试");
        }
    }

    @ApiOperation(value="查询用户钱包可用余额", notes="传用户id，账户id币种id")
    @GetMapping(value = "/availableBalance/{accountId}/{currencyName}")
    public ReturnDTO<BigDecimal> availableBalanceFeign(@ALoginUser LoginUser user,@PathVariable("accountId")Long accountId, @PathVariable("currencyName")String currencyName){
        if(user == null || user.getUser_id() == null){
            return ReturnDTO.error("用户未登录");
        }
        return ReturnDTO.ok(walletTransferService.availableBalance(user.getUser_id(), accountId,currencyName));
    }

    @ApiOperation(value="两个账户共同币种", notes="两个账户共同币种")
    @GetMapping(value = "/togetherCoin/{accountId1}/{accountId2}")
    public ReturnDTO<List<CoinVo>> togetherCoin(@PathVariable("accountId1")Long accountId1, @PathVariable("accountId2")Long accountId2){
        return ReturnDTO.ok(walletTransferService.togetherCoin( accountId1,accountId2));
    }
}
