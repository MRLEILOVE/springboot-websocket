package com.bittrade.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserWalletVO {
    /**
     * 法币数量
     */
    private Double legalCurrencyCount;

    /**
     * 货币数量
     */
    private Double currencyCount;

}
