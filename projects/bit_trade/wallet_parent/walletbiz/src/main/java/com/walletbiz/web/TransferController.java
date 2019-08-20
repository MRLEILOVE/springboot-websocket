package com.walletbiz.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.common.DTO.ReturnDTO;
import com.core.web.tool.WebUtil;
import com.walletbiz.dto.AccountNameDto;
import com.walletbiz.dto.TransferDto;
import com.walletbiz.pojo.TAccountConfig;
import com.walletbiz.pojo.TAccountManage;
import com.walletbiz.service.ITwalletFundAccountService;
import com.walletbiz.vo.AssetRecordTypeVO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    private ITwalletFundAccountService walletFundAccountService;

/*    @GetMapping("/amountBalance/{accountId}/{currency}")
    @ApiOperation(value="查詢账户余额", notes="查詢账户余额")
    @ResponseBody
    public ReturnDTO<String> amountBalance(@PathVariable("accountId")Integer accountId,@PathVariable("currency")String currency){
        if(WebUtil.getLoginUser()==null){
            return ReturnDTO.error("用户未登录");
        }
        Long userId = WebUtil.getLoginUser().getUser_id();
        String amountBalance = walletFundAccountService.amountBalance(userId,accountId,currency);
        return ReturnDTO.ok(amountBalance);
    }*/


    /**
     * 根据账户名称查询可以划转的方向
     */
    @ApiOperation(value="查询账户列表", notes="查询账户列表")
    @PostMapping(value = "/queryAccountList")
    @ResponseBody
    public ReturnDTO<List<TAccountManage>> queryAccountDirectionList(@RequestBody AccountNameDto accountNameDto){
        return walletFundAccountService.queryAccountDirectionList(accountNameDto);
    }

    /**
     * 查询两个账户共同的币种
     */
    @ApiOperation(value="查询两个账户共同的币种", notes="查询两个账户共同的币种")
    @GetMapping(value = "/commonCurrency/{accountId1}/{accountId2}")
    @ResponseBody
    public ReturnDTO<List<TAccountConfig>> commonCurrency(@PathVariable("accountId1")Integer accountId1,@PathVariable("accountId2")Integer accountId2){
        return ReturnDTO.ok(walletFundAccountService.commonCurrency(accountId1,accountId2));
    }

    /**
     * 查找币种列表
     */
    @ApiOperation(value="查找币种列表", notes="查找币种列表")
    @GetMapping(value = "/currencyList")
    @ResponseBody
    public ReturnDTO<List<TAccountConfig>> currencyList(){
        return ReturnDTO.ok(walletFundAccountService.currencyList());
    }

    /**
     * 查找类型/划转方向
     */
    @ApiOperation(value="查找类型/划转方向（资金账户）", notes="查找类型/划转方向（资金账户）")
    @GetMapping(value = "/queryTypeForFund")
    @ResponseBody
    public ReturnDTO<List<AssetRecordTypeVO>> queryTypeForFund(){
        List<AssetRecordTypeVO> list = new ArrayList<>();
        list.add(new AssetRecordTypeVO(1,"提币"));
        list.add(new AssetRecordTypeVO(2,"充币"));
        list.add(new AssetRecordTypeVO(3,"转入"));
        list.add(new AssetRecordTypeVO(4,"转出"));
        return ReturnDTO.ok(list);
    }
}
