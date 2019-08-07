package com.bittrade.common.enums;


public enum WalletRecordTypeEnumer {
    BIBI_TO_PERSONAL((byte)1,"币币账户划转到法币账户"),
    PERSONAL_TO_BIBI((byte)2, "法币账户划转到币币账户"),
    BUYING_AND_SELLING((byte)3, "币币交易")
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
