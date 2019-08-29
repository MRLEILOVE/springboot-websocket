package com.bittrade.uac.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

/**
 * @author: xzc
 * @create: 2019/8/27 下午4:01
 * @description: 认证信息
 **/
@Getter
@Setter
@ToString
@TableName("t_user_attachment")
public class UserAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户附件表主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    @TableField("uesr_id")
    private Long uesrId;
    /**
     * 证件号码
     */
    @TableField("identity_no")
    private String identityNo;
    /**
     * 证件类型:证件类型:1.身份证 2.军官证，3.护照 4.台湾居民通行证 5.港澳居民通行证
     */
    @TableField("identity_type")
    private Integer identityType;
    /**
     * 证件正面照片
     */
    @TableField("identity_front_url")
    private String identityFrontUrl;
    /**
     * 证件背面照片
     */
    @TableField("identity_back_url")
    private String identityBackUrl;
    /**
     * 证件是否提交:1提交，0无效
     */
    @TableField("post_real_validate")
    private Integer postRealValidate;
    /**
     * 证件是否审核
     */
    @TableField("fhas_real_Validate")
    private Integer fhasRealValidate;

    @TableField("update_time")
    private Date updateTime;

    @TableField("create_time")
    private Date createTime;
    /**
     * 缺省状态
     */
    private Integer status;
    /**
     * 第一次登录ip
     */
    @TableField("user_first_ip")
    private String userFirstIp;
}
