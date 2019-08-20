package com.wallet.biz.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author helen
 * @since 2019-07-10
 */
@TableName("t_wallet_address")
@Data
public class TWalletAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @TableField("user_id")
    private Long userId;

    @TableField("token_type")
    private String tokenType;

    private String name;
    private String address;


    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;



}
