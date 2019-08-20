package com.bittrade.common.enums;

/**
 * c2c钱包流水状态
 */
public enum LegalRecordStatusEnumer {
    BUY(1, "买入"),
    SELL(2, "卖出"),
    LEGAL_TO_BIBI(3,"法币钱包划转币币钱包"),
    LEGAL_TO_FUNDS(4,"法币钱包划转资金钱包"),
    FUNDS_TO_LEGAL(5,"资金钱包划转法币钱包"),
    BIBI_TO_LEGAL(6,"币币钱包划转法币钱包")
    ;

    private int		code;
    private String	name;

    LegalRecordStatusEnumer(int code, String name) {
        this.code = code;
        this.name = name;
    }
    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
