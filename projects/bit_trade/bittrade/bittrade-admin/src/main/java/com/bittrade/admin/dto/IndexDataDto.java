package com.bittrade.admin.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * <p>
 * 首页
 * <p>
 *
 * @author yongheng
 * @since 2018/12/19
 */
@Data
public class IndexDataDto implements Serializable {

    private BigDecimal totalUserRechargeCNY = BigDecimal.ZERO;    // 用户总充值(CNY)
    private BigDecimal totalUserRechargeUSDT = BigDecimal.ZERO;    // 用户总充值(USDT)
    private BigDecimal totalUserWithdrawCNY = BigDecimal.ZERO;    // 用户总提现(CNY)
    private BigDecimal totalUserWithdrawUSDT = BigDecimal.ZERO;    // 用户总提现(USDT)

    private Integer registerCount = 0; // 总注册数
    private Integer yesterdayRegisterCount = 0; // 昨天注册数量
    private Integer todayRegisterCount = 0; // 今天注册数量

    private Integer auditingRechargeCount = 0; // 充值待审核数量
    private Integer auditingWithdrawCount = 0; // 提现待审核数量
    private Integer auditingIdentityCount = 0; // 身份认证待审核数量

    /**
     * 持有条数
     */
    private Integer maxcount;
    /**
     * 总价值
     */
    private BigDecimal countCost;
    /**
     * 预期收益价值
     */
    private BigDecimal countProfit;


    /**
     * 当日抢狗人数(抢到狗)
     */
    private Integer buyUserSum;
    /**
     * 当日卖狗人数(卖出狗)
     */
    private Integer sellUserSum;
    /**
     * 当日成交量
     */
    private BigDecimal buyPeiceSum;
    /**
     * 当日激活人数
     */
    private Integer dayAuthSum;
    /**
     * 总激活人数
     */
    private Integer authSum;
    /**
     * 当日首次抢狗人数(注册至今首次)
     */
    private Integer dayFirstBuy;

    /**
     * 推荐收益需校正的人数
     */
    private Integer recommendEarningsSum;
}