package com.jdcloud.provider.service.impl;

import com.jdcloud.provider.dto.*;
import com.jdcloud.provider.mapper.BetaOrderMapper;
import com.jdcloud.provider.service.*;
import com.jdcloud.util.date.DateTimeUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 统计实现类
 * <p>
 *
 * @author yongheng
 * @since 2018/12/19
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private RechargeOrderService rechargeOrderService;
    @Autowired
    private WithdrawOrderService withdrawOrderService;
    @Autowired
    private UserAccountService userAccountService;
    @Resource
    private BetaOrderMapper betaOrderMapper;

    @Autowired
    private BetaOrderService betaOrderService;

    /**
     * 充提统计<br>
     *
     * @return:
     * @Author: yongheng
     * @Date: 2018/12/19 10:50
     */
    @Override
    public StatisticsDto amountStatistics(Long userId) {
        StatisticsDto statistics = new StatisticsDto();
        // 充值统计
        StatisticsDto rechargeStatistics = rechargeOrderService.statisticsByUserId(userId);
        // 提现统计
        StatisticsDto withdrawStatistics = withdrawOrderService.statisticsByUserId(userId);

        // 数据处理
        statistics.setTotalUserRechargeCNY(rechargeStatistics.getTotalUserRechargeCNY());
        statistics.setTotalUserRechargeUSDT(rechargeStatistics.getTotalUserRechargeUSDT());
        statistics.setTotalManualRecharge(rechargeStatistics.getTotalManualRecharge());

        statistics.setTotalUserWithdrawCNY(withdrawStatistics.getTotalUserWithdrawCNY());
        statistics.setTotalUserWithdrawUSDT(withdrawStatistics.getTotalUserWithdrawUSDT());
        statistics.setTotalManualWithdraw(withdrawStatistics.getTotalManualWithdraw());
        return statistics;
    }

    /**
     * 首页数据<br>
     *
     * @return:
     * @Author: yongheng
     * @Date: 2018/12/19 15:23
     */
    @Override
    public IndexDataDto indexDataInit() {
        IndexDataDto indexData = new IndexDataDto();
        // 查询用户总充值
        RechargeStatistics rechargeStatistics = rechargeOrderService.rechargeStatistics();
        indexData.setTotalUserRechargeCNY(rechargeStatistics.getTotalUserRechargeCNY());
        indexData.setTotalUserRechargeUSDT(rechargeStatistics.getTotalUserRechargeUSDT());

        // 查询用户总提现
        WithdrawStatistics withdrawStatistics = withdrawOrderService.withdrawStatistics();
        indexData.setTotalUserWithdrawCNY(withdrawStatistics.getTotalUserWithdrawCNY());
        indexData.setTotalUserWithdrawUSDT(withdrawStatistics.getTotalUserWithdrawUSDT());

        // 查询充值待审核数
        indexData.setAuditingRechargeCount(rechargeOrderService.queryAuditingCount());
        // 查询提现待审核数
        indexData.setAuditingWithdrawCount(withdrawOrderService.queryAuditingCount());
        return indexData;
    }

    @Override
    public Wrapper<IndexDataDto> userMonitor() {
        // 用户监控数据
        IndexDataDto dataDto = userAccountService.userMonitor();
        return WrapMapper.ok(dataDto);
    }

    @Override
    public Wrapper<IndexDataDto> betaMonitor() {
        // 抢狗监控数据
        // 今天的开始时间
        Date beginTime = DateTimeUtils.getActinTime(new Date());
        // 今天的结束时间
        Date endTime = DateTimeUtils.getEndTime(new Date());
//        IndexDataDto dataDto = betaOrderMapper.betaMonitor(beginTime, endTime);
        IndexDataDto dataDto = betaOrderMapper.betaMonitorNew(beginTime, endTime);
        return WrapMapper.ok(dataDto);
    }

    @Override
    public Wrapper<IndexDataDto> cardMonitor() {
        // 身份认证待审核数
        IndexDataDto dataDto = userAccountService.userMonitor();
        return WrapMapper.ok(dataDto);
    }

    @Override
    public Wrapper<IndexDataDto> accountMonitor() {
        //统计用户持仓数据
        IndexDataDto dataDto = betaOrderMapper.selectCountUserBetaCount();
        return WrapMapper.ok(dataDto);
    }
}