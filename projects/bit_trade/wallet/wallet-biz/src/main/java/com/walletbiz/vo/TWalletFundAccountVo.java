package com.walletbiz.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class TWalletFundAccountVo {
    private Integer id;

    private Long userId;

    private String currency;

    private BigDecimal total;

    private BigDecimal conversion;

    private BigDecimal transferFrozen;

    private Integer version;

    private Date createTime;

    private Date updateTime;
}
