package com.walletbiz.scheduled;
/*
package com.jdcloud.provider.scheduled;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jdcloud.provider.enumer.CoinType;
import com.jdcloud.provider.pojo.*;
import com.jdcloud.provider.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


*/
/**
 * 资金划转定时器
 *//*

@Component
public class FundAccountScheduled {
    private static final Logger LOG					= LoggerFactory.getLogger( FundAccountScheduled.class );

    @Autowired
    private IWWithdrawWalletBillService withdrawWalletBillService;
    @Autowired
    private ITwalletFundAccountService walletFundAccountService;
    @Autowired
    private ITwalletFundAccountRecordService walletFundAccountRecordService;
    @Autowired
    private IWUserWalletBillService userWalletBillService;
    @Autowired
    private ITOrderService orderService;

    @Scheduled(cron = "0/10 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void fundAccount() {
        try {
            myaccount();
        } catch (Exception e) {
            LOG.error("资金划转定时器错误：" + e.getMessage(),e);
            throw e;
        }
    }


    public void myaccount(){
        TWalletFundAccount bossBtc = walletFundAccountService.selectById(1);  //btc
        TWalletFundAccount bossUsdt= walletFundAccountService.selectById(2); //usdt

        BigDecimal bossBtcTotal = bossBtc.getTotal();
        BigDecimal bossUsdtTotal= bossUsdt.getTotal();


        //更新充值记录表以及加入boss钱包的金额
        EntityWrapper<WUserWalletBill>entityWrapper1=new EntityWrapper<>();
        entityWrapper1.eq("direction","1").eq("flag","1").eq("transfer_flag",2).eq("trade_step",50);
        List<WUserWalletBill> rechargeList = userWalletBillService.selectList(entityWrapper1);
        List<TWalletFundAccountRecord>list1=new ArrayList<>();

        //更新充值用户资金的total插入流水
      // List<TWalletFundAccount>list2=new ArrayList<>();
        List<TWalletFundAccount>list=new ArrayList<>();
        for (WUserWalletBill wUserWalletBill:rechargeList){
            EntityWrapper<TWalletFundAccount>entityWrapper=new EntityWrapper<>();
            entityWrapper.eq("user_id",wUserWalletBill.getUserId()).eq("currency",wUserWalletBill.getToken());
            TWalletFundAccount walletFundAccount = walletFundAccountService.selectOne(entityWrapper);
            if(null!=walletFundAccount) {
                TWalletFundAccountRecord tWalletFundAccountRecord = new TWalletFundAccountRecord();
                BigDecimal totals = walletFundAccount.getTotal().add(walletFundAccount.getTransferFrozen());
                tWalletFundAccountRecord.setBeforeAmount(totals);
                totals = totals.add(wUserWalletBill.getAmount());
                walletFundAccount.setTotal(walletFundAccount.getTotal().add(wUserWalletBill.getAmount()));
                tWalletFundAccountRecord.setAfterAmount(totals);
                tWalletFundAccountRecord.setChangeAmount(tWalletFundAccountRecord.getAfterAmount().subtract(tWalletFundAccountRecord.getBeforeAmount()));
                tWalletFundAccountRecord.setUserId(walletFundAccount.getUserId());
                tWalletFundAccountRecord.setCurrency(walletFundAccount.getCurrency());
                tWalletFundAccountRecord.setType(1);
                tWalletFundAccountRecord.setOrderId(wUserWalletBill.getOrderId());
                System.out.println(tWalletFundAccountRecord);
                list1.add(tWalletFundAccountRecord);
               // walletFundAccount.setVersion(walletFundAccount.getVersion());
                list.add(walletFundAccount);
            }

        }
      */
