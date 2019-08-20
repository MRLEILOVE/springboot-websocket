package com.wallet.constant;

public class TradeStepConstant {
    public final static String PENDING = "10";//待后台审核[商户审核通过]
    public final static String AUDIT_PASS = "20";//审核通过可以交易
    public final static String PRE_BROADCAST = "25";//将要广播交易
    public final static String BROADCAST = "30";//已广播
    public final static String PACKAGED = "40";//已打包
    public final static String CONFIRM = "50";//确认完成
}


