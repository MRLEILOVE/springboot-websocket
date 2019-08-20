package com.wallet.biz.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundVO {
    /**
     * id
     */
    private long id;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 虚拟货币name
     */
    private String currency;

    /**
     * 总数量
     */
    private BigDecimal total;

    /**
     * 划转冻结数量
     */
    private BigDecimal transferFrozen;
}
