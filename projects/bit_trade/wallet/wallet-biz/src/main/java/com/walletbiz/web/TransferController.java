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

    @PostMapping("/transferOfFunds")
    @ApiOperation(value="资金划转", notes="资金划转")
    @ResponseBody
    public ReturnDTO<String> transferOfFunds(@RequestBody TransferDto transferDto){
        if(WebUtil.getLoginUser()==null || WebUtil.getLoginUser().getUser_id() == null){
            return ReturnDTO.error("用户未登录");
        }else{
            transferDto.setUserId(WebUtil.getLoginUser().getUser_id());
        }
        if(StringUtils.isEmpty(transferDto.getCurrency())){
            return ReturnDTO.error("币种不能为空");
        }
        if(null == transferDto.getAccountInId()){
            return ReturnDTO.error("转入账户不能为空");
        }
        if(null == transferDto.getAccountOutId()){
            return ReturnDTO.error("转出账户不能为空");
        }
        if(null == transferDto.getNum() || transferDto.getNum().compareTo(BigDecimal.ZERO) <= 0){
            return ReturnDTO.error("划转数量必须正整数");
        }
        /*if(StringUtils.isEmpty(transferDto.getPassword())){
            return WrapMapper.error("密码为空");
        }*/
        try{
            return walletFundAccountService.transferOfFunds(transferDto);
        }catch (Exception e){
            e.printStackTrace();
            return ReturnDTO.error("服务器繁忙，请稍后重试");
        }
    }

    @GetMapping("/amountBalance/{accountId}/{currency}")
    @ApiOperation(value="查詢账户余额", notes="查詢账户余额")
    @ResponseBody
    public ReturnDTO<String> amountBalance(@PathVariable("accountId")Integer accountId,@PathVariable("currency")String currency){
        if(WebUtil.getLoginUser()==null){
            return ReturnDTO.error("用户未登录");
        }
        Long userId = WebUtil.getLoginUser().getUser_id();
        String amountBalance = walletFundAccountService.amountBalance(userId,accountId,currency);
        return ReturnDTO.ok(amountBalance);
    }


/*    @PostMapping("/queryTransferRecord")
    @ApiOperation(value="查詢划转记录")
    @ResponseBody
    public Wrapper<Page<RecordVO>> queryTransferRecord(@RequestBody  AccountTypeDto accountTypeDto){
        if(RequestUtil.getCurrentUser()==null){
            return WrapMapper.error("用户未登录");
        }
        Long userId = RequestUtil.getCurrentUser().getUser_id();
        Page<RecordVO> page = walletFundAccountService.queryTransferRecord(userId,accountTypeDto);
        Wrapper<Page<RecordVO>> ok = WrapMapper.ok(page);
        return ok;
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

    @GetMapping("/accountEntryFeign")
    @ApiOperation(value="账户入账（资金划转，被远程调用）", notes="账户入账（资金划转，被远程调用）")
    @ResponseBody
    public String accountEntryFeign(@RequestParam Long userId,@RequestParam String currency,@RequestParam BigDecimal num,@RequestParam Integer type) throws Exception {
        return walletFundAccountService.accountEntryFeign(userId,currency,num,type);
    }

    @GetMapping("/getTypeFeign")
    @ApiOperation(value="获取划转类型", notes="获取划转类型")
    @ResponseBody
    public Integer getTypeFeign(@RequestParam Integer accountInId,@RequestParam Integer accountOutId) {
        return walletFundAccountService.getType(accountInId,accountOutId);
    }
}
