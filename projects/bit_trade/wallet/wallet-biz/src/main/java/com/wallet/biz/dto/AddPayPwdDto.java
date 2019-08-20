package com.wallet.biz.dto;

import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
public class AddPayPwdDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    private Long userId; //用户ID
    @NonNull
    private String payPassword; //新支付密码MD5
    private String historyPaypassword; //原支付密码MD5


}

