package com.walletbiz.enumer;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "法币交易对类型")
public enum LegalCurrencyEnum {
    USDT(0, "USDT"),
    BITT( 1, "BITT"),
    BTC( 11, "BTC"),
    ;

    private Integer code;
    private String name;

    LegalCurrencyEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    //根据key获取name的值
    public static String getValueByKey(int code){
        for (LegalCurrencyEnum s : LegalCurrencyEnum.values()) {
            if(s.getCode()== code){
                return s.getName();
            }
        }
        return null;
    }

    //根据匹配value的值获取key
    public static Integer getKeyByValue(String name){
        for (LegalCurrencyEnum s : LegalCurrencyEnum.values()) {
            if(name.equals(s.getName())){
                return s.getCode();
            }
        }
        return null;
    }
}
