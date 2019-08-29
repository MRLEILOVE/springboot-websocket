package com.bittrade.uac.model.enums;

import lombok.Getter;

/**
 * @author: xzc
 * @create: 2019/8/27 下午6:04
 * @description: 消息模版
 **/
@Getter
public enum  MessageTemplateEnum {
    USER_REGISTER_TEMPLATE("REGISTER_TEMPLATE", "用户注册", "【Bitrade】尊敬的用户您好,您的验证码是:%s,验证码有效时间10分钟！"),
    CHANGE_PAY_PWD_TEMPLATE("USERSEND_TEMPLATE", "修改支付密码", "【Bitrade】尊敬的用户您好,您的验证码是:%s,验证码有效时间10分钟！"),
    USER_RECEIVING_TEMPLATE("RECEIVING_TEMPLATE", "修改收付款二维码", "【Bitrade】尊敬的用户您好,您的验证码是:%s,验证码有效时间10分钟！"),
    USER_BETA_PAY("USER_BETA_PAY", "beta狗出售", "【Bitrade】尊敬的用户您好,你有宠物订单被抢购，请尽快登录系统进行处理，否则2小时后将自动交易！"),
    USER_BETA_TRANSFER("USER_BETA_TRANSFER", "beta狗领养", "【Bitrade】尊敬的用户您好,你订单状态有变化，请尽快登录系统进行处理，否则将自动转入非正常账户！"),
    QUOTATION_HEALTH_TEMPLATE("Quotation_Health_TEMPLATE", "行情检查", "【Bitrade】您好,行情出现波动，现已无法正常连接！"),

    SELL_SELL_CURRENCY_SENDBUY_TEMPLATE("SELL_SELL_CURRENCY_SENDBUY_TEMPLATE", "卖方出售货币通知买家付款",
                                                "【Bitrade】#orderNumber对方向您出售，%s个%s，单价:%s元，总价为:%s元，请您及时打款。"),

    BUY_CURRENCY_SENDSELL_TEMPLATE("BUY_CURRENCY_SENDSELL_TEMPLATE", "买方打款后卖家短信通知",
                                           "【Bitrade】订单:%s对方已完成打款，:%s个%s，单价:%s元，总价为:%s元，请您及时确认收款并放币。"),

    SELL_CURRENCY_SENDBUY_TEMPLATE("SELL_CURRENCY_SENDBUY_TEMPLATE", "卖方放币后买方短信提醒",
                                           "【Bitrade】订单:%s对方已确认收款并放币，%s个%s，单价:%s元，总价为:%s元，请您及时查收。"),

    RECHARGE_TEMPLATE("RECHARGE_TEMPLATE", "冲币成功",
                              "【Bitrade】尊敬的用户您好,恭喜您于%s充值%s成功，充值数量为:%s"),

    WITHDRAW_TEMPLATE("WITHDRAW_TEMPLATE", "提币成功",
                              "【Bitrade】尊敬的用户您好,恭喜您于%s提取%s成功！,数量为:%s,订单号为:%s");



    MessageTemplateEnum(String busType, String busName, String location) {
        this.busType = busType;
        this.busName = busName;
        this.location = location;
    }

    /**
     * 总线类型
     */
    private String busType;

    /**
     * 总线名称
     */
    private String busName;

    /**
     * 消息
     */
    private String location;
}
