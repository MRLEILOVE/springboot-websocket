package com.bittrade.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryWalletVO {
    /**
     * 钱包id
     */
    private long id;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 虚拟货币name
     */
    private String currencyName;

    /**
     * 总数量
     */
    private Double total;

    /**
     * 交易冻结数量
     */
    private Double tradeFrozen;

    /**
     * 划转冻结数量
     */
    private Double transferFrozen;
}
