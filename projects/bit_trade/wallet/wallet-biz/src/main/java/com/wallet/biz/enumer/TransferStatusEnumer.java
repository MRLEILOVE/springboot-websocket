package com.wallet.biz.enumer;


/**
 * 划转记录状态
 */
public enum TransferStatusEnumer {
    PENDING((byte)1,"待处理"),
    SUCCESS((byte)2, "成功"),
    FAIL(   (byte)3,    "失败"),
    UNKNOW( (byte)4,  "状态未明")
    ;

    private byte code;
    private String name;

    TransferStatusEnumer(byte code, String name) {
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
