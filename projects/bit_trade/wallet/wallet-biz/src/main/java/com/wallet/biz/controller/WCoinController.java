package com.wallet.biz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.api.service.IWCoinService;
import com.wallet.biz.pojo.model.WCoin;
import com.wallet.biz.pojo.vo.CoinTypeVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wCoin" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WCoinController {

    @Autowired
    IWCoinService wCoinService;

    @GetMapping("/rechargecoinlist")
    @ApiOperation(value = "可充币种列表", notes = "可充币种列表")
    public ReturnDTO rechargecoinlist(){
        List<WCoin> Rechargecoinlist = wCoinService.list(new QueryWrapper<>(WCoin.builder()
                .isRecharge("E").build()));
        if (null == Rechargecoinlist){
            return ReturnDTO.error("");
        }
        return getReturnDTO(Rechargecoinlist);
    }

    @GetMapping("/Withdrawcoinlist")
    @ApiOperation(value = "可充币种列表", notes = "可充币种列表")
    public ReturnDTO withdrawcoinlist(){
        List<WCoin> Withdrawcoinlist = wCoinService.list(new QueryWrapper<>(WCoin.builder()
                .isWithdraw("E").build()));
        return getReturnDTO(Withdrawcoinlist);
    }

    private ReturnDTO getReturnDTO(List<WCoin> WCoinlist) {
        List<CoinTypeVO> coinlist = new ArrayList<>();
        for (WCoin list : WCoinlist){
            CoinTypeVO coin = new CoinTypeVO();
            coin.setCoinType(list.getCoinType());
            coin.setToken(list.getToken());
            coinlist.add(coin);
        }
        return ReturnDTO.ok(coinlist);
    }
}
