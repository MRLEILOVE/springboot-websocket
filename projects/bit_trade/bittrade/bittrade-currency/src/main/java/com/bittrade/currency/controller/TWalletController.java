package com.bittrade.currency.controller;

import com.bittrade.pojo.vo.CoinAccountVO;
import com.bittrade.pojo.vo.UserWalletVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.currency.dao.ITWalletDAO;
import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.vo.TWalletVO;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.api.service.ITWalletService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tWallet" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TWalletController extends BaseController<TWallet, TWalletDTO, TWalletVO, ITWalletDAO, ITWalletService<TWallet, TWalletDTO, TWalletVO, ITWalletDAO>> {
    @Autowired
    private ITWalletService<TWallet, TWalletDTO, TWalletVO, ITWalletDAO> walletService;

    @ApiOperation(value="查询用户的币币账户", notes="查询用户的币币账户")
    @GetMapping(value = "/queryCoinAccountByUserId/{userId}")
    @ResponseBody
    public CoinAccountVO queryCoinAccountByUserId(@PathVariable("userId")Integer userId){
        return walletService.queryCoinAccountByUserId(userId);
    }

    @ApiOperation(value="查询用户钱包", notes="传用户id，交易对id")
    @GetMapping(value = "/queryUserWallet/{userId}/{currencyTradeId}")
    @ResponseBody
    public UserWalletVO queryUserWallet(@PathVariable("userId")Integer userId, @PathVariable("currencyTradeId")Integer currencyTradeId){
        return walletService.queryUserWallet(userId,currencyTradeId);
    }

}
