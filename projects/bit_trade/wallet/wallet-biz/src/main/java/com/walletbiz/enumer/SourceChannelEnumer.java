package com.walletbiz.enumer;

/**
 * 来源渠道
 */
public enum SourceChannelEnumer {
    APP(       (byte)1, "app"),
    Backstage( (byte)2, "后台"),
    PC(        (byte)3, "PC"),
    ;

    private byte code;
    private String name;

    SourceChannelEnumer(byte code, String name) {
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
