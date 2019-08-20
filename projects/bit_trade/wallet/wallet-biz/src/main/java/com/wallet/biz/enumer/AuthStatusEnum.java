package com.wallet.biz.enumer;

import lombok.Getter;

public class AuthStatusEnum {
    public enum authType {
        SUCCESS(1, "成功"), WATI(2, "待审核"), FAIL(3, "审核失败"), BLACK(4, "黑名单");
        authType(Integer code, String value) {
            this.code = code;
            this.value = value;
        }
        @Getter
        private Integer	code;
        @Getter
        private String	value;
    }
}
