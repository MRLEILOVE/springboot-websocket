package com.bittrade.common.enums;


public enum WalletRecordTypeEnumer {
    BIBI_TO_PERSONAL(   (byte)1,"币币账户划转到法币账户"),
    PERSONAL_TO_BIBI(   (byte)2, "法币账户划转到币币账户"),
    BUYING(             (byte)3, "买入"),
    SELLING(            (byte)4, "卖出"),
    BIBI_TO_FUNDS(      (byte)5,"币币钱包划转资金钱包"),
    FUNDS_TO_BIBI(      (byte)6,"资金钱包划转币币钱包")
    ;

    private byte code;
    private String name;

    WalletRecordTypeEnumer(byte code, String name) {
        this.code = code;
        this.name = name;
    }
    public Byte getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
