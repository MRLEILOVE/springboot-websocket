package com.wallet.biz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.api.service.IWCoinConfigService;
import com.wallet.biz.pojo.model.WCoinConfig;
import com.wallet.biz.pojo.vo.WithdrawBillParamVo;
import com.wallet.biz.service.IwalletCaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wallet.biz.api.service.IWOrderService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    @PostMapping("confirmTibi")
    @ApiOperation(value = "确定提币", notes = "确定提币")
    public ReturnDTO confirmTibi(@RequestBody @Validated WithdrawBillParamVo withdrawBillParamVo) {
        //判断该币种是否可以提币
        WCoinConfig coinConfig = WCoinConfigService.getOne(new QueryWrapper<>(WCoinConfig.builder()
                .coinType(withdrawBillParamVo.getCoinType())
                .token(withdrawBillParamVo.getToken())
                .valid("E")
                .build()), true);
        if(null == coinConfig){
            return ReturnDTO.error("该币种暂时不支持提币");
        }

        return caseService.confirmTibi(withdrawBillParamVo);
    }
/*
    @PostMapping("chongbi")
    @ApiOperation(value = "充币")
    public String chongbi() throws  FlowException {
        Long userId = RequestUtil.getCurrentUser().getUser_id();
        if (userId == null) {
            throw new FlowException("用户未登录");
        }
        CreateAddressParamDto paramDto = new CreateAddressParamDto();
        paramDto.setUserId(userId);
        String s = createAddressFactory.create(paramDto);
        System.out.println(s);
        if (s.contains("BTC_TOKEN")) {
            s = s.replaceAll("BTC_TOKEN", "USDT");
        }
        return s;
    }
    */
    @PostMapping("tibiFee")
    @ApiOperation(value = "提币费率", notes = "提币费率")
    public ReturnDTO tibiFee() {
        return caseService.showfee();
    }
