package com.bittrade.c2c.controller;

import com.bittrade.c2c.service.ITLegalCurrencyAccountService;
import com.bittrade.pojo.dto.TLegalCurrencyAccountDTO;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.bittrade.pojo.vo.AssetsVO;
import com.bittrade.pojo.vo.ConversionVo;
import com.bittrade.pojo.vo.TLegalCurrencyAccountVO;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.framework.base.controller.BaseController;
import com.core.web.common.entity.LoginUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tLegalCurrencyAccount" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TLegalCurrencyAccountController extends BaseController<TLegalCurrencyAccount, TLegalCurrencyAccountDTO, TLegalCurrencyAccountVO, ITLegalCurrencyAccountService> {
    @Autowired
    private ITLegalCurrencyAccountService legalCurrencyAccountService;

    @PostMapping("/conversionTotal")
    @ApiOperation(value = "c2c账户总资金折合", notes = "总资金折合")
    @ResponseBody
    public ReturnDTO<ConversionVo> totalConversion(@ALoginUser LoginUser user){
        if (user == null || user.getUser_id() == null) {
            return ReturnDTO.ok(ConversionVo.builder().CNY(BigDecimal.ZERO).USDT(BigDecimal.ZERO).build());
        }
        ConversionVo conversionVo = legalCurrencyAccountService.totalConversion(user.getUser_id());
        return ReturnDTO.ok(conversionVo);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "查询当前用户的法币账户钱包列表", notes = "查询当前用户的法币账户钱包列表")
    @ResponseBody
    public ReturnDTO<List<AssetsVO>> detail(@ALoginUser LoginUser user){
        Long userId = user == null ? null : user.getUser_id();
        if(userId == null){
            return ReturnDTO.ok(null);
        }
        return ReturnDTO.ok(legalCurrencyAccountService.detail(userId));
    }

    @ApiOperation(value="查询用户钱包可用余额", notes="传用户id，币种名称")
    @GetMapping(value = "/availableBalanceFeign")
    @ResponseBody
    public String availableBalanceFeign(@RequestParam("userId")Long userId, @RequestParam("coinName")String coinName){
        return legalCurrencyAccountService.availableBalance(userId, coinName);
    }
}
