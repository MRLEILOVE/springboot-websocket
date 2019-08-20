package com.wallet.biz.enumer;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "资金转换类型")
public enum TransferTypeEnum {
    FUND_TO_PERSONAL(1,  "资金账户划转法币账户"),
    FUND_TO_BETA(    2,  "资金账户划转beta账户"),
    FUND_TO_BIBI(    3,  "资金账户划转币币账户"),
    PERSONAL_TO_FUND(4,  "法币账户划转资金账户"),
    PERSONAL_TO_BETA(5,  "法币账户划转beta账户"),
    PERSONAL_TO_BIBI(6,  "法币账户划转币币账户"),
    BETA_TO_FUND(    7,  "beta账户划转资金账户"),
    BETA_TO_PERSONAL(8,  "beta账户划转法币账户"),
    BETA_TO_BIBI(    9,  "beta账户划转币币账户"),
    BIBI_TO_PERSONAL(10, "币币账户划转法币账户"),
    BIBI_TO_BETA(    11, "币币账户划转beta账户"),
    BIBI_TO_FUND(    12, "币币账户划转资金账户")
    ;

    private Integer type;
    private String name;

    TransferTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    //根据key获取name的值
    public static String getValueByKey(int code){
        for (TransferTypeEnum s : TransferTypeEnum.values()) {
            if(s.getType()== code){
                return s.getName();
            }
        }
        return null;
    }

    //根据匹配value的值获取key
    public static Integer getKeyByValue(String name){
        for (TransferTypeEnum s : TransferTypeEnum.values()) {
            if(name.equals(s.getName())){
                return s.getType();
            }
        }
        return null;
    }

}
