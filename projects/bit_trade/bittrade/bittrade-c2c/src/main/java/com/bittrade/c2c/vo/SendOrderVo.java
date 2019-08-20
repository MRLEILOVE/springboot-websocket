package com.bittrade.c2c.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xzc
 * @date 2019-08-20 17:14
 * @description
 */
@Setter
@Getter
@ToString
public class SendOrderVo {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 接受者ID
     */
    private Long receiverId;
}
