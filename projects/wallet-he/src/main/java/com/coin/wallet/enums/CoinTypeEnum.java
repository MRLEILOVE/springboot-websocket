package com.coin.wallet.enums;

public enum CoinTypeEnum {
    BTC("BTC", "比特币"),
    BTC_TOKEN("BTC_TOKEN", "比特币代币");

    private String coinType;
    private String desc;

    CoinTypeEnum(String coinType, String desc) {
        this.coinType = coinType;
        this.desc = desc;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
