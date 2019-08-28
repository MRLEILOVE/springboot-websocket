package com.common.bittrade.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.TLegalCurrencyAccountDTO;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.bittrade.pojo.vo.AssetsVO;
import com.bittrade.pojo.vo.ConversionVo;
import com.bittrade.pojo.vo.TLegalCurrencyAccountVO;
import com.common.bittrade.service.ITLegalCurrencyAccountService;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.framework.base.controller.BaseController;
import com.core.web.constant.entity.LoginUser;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

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

    @GetMapping("/conversionTotal")
    @ApiOperation(value = "c2c账户总资金折合", notes = "总资金折合")
    @ResponseBody
    public ReturnDTO<ConversionVo> totalConversion(@ALoginUser LoginUser user){
        if (user == null || user.getUser_id() == null) {
            return ReturnDTO.ok(ConversionVo.builder().CNY(BigDecimal.ZERO).USDT(BigDecimal.ZERO).build());
        }
        ConversionVo conversionVo = legalCurrencyAccountService.totalConversion(user.getUser_id());
        return ReturnDTO.ok(conversionVo);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "查询当前用户的c2c账户钱包列表", notes = "查询当前用户的c2c账户钱包列表")
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

    @ApiModelProperty(value = "获取用户法币账户总的usdt数量",notes = "传用户id")
    @GetMapping("/getAssetsFeign")
    @ResponseBody
    public BigDecimal getAssetsFeign(@RequestParam("userId")Long userId){
        BigDecimal BigDecimal = legalCurrencyAccountService.getAssets(userId);
//        ConversionVo conversionVo = legalCurrencyAccountService.totalConversion(userId);
        return BigDecimal;
    }

}
