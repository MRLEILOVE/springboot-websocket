package com.bittrade.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 资金账户币种
 */
public enum FundCoinEnumer {

    USDT(0, "USDT"),
    BTC( 1, "BTC");

    private Integer type;
    private String name;

    FundCoinEnumer(Integer type, String name) {
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

    //获取所有value
    public static List<String> getValues(){
        List<String> values = new ArrayList<>();
        for(TransferTypeEnum s : TransferTypeEnum.values()){
            values.add(s.getName());
        }
        return values;
    }
}
