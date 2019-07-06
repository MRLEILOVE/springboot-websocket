package com.coin.wallet.enums;

public enum WalletTypeEnum {

    WITHDRAW("withdraw", "提现地址"),
    FEE("fee", "手续费地址");

    private String type;
    private String desc;

    WalletTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
