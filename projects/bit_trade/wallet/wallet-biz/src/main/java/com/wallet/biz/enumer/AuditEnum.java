package com.wallet.biz.enumer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class   AuditEnum {

//    public enum orderType{
//        未审核,客服已审核,财务已审核,拒绝
//    };

    //  1是未审核，2是客服已审核，3是财务已审核，4是财务拒绝，5是成功，-1客服已拒绝
    public enum orderType {
        SERVICEREFUSE(-1,"客服已拒绝"),NOTAUDITED(1, "未审核"), CSAUDITED(2, "客服已审核"), FINANCEAUDITED(3, "财务已审核"), REFUSE(4, "财务拒绝"),SUCCEED(5, "成功");
        orderType(Integer code, String value) {
            this.code = code;
            this.value = value;
        }
        @Getter
        private Integer	code;
        @Getter
        private String	value;
    }
}
