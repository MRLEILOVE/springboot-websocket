package com.wallet.biz.enumer;

/**
 * 账户类型
 */
public enum AccountTypeEnum {
    FUND(       1, "资金账户"),
    PERSONERAL( 2, "法币账户"),
    BETA(       3, "beta账户"),
    BIBI(       4, "币币账户")
    ;

    private Integer code;
    private String name;

    AccountTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
