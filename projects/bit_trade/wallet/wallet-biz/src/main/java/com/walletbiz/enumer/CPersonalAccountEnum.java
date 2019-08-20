package com.walletbiz.enumer;

/**
 * 法币账户流水类型
 */
public enum CPersonalAccountEnum {
    PERSONAL_TO_BETA(1, "法币账户-->beta账户"),
    BETA_TO_PERSONAL( 2, "beta账户-->法币账户"),
    PERSONAL_TO_FUNDS(3,"法币账户-->资金账户"),
    FUNDS_TO_PERSONAL(4,"资金账户-->法币账户"),
    PERSONAL_TO_BIBI(5,"法币账户-->币币账户"),
    BIBI_TO_PERSONAL(6,"币币账户-->法币账户")
    ;

    private Integer code;
    private String name;

    CPersonalAccountEnum(Integer code, String name) {
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
