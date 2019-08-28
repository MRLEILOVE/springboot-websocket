package com.wallet.biz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.pojo.model.WCoin;
import com.bittrade.pojo.vo.MaxMinVo;
import com.common.bittrade.service.IWCoinService;
import com.wallet.biz.api.service.ITParamConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.vo.CoinTypeVO;
import com.bittrade.pojo.vo.WithdrawBillParamVo;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.web.constant.entity.LoginUser;
import com.wallet.biz.api.service.IWCoinConfigService;
import com.wallet.biz.api.service.IWOrderService;
import com.wallet.biz.api.service.IwalletCaseService;

import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wOrder" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WOrderController {

    @Autowired
    private IwalletCaseService caseService;
    @Autowired
    private IWCoinConfigService WCoinConfigService;
    @Autowired
    IWOrderService orderService;
    @Autowired
    ITParamConfigService tParamConfigService;

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
    @ApiOperation(value = "可提币种列表", notes = "可提币种列表")
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
            if(list.getToken().equals("BTC")){
                coin.setName("比特币");
            }else{
                coin.setName("泰达币");
            }
            coinlist.add(coin);
        }
        return ReturnDTO.ok(coinlist);
    }

    @PostMapping("confirmTibi")
    @ApiOperation(value = "确定提币", notes = "确定提币")
    public ReturnDTO confirmTibi(@RequestBody @Validated WithdrawBillParamVo withdrawBillParamVo, @ALoginUser LoginUser user) {
        //用户判断
        Long userId = 10086L;//user == null ? null : user.getUser_id();
        if (userId == null) {
            return ReturnDTO.error("用户未登录");
        }
//        if (user.checkPayPassWord(withdrawBillParamVo.getPassword())) {
//            return ReturnDTO.error("密码错误");
//        }
        return caseService.confirmTibi(withdrawBillParamVo, userId);
    }


    @GetMapping("rechargerecord")
    @ApiOperation(value = "充币记录", notes = "充币记录")
    public ReturnDTO rechargeRecord(/*@RequestBody @Validated CoinTypeVO coinTypeVO,*/ @ALoginUser LoginUser user) {
        Long userId = 10086L;//user == null ? null : user.getUser_id();
        if (userId == null) {
            return ReturnDTO.error("用户未登录");
        }
//        Page<OrderVO> page = orderService.queryFundrechargeRecord(userId,addressParamDto);
        return caseService.rechargeRecord(userId/*, coinTypeVO*/);
    }

    @GetMapping("withdrawrecord")
    @ApiOperation(value = "提币记录", notes = "提币记录")
    public ReturnDTO withdrawRecord(/*@RequestBody @Validated CoinTypeVO coinTypeVO,*/ @ALoginUser LoginUser user) {
        Long userId = 10086L;//user == null ? null : user.getUser_id();
        if (userId == null) {
            return ReturnDTO.error("用户未登录");
        }
//        Page<OrderVO> page = orderService.queryFundwithdrawRecord(userId,addressParamDto);
        return caseService.withdrawRecord(userId/*, coinTypeVO*/);
    }
    @GetMapping("feeMaxMin")
    @ApiOperation(value = "最大最小提币费率", notes = "最大最小提币费率")
    public ReturnDTO feeMaxMin(@RequestBody @Validated CoinTypeVO coinTypeVO) {
        MaxMinVo fee = new MaxMinVo();
        fee.setMax(tParamConfigService.getEnableValueByKey(coinTypeVO.getToken().toLowerCase()+"MaxFee"));
        fee.setMin(tParamConfigService.getEnableValueByKey(coinTypeVO.getToken().toLowerCase()+"MinFee"));
        return ReturnDTO.ok(fee);
    }

    @GetMapping("quotaMaxMin")
    @ApiOperation(value = "最大最小提币数量", notes = "最大最小提币数量")
    public ReturnDTO quotaMaxMin(@RequestBody @Validated CoinTypeVO coinTypeVO) {
        MaxMinVo quota = new MaxMinVo();
        quota.setMax(tParamConfigService.getEnableValueByKey(coinTypeVO.getToken().toLowerCase()+"Max"));
        quota.setMin(tParamConfigService.getEnableValueByKey(coinTypeVO.getToken().toLowerCase()+"Min"));
        return ReturnDTO.ok(quota);
    }

    @GetMapping("jugmentQuota")
    @ApiOperation(value = "额度判断", notes = "额度判断")
    public ReturnDTO jugmentQuota(@RequestBody @Validated CoinTypeVO coinTypeVO, @ALoginUser LoginUser user) {
        Long userId = 10086L;//user == null ? null : user.getUser_id();
        if (userId == null) {
            return ReturnDTO.error("用户未登录");
        }
        return caseService.checkparam(coinTypeVO,userId);
    }
}