/*
    @PostMapping("qrCode")
    @ApiOperation(value = "二维码", notes = "二维码")
    public Wrapper qrCode(@RequestBody @Validated CoinTypeDto coinTypeDto) throws FlowException, FileNotFoundException {
        Long userId = RequestUtil.getCurrentUser().getUser_id();
        if (userId == null) {
            throw new FlowException("用户未登录");
        }
        EntityWrapper<WUserWallet>entityWrapper=new EntityWrapper<>();
        if(CoinType.USDT.toString().equals(coinTypeDto.getToken())){
            coinTypeDto.setToken("BTC_TOKEN");
        }
        entityWrapper.eq("user_id",userId).eq("coin_type",coinTypeDto.getToken());
        WUserWallet wUserWallet1 = wUserWalletService.selectOne(entityWrapper);
        if (null==wUserWallet1){
            return WrapMapper.error("该用户还没有钱包地址");
        }
        if (null!=coinTypeDto.getUrl()&&!"".equals(coinTypeDto.getUrl())){
            wUserWallet1.setCodeQr(coinTypeDto.getUrl());
            boolean b = wUserWalletService.updateById(wUserWallet1);
            if (b){
                return WrapMapper.ok("success");
            }
            return WrapMapper.error("error");
        }
        if (null==wUserWallet1.getCodeQr()||"".equals(wUserWallet1.getCodeQr())){
            return WrapMapper.error("该用户还没有二维码");
        }
        return WrapMapper.ok(wUserWallet1.getCodeQr());
    }


    @PostMapping("record")
    @ApiOperation(value = "充提币记录", notes = "充提币记录")
    public Wrapper rechargeRecord(@RequestBody WalletDto walletDto) throws FlowException {
        Long user_id = RequestUtil.getCurrentUser().getUser_id();
        if (user_id == null) {
            throw new FlowException("用户未登录");
        }

        EntityWrapper<WUserWalletBill> chong = new EntityWrapper<>();
        chong.eq("user_id", user_id).eq("flag",2).orderBy("create_time", false);
        EntityWrapper<WWithdrawWalletBill> ti = new EntityWrapper<>();
        ti.eq("user_id", user_id).eq("flag",2).orderBy("create_time", false);
        if (null != walletDto.getToken() && !"".equals(walletDto.getToken())) {
            ti.eq("token", walletDto.getToken());
            chong.eq("token", walletDto.getToken());
        }
        List<CoinRecordVo> voList = new ArrayList<>();
        long pages = 0L;//page1.getPages() + page2.getPages();
        long totals = 0L;//page1.getTotal() + page2.getTotal();

        if ("recharge".equals(walletDto.getType())) {
            Page<WUserWalletBill> chongPage = new Page<>(walletDto.getCurrent(), walletDto.getSize() / 2);
            Page<WUserWalletBill> page1 = userWalletBillService.selectPage(chongPage, chong);
            List<WUserWalletBill> chongList = page1.getRecords();
            for (WUserWalletBill userWalletBill : chongList) {
                CoinRecordVo coinRecordVo = new CoinRecordVo();
                coinRecordVo.setToken(userWalletBill.getToken());
                coinRecordVo.setAmount(userWalletBill.getAmount().toPlainString());
                coinRecordVo.setReceiverAddress(userWalletBill.getReceiverAddress());
                coinRecordVo.setCreateTime(userWalletBill.getCreateTime());
                coinRecordVo.setDirection(userWalletBill.getDirection());
                voList.add(coinRecordVo);
            }
            pages += page1.getPages();
            totals += page1.getTotal();
        }

        if ("withdraw".equals(walletDto.getType())) {
            Page<WWithdrawWalletBill> tiPage = new Page<>(walletDto.getCurrent(), walletDto.getSize() / 2);
            Page<WWithdrawWalletBill> page2 = withdrawWalletBillService.selectPage(tiPage, ti);
            List<WWithdrawWalletBill> tiList = page2.getRecords();
            for (WWithdrawWalletBill wWithdrawWalletBill : tiList) {
                CoinRecordVo coinRecordVo = new CoinRecordVo();
                coinRecordVo.setToken(wWithdrawWalletBill.getToken());
                coinRecordVo.setAmount(wWithdrawWalletBill.getAmount().toPlainString());
                coinRecordVo.setReceiverAddress(wWithdrawWalletBill.getReceiverAddress());
                coinRecordVo.setCreateTime(wWithdrawWalletBill.getCreateTime());
                coinRecordVo.setDirection(wWithdrawWalletBill.getDirection());
                voList.add(coinRecordVo);
            }
            pages += page2.getPages();
            totals += page2.getTotal();
        }

        Collections.sort(voList, new Comparator<CoinRecordVo>() {
            @Override
            public int compare(CoinRecordVo o1, CoinRecordVo o2) {
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }
        });

        PageVo<CoinRecordVo> page = new PageVo<>();
        page.setRecords(voList);
        page.setCurrent(walletDto.getCurrent());
        page.setSize(walletDto.getSize());
        page.setTotal(totals);
        page.setPages(pages);
        return WrapMapper.ok(page);
    }
*//*
    @PostMapping("list")
    @ApiOperation(value = "查询当前用户的币种余额", notes = "查询当前用户的币种余额")
    public Wrapper list(@RequestBody CoinTypeDto coinTypeDto) throws FlowException {
        Long user_id = RequestUtil.getCurrentUser().getUser_id();
        if (user_id == null) {
            throw new FlowException("用户未登录");
        }

        List<TWalletFundAccount> tWalletFundAccounts = iTwalletFundAccountService.list(user_id);
        return WrapMapper.ok(tWalletFundAccounts);
    }

    @PostMapping("conversionTotal")
    @ApiOperation(value = "总资金折合", notes = "总资金折合")
    public Wrapper totalConversion() throws FlowException {
        Long user_id = RequestUtil.getCurrentUser().getUser_id();
        if (user_id == null) {
            throw new FlowException("用户未登录");
        }
        ConversionVo conversionVo = iTwalletFundAccountService.totalConversion(user_id);
        return WrapMapper.ok(conversionVo);
    }

    @PostMapping("auditStatus")
    @ApiOperation(value = "审核记录", notes = "审核记录")
    public Wrapper auditStatus(@RequestBody PageDto pageDto) throws FlowException {
        Long user_id = RequestUtil.getCurrentUser().getUser_id();
        if (user_id == null) {
            throw new FlowException("用户未登录");
        }
        EntityWrapper<TOrder> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_id", user_id).orderBy("create_time", false);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(-1);
        entityWrapper.in("type", list);//审核通过的记录不显示，只显示审核中、被拒绝的记录
        Page<TOrder> page = new Page<>(pageDto.getCurrent(), pageDto.getSize());
        Page<TOrder> page1 = orderService.selectPage(page, entityWrapper);
        return WrapMapper.ok(page1);
    }
*/
    @PostMapping("tibiMaxMin")
    @ApiOperation(value = "最大最小提币数量", notes = "最大最小提币数量")
    public ReturnDTO maxMinTibi() {
        return caseService.showmaxMin();
    }

    @PostMapping("jugment")
    @ApiOperation(value = "额度判断", notes = "额度判断")
    public ReturnDTO judgment(/*@RequestBody @Validated JudgmentDto withDrawParamVo*/) {
        return caseService.checkparam(/*withDrawParamVo*/);
    }
}
