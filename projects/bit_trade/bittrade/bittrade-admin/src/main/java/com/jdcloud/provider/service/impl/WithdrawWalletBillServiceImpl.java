package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AuditEnum;
import com.jdcloud.provider.dto.WithdrawWalletBillDto;
import com.jdcloud.provider.pojo.WalletFundAccountRecord;
import com.jdcloud.provider.pojo.WithdrawWalletBill;
import com.jdcloud.provider.mapper.WithdrawWalletBillMapper;
import com.jdcloud.provider.pojo.vo.MentionMoney;
import com.jdcloud.provider.pojo.vo.WithdrawWalletBillVo;
import com.jdcloud.provider.service.OrderService;
import com.jdcloud.provider.service.WalletFundAccountRecordService;
import com.jdcloud.provider.service.WithdrawWalletBillService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.util.date.DateTimeUtils;
import org.jsoup.helper.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 提币钱包账单 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-31
 */
@Service
public class WithdrawWalletBillServiceImpl extends ServiceImpl<WithdrawWalletBillMapper, WithdrawWalletBill> implements WithdrawWalletBillService {

    @Autowired
    private WalletFundAccountRecordService walletFundAccountRecordService;

    @Autowired
    private OrderService orderService;

    /**
     *
     * @param page
     * @param dto
     * @return
     */
    @Override
    public Page<WithdrawWalletBillVo> selectWithdrawWalletBill(Page<WithdrawWalletBillVo> page,WithdrawWalletBillDto dto){
        page.setRecords(baseMapper.selectWithdrawWalletBill(page,dto));
        return page;
    }

    /**
     * 平台提币详情
     * @param id
     * @return
     */
    @Override
    public WithdrawWalletBillVo selectWithdrawWalletBillDetail(Long id) {
        return baseMapper.selectWithdrawWalletBillDetail(id);
    }


    /**
     * 统计 提币数
     * @return
     */
    @Override
    public MentionMoney getMentionMoneyCount(){
        // 今天的开始时间
        Date startDate =  DateTimeUtils.getDays();
        // 获取今天的结束时间
        Date endDate =   DateTimeUtils.getEndTime(new Date());
        MentionMoney money = new MentionMoney ();
        BigDecimal BTCCount = orderService.getOrderCount(startDate,endDate, AuditEnum.orderType.SUCCEED.getCode(),"BTC");
        if(BTCCount==null){
            money.setTotalUserWithdrawBTC(new BigDecimal(0));
        }else {
            money.setTotalUserWithdrawBTC(BTCCount);
        }
        BigDecimal USDTCount = orderService.getOrderCount(startDate,endDate, AuditEnum.orderType.SUCCEED.getCode(),"USDT");
        if(USDTCount==null){
            money.setTotalUserWithdrawUSDT(new BigDecimal(0));
        }else {
            money.setTotalUserWithdrawUSDT(USDTCount);
        }
//        orderService.getOrderCoinCountSucceed


//        // 获取当月BTC  USDT
//        //
//        Date startDate =  DateTimeUtils.getMonthBegin();
//        // 获取今天的结束时间
//        Date endDate =   DateTimeUtils.getEndTime(new Date());
        return money;
    }



    /**
     * 统计 充币数
     * @return
     */
    @Override
    public MentionMoney getCoinCount(){
        // 今天的开始时间
        Date startDate =  DateTimeUtils.getDays();
        // 获取今天的结束时间
        Date endDate =   DateTimeUtils.getEndTime(new Date());
        MentionMoney money = new MentionMoney ();
        BigDecimal BTCCount = orderService.getOrderCoinCount(startDate,endDate, AuditEnum.orderType.SUCCEED.getCode(),"BTC");
        if(BTCCount==null){
            money.setTotalUserWithdrawBTC(new BigDecimal(0));
        }else {
            money.setTotalUserWithdrawBTC(BTCCount);
        }
        BigDecimal USDTCount = orderService.getOrderCoinCount(startDate,endDate, AuditEnum.orderType.SUCCEED.getCode(),"USDT");
        if(USDTCount==null){
            money.setTotalUserWithdrawUSDT(new BigDecimal(0));
        }else {
            money.setTotalUserWithdrawUSDT(USDTCount);
        }

        // 当月充币数
        Date monthStartDate =  DateTimeUtils.getDays();
        // 当月充币数


        return money;
    }



    /**
     * 平台获取提币钱包账单
     * @param page
     * @param dto
     * @return
     */
//    @Override
//    public Page<WithdrawWalletBillVo> selectWithdrawWalletBill(Page<WithdrawWalletBillVo> page, WithdrawWalletBillDto dto) {
//        List<WithdrawWalletBillVo>  list=  baseMapper.selectWithdrawWalletBill(page,dto);
////        List<WithdrawWalletBillVo> arrayList = new ArrayList<>();
////        for (WithdrawWalletBillVo vo :list){
////            EntityWrapper entity = new EntityWrapper();
////            entity.eq("order_id",vo.getOrderId());
////            entity.ge("user_id",100);
////            WalletFundAccountRecord record =  walletFundAccountRecordService.selectOne(entity);
////            arrayList.add()
////        }
//        page.setRecords(null);
//        return page;
//    }



}
