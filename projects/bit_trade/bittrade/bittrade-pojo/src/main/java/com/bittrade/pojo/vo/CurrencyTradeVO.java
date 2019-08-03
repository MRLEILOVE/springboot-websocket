package com.bittrade.pojo.vo;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CurrencyTradeVO {
    /**
     * 住建id
     */
    private Integer id;

    /**
     * 交易对,如BTC/USDT
     */
    private String symbol;

    /**
     * 货币id，t_currency表中的id
     */
    private Integer currencyId1;

    /**
     * 货币name
     */
    private String currencyName;

    /**
     * 法币id，t_currency表中的id
     */
    private Integer currencyId2;

    /**
     * 法币name
     */
    private String legalCurrencyName;

    /**
     * 单价小数位
     */
    private Integer priceDecimalDigits;

    /**
     * 数量小数位
     */
    private Integer countDecimalDigits;

    /**
     * 最小挂单单价
     */
    private java.math.BigDecimal minPrice;

    /**
     * 最小挂单数量
     */
    private java.math.BigDecimal minCount;

    /**
     * 最小挂单金额
     */
    private java.math.BigDecimal minAmount;

    /**
     * 最大可买单价
     */
    private java.math.BigDecimal maxPrice;

    /**
     * 最大可买数量
     */
    private java.math.BigDecimal maxCount;

    /**
     * 最大可买金额
     */
    private java.math.BigDecimal maxAmount;
}
