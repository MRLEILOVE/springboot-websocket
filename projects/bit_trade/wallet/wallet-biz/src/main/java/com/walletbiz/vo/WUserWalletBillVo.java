package com.walletbiz.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class WUserWalletBillVo {
    private String token;
    private BigDecimal amount;
    private String address;
    private LocalDateTime createTime;
}
