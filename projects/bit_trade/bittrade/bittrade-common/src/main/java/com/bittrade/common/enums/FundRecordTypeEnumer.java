package com.bittrade.common.enums;

/**
 * 资金账户流水类型
 */
public enum FundRecordTypeEnumer {
    recharge(1, "充币"),
    withdraw(2, "提币"),
    BIBI_TO_FUNDS(3,"币币钱包划转资金钱包"),
    FUNDS_TO_BIBI(4,"资金钱包划转币币钱包"),
    FUNDS_TO_C2C(5,"资金钱包划转c2c钱包"),
    C2C_TO_FUNDS(6,"c2c钱包划转资金钱包")
    ;

    private int		code;
    private String	name;

    FundRecordTypeEnumer(int code, String name) {
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
