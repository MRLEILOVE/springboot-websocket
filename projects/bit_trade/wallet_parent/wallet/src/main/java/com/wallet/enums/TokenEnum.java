package com.wallet.enums;

public enum TokenEnum {
    BTC("BTC", "BTC"),

    BTC_TOKEN("BTC_TOKEN", "BTC_TOKEN"),
    USDT("BTC_TOKEN", "USDT");

    private String coinType;
    private String token;

    TokenEnum(String coinType, String token) {
        this.coinType = coinType;
        this.token = token;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
