package com.walletbiz.enumer;

public enum AccountRecordEnum {

    RECHARGE(1, "充值"),
    WITHDRAW( 2, "提现"),
    FUNDS_TO_LEGAL(3,"划转：资金账户-->法币钱包"),
    LEGAL_TO_FUNDS(4,"划转：法币钱包-->资金账户"),
    FUNDS_TO_BETA(5,"划转：资金账户-->beta账户"),
    BETA_TO_FUNDS(6,"划转：beta账户-->资金账户"),
    FUNDS_TO_BIBI(7,"划转：资金账户-->币币账户"),
    BIBI_TO_FUNDS(8,"划转：币币账户-->资金账户")
    ;

    private Integer code;
    private String name;

    AccountRecordEnum(Integer code, String name) {
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
