package com.bittrade.admin.model.vo;

import java.math.BigDecimal;

import com.bittrade.admin.model.domain.ComboGroup;

import lombok.Data;

/**
 * 点击次数VO
 */
@Data
public class ComboCountVo extends ComboGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 该套餐抢过的人数
     */
    private Integer userSum;
    /**
     * 该套餐剩余待转让狗数量
     */
    private Integer betaSum;
    /**
     * 出售价格
     */
    private BigDecimal sellPrice;

    /**
     * 价格基数 =（当前套餐最大值 - 最小值 * 拆分单数）
     */
    private BigDecimal priceBase;
    /**
     * 价格权重 =（当前价格基数 / 价格基数总和）保留十位小数
     */
    private BigDecimal priceWeight;

    /**
     * 饱和度 = 剩余狗数 / 抢狗人数
     * 风险系数 = 饱和度 / 饱和度上限
     * 饱和风险 风险系数 >= 0.95 就是警告 风险系数 < 0.95 就是正常
     * 0 = 正常 1 = 警告
     */
    private Integer saturatedRisk = 0;

    /**
     * 价格范围 = 最小价格 * 拆分单数 < 当前拆分金额 < 最高价格 * 拆分单数
     * 当前拆分金额满足则是正常，不满足则是超限
     * 0 = 正常 1 = 超限
     */
    private Integer priceRisk = 0;

    /**
     * 当前套餐拆分价格总额 = 拆分价值 * 价格权重
     */
    private BigDecimal splitPrice;

    /**
     *  最低价格（元）
     */
    private BigDecimal minPrice2;
    /**
     * 最高价格（元）
     */
    private BigDecimal maxPrice2;

}
