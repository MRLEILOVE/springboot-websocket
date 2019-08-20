package com.walletbiz.enumer;

public enum BetaAccountEnum {
    PERSONAL_TO_BETA(1, "划转：转法币账户-->beta账户"),
    BETA_TO_PERSONAL( 2, "划转：beta账户-->法币账户"),
    BETA_TO_FUNDS(3,"划转：beta账户-->资金账户"),
    FUNDS_TO_BETA(4,"划转：资金账户-->beta账户")
    ;

    private Integer code;
    private String name;

    BetaAccountEnum(Integer code, String name) {
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
