package com.wallet.biz.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

import com.wallet.biz.pojo.TWalletFundAccount;

@Data
public class AccountVO {
    /**
     * udst总数量
     */
    private BigDecimal total;

    /**
     * 折合人民币
     */
    private BigDecimal CNY;

    /**
     * 钱包列表
     */
    List<TWalletFundAccount> list;
}
