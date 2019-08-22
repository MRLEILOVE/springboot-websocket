package com.wallet.biz.controller;

import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.web.constant.entity.LoginUser;
import com.wallet.biz.api.service.IWCoinConfigService;
import com.wallet.biz.pojo.vo.WithdrawBillParamVo;
import com.wallet.biz.api.service.IwalletCaseService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ReturnDTO confirmTibi(@RequestBody @Validated WithdrawBillParamVo withdrawBillParamVo,@ALoginUser LoginUser user) {
        //用户判断
        Long userId = user == null ? null : user.getUser_id();
        if(userId == null){
            return ReturnDTO.error("用户未登录");
        }
        if(user.checkPayPassWord(withdrawBillParamVo.getPassword())){
            return ReturnDTO.error("密码错误");
        }
        return caseService.confirmTibi(withdrawBillParamVo,userId);
        }


    }
/*
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

    @PostMapping("tibiMaxMin")
    @ApiOperation(value = "最大最小提币数量", notes = "最大最小提币数量")
    public ReturnDTO maxMinTibi() {
        return caseService.showmaxMin();
    }

    @PostMapping("jugment")
    @ApiOperation(value = "额度判断", notes = "额度判断")
    public ReturnDTO judgment(/*@RequestBody @Validated JudgmentDto withDrawParamVo*//*) {
        return caseService.checkparam(/*withDrawParamVo*//*);
    }*/
//}
