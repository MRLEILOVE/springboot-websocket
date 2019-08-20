package com.walletbiz.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * <p>
 * 用户收款信息表
 * </p>
 *
 * @author helne
 * @since 2019-03-22
 */
@TableName("c_personal_card")
@Data
public class PersonalCard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户收款信息表ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 银行名称
     */
    @TableField("bank_name")
    private String bankName;
    /**
     * 银行卡号
     */
    @TableField("bank_number")
    private String bankNumber;
    /**
     * 支付宝收款二维码
     */
    private String alipay;
    /**
     * 支付宝账号
     */
    @TableField("alipay_number")
    private String alipayNumber;
    /**
     * 微信收款二维码
     */
    private String wechat;
    /**
     * 微信账号
     */
    @TableField("wechat_number")
    private String wechatNumber;
    /**
     * 身份证号
     */
    @TableField("card_number")
    private String cardNumber;
    /**
     * 身份证正面图片路径
     */
    @TableField("card_A_url")
    private String cardAUrl;
    /**
     * 身份证背面图片路径
     */
    @TableField("card_B_url")
    private String cardBUrl;
    /**
     * 1=认证成功，2=待审核，3=审核不成功,4=黑名单
     */
    @TableField("auth_stauts")
    private Integer authStauts;
    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 当前身份
     */
    @TableField("current_identity")
    private  String currentIdentity;

    /**
     * 是否初次审核通过1=是2=否
     */
    @TableField("init_check")
    private Integer initCheck;

    /**
     * 老用户标识0=新用户1=老用户
     */
    @TableField("old_flag")
    private Integer oldFlag;


}
