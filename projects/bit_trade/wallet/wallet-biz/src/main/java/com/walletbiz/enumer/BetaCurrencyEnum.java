package com.walletbiz.enumer;


/**
 * beta币种类型
 */
public enum BetaCurrencyEnum {
    //0=USDT,1=BITT，2=时间链，3=β通证，4=推广收益,5累计收益,6团队收益,11=BTC
    USDT(0, "USDT"),
    BITT( 1, "BITT"),
    BTC(11,"BTC")
    ;

    private Integer code;
    private String name;

    BetaCurrencyEnum(Integer code, String name) {
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
        for (BetaCurrencyEnum s : BetaCurrencyEnum.values()) {
            if(s.getCode()== code){
                return s.getName();
            }
        }
        return null;
    }

    //根据匹配value的值获取key
    public static Integer getKeyByValue(String name){
        for (BetaCurrencyEnum s : BetaCurrencyEnum.values()) {
            if(name.equals(s.getName())){
                return s.getCode();
            }
        }
        return null;
    }
}
