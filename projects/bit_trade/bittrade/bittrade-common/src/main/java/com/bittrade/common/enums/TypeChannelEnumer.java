package com.bittrade.common.enums;

/**
 * 操作渠道
 */
public enum TypeChannelEnumer {
    C_TO_B((byte)1,"C2C钱包划转到币币钱包"),
    B_TO_C((byte)2, "币币钱包划转到C2C钱包"),
    ;

    private byte code;
    private String name;

    TypeChannelEnumer(byte code, String name) {
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