/*  if (list2.size()>0){
            walletFundAccountService.updateBatchById(list2);
        }*//*



        //插入boss钱包的资金充值流水和更新boss总额
        for (WUserWalletBill wUserWalletBill:rechargeList){

            wUserWalletBill.setFlag(2);

            TWalletFundAccountRecord rechargeRecord=new TWalletFundAccountRecord();
            rechargeRecord.setType(1);
            rechargeRecord.setCurrency(wUserWalletBill.getToken());
            rechargeRecord.setOrderId(wUserWalletBill.getOrderId());

            if(CoinType.BTC.toString().equals(wUserWalletBill.getToken())) {
                rechargeRecord.setUserId(1L);
                rechargeRecord.setBeforeAmount(bossBtcTotal);
                bossBtcTotal=bossBtcTotal.add(wUserWalletBill.getAmount());
                rechargeRecord.setAfterAmount(bossBtcTotal);
                rechargeRecord.setChangeAmount(rechargeRecord.getAfterAmount().subtract(rechargeRecord.getBeforeAmount()));

            }else if(CoinType.USDT.toString().equals(wUserWalletBill.getToken())){
                rechargeRecord.setUserId(2L);
                rechargeRecord.setBeforeAmount(bossUsdtTotal);
                bossUsdtTotal=bossUsdtTotal.add(wUserWalletBill.getAmount());
                rechargeRecord.setAfterAmount(bossUsdtTotal);
                rechargeRecord.setChangeAmount(rechargeRecord.getAfterAmount().subtract(rechargeRecord.getBeforeAmount()));
            }
            list1.add(rechargeRecord);
        }



        //更新提现记录表，以及减去boss钱包的金额
        // select *  from w_withdraw_wallet_bill where direction=-1 and flag=1 ;
        //更新提现用户资金的total插入流水
        EntityWrapper<WWithdrawWalletBill>entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("direction","-1").eq("flag","1").eq("trade_step",50);
        List<WWithdrawWalletBill>withList=withdrawWalletBillService.selectList(entityWrapper);
        List<String>list3=new ArrayList<>();
        for (WWithdrawWalletBill withdrawWalletBill:withList){
            list3.add(withdrawWalletBill.getOrderId());
            EntityWrapper<TWalletFundAccount>entityWrapper2=new EntityWrapper<>();
            entityWrapper2.eq("user_id",withdrawWalletBill.getUserId()).eq("currency",withdrawWalletBill.getToken());
            TWalletFundAccount walletFundAccount = walletFundAccountService.selectOne(entityWrapper2);
            if(null!=walletFundAccount){
                TWalletFundAccountRecord withRecord=new TWalletFundAccountRecord();
                withRecord.setOrderId(withdrawWalletBill.getOrderId());
                withRecord.setCurrency(walletFundAccount.getCurrency());
                withRecord.setType(2);
                withRecord.setUserId(walletFundAccount.getUserId());
                BigDecimal totals=walletFundAccount.getTransferFrozen().add(walletFundAccount.getTotal());
                withRecord.setBeforeAmount(totals);
             //   walletFundAccount.setVersion(walletFundAccount.getVersion());
                EntityWrapper<TOrder>entityWrapper3=new EntityWrapper<>();
                entityWrapper3.eq("order_id",withRecord.getOrderId());
                TOrder order = orderService.selectOne(entityWrapper3);
                walletFundAccount.setTransferFrozen(walletFundAccount.getTransferFrozen().subtract(withdrawWalletBill.getAmount()).subtract(order.getFee()));
                withRecord.setAfterAmount(walletFundAccount.getTransferFrozen().add(walletFundAccount.getTotal()));
                withRecord.setChangeAmount(withRecord.getAfterAmount().subtract(withRecord.getBeforeAmount()));
                list1.add(withRecord);
                list.add(walletFundAccount);
            }
        }
        //更新boss提现记录表的total，以及减去boss钱包的金额
        for (WWithdrawWalletBill withdrawWalletBill:withList){
            withdrawWalletBill.setFlag(2);
            TWalletFundAccountRecord withRecord=new TWalletFundAccountRecord();
            withRecord.setType(2);
            withRecord.setCurrency(withdrawWalletBill.getToken());
            withRecord.setOrderId(withdrawWalletBill.getOrderId());
            //加入订单表id

            if(CoinType.BTC.toString().equals(withdrawWalletBill.getToken())) {
                withRecord.setUserId(1L);
                withRecord.setBeforeAmount(bossBtcTotal);
                bossBtcTotal=bossBtcTotal.subtract(withdrawWalletBill.getAmount());
                withRecord.setAfterAmount(bossBtcTotal);
                withRecord.setChangeAmount(withRecord.getAfterAmount().subtract(withRecord.getBeforeAmount()));

            }else if(CoinType.USDT.toString().equals(withdrawWalletBill.getToken())){
                withRecord.setUserId(2L);
                withRecord.setBeforeAmount(bossUsdtTotal);
                bossUsdtTotal=bossUsdtTotal.subtract(withdrawWalletBill.getAmount());
                withRecord.setAfterAmount(bossUsdtTotal);
                withRecord.setChangeAmount(withRecord.getAfterAmount().subtract(withRecord.getBeforeAmount()));
            }
            list1.add(withRecord);
        }
        EntityWrapper<TOrder>entityWrapper2=new EntityWrapper<>();
        entityWrapper2.in("order_id",list3).eq("type",3);
        List<TOrder> tOrders = orderService.selectList(entityWrapper2);
        for (TOrder tOrder:tOrders){
            tOrder.setType(5);
        }


        bossBtc.setTotal(bossBtcTotal);
        bossUsdt.setTotal(bossUsdtTotal);
        list.add(bossBtc);
        list.add(bossUsdt);
        if (tOrders.size()>0){
            orderService.updateBatchById(tOrders);
        }
        if (rechargeList.size()>0){
            userWalletBillService.updateBatchById(rechargeList);

        }
        if (withList.size()>0){
            withdrawWalletBillService.updateBatchById(withList);
        }
        if (list1.size()>0){
            walletFundAccountRecordService.insertBatch(list1);
        }
        if (list.size()>0){
            walletFundAccountService.updateBatchById(list);
        }

    }
}
*/
