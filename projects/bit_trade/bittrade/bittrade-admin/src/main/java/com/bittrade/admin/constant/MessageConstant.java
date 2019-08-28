package com.bittrade.admin.constant;

import lombok.Getter;

/**
 * message 通用消息模板
 *
 * @author RLY
 */

public class MessageConstant {

    /**
     * 聚合模板
     *
     * @author who ?
     */
    public enum SmsTempletEnum {

        /**
         * 通用模板(短信内容:验证码${code}, 您的验证码是XXXX, 感谢您的支持！)
         */
        USER_REGISTER_TEMPLATE("REGISTER_TEMPLATE", "用户注册", "【Bitrade】尊敬的用户您好,您的验证码是#code,验证码有效时间10分钟！"),
        USER_USERSEND_TEMPLATE("USERSEND_TEMPLATE", "修改支付密码", "【Bitrade】尊敬的用户您好,您的验证码是#code,验证码有效时间10分钟！"),
        USER_RECEIVING_TEMPLATE("RECEIVING_TEMPLATE", "修改收付款二维码", "【Bitrade】尊敬的用户您好,您的验证码是#code,验证码有效时间10分钟！"),
        USER_BETA_PAY("USER_BETA_PAY", "beta狗出售", "【Bitrade】尊敬的用户您好,你有宠物订单被抢购，请尽快登录系统进行处理，否则2小时后将自动交易！"),
        USER_BETA_TRANSFER("USER_BETA_TRANSFER", "beta狗领养", "【Bitrade】尊敬的用户您好,你订单状态有变化，请尽快登录系统进行处理，否则将自动转入非正常账户！"),
        Quotation_Health_TEMPLATE("Quotation_Health_TEMPLATE", "行情检查", "【Bitrade】您好,行情出现波动，现已无法正常连接！"),

        SELL_SELL_CURRENCY_SENDBUY_TEMPLATE("SELL_SELL_CURRENCY_SENDBUY_TEMPLATE", "卖方出售货币通知买家付款",
                "【Bitrade】#orderNumber对方向您出售，#orderCount个#orderBoth，单价#orderAmount元，总价为#orderTotalAmount元，请您及时打款。"),

        BUY_CURRENCY_SENDSELL_TEMPLATE("BUY_CURRENCY_SENDSELL_TEMPLATE", "买方打款后卖家短信通知",
                "【Bitrade】订单#orderNumber对方已完成打款，#orderCount个#orderBoth，单价#orderAmount元，总价为#orderTotalAmount元，请您及时确认收款并放币。"),

        SELL_CURRENCY_SENDBUY_TEMPLATE("SELL_CURRENCY_SENDBUY_TEMPLATE", "卖方放币后买方短信提醒",
                "【Bitrade】订单#orderNumber对方已确认收款并放币，#orderCount个#orderBoth，单价#orderAmount元，总价为#orderTotalAmount元，请您及时查收。"),

        RECHARGE_TEMPLATE("RECHARGE_TEMPLATE", "冲币成功",
                                               "【Bitrade】尊敬的用户您好,恭喜您于#now充值#cointype成功，充值数量为#amount"),

        WITHDRAW_TEMPLATE("WITHDRAW_TEMPLATE", "提币成功",
                                  "【Bitrade】尊敬的用户您好,恭喜您于#now提取#cointype成功！,数量为#amount,订单号为#orderId");



        SmsTempletEnum(String busType, String busName, String location) {
            this.busType = busType;
            this.busName = busName;
            this.location = location;
        }

        public static SmsTempletEnum getEnum(String location) {
            SmsTempletEnum smsTempletEnum = null;
            for (SmsTempletEnum ele : SmsTempletEnum.values()) {
                if (location.equals(ele.getLocation())) {
                    smsTempletEnum = ele;
                    break;
                }
            }
            return smsTempletEnum;
        }

        @Getter
        private String busType;

        @Getter
        private String busName;

        @Getter
        private String location;
    }

    /**
     * 邮件模板
     *
     * @author who ?
     */
    public enum EmailTemplateEnum {

    }

}
