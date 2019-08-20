package com.wallet.biz.pojo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order")
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 提币用户
     */
    @TableField("user_id")
    private Long userId;
    @TableField("order_id")
    private String orderId;

    /**
     * 手续费
     */
    private BigDecimal fee;

    /**
     * 具体币种
     */
    private String token;

    /**
     * 提币数量
     */
    private BigDecimal amount;

    /**
     * 接收地址
     */
    @TableField("receiver_address")
    private String receiverAddress;

    private Integer type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private Date createTime;


}
