package com.bittrade.uac.model.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author oublue
 * @since 2018-10-26
 */
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
     * 登录名
     */
    @TableField("login_name")
    private String loginName;
    /**
     * 登录密码
     */
    @TableField("login_password")
    private String loginPassword;
    /**
     * 用户昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;
    /**
     * 电话号码
     */
    @TableField("tele_phone")
    private String telePhone;
    /**
     * 电话号码
     */
    @TableField("recommend_code")
    private String recommendCode;
    /**
     * 邮箱地址
     */
    @TableField("user_email")
    private String userEmail;
    /**
     * 手机区域码
     */
    @TableField("phone_area_code")
    private String phoneAreaCode;
    /**
     * 是否电话绑定:1绑定，0无效
     */
    @TableField("is_tel_validate")
    private Integer isTelValidate;
    /**
     * 是否证件绑定:1绑定，0无效
     */
    @TableField("is_identity_validate")
    private Integer isIdentityValidate;
    /**
     * 是否邮箱绑定:1绑定，0无效
     */
    @TableField("is_mail_validate")
    private Integer isMailValidate;
    /**
     * 用户地址
     */
    @TableField("user_address")
    private String userAddress;
    /**
     * 证件类型:证件类型:1.身份证 2.军官证，3.护照 4.台湾居民通行证 5.港澳居民通行证
     */
    @TableField("identity_type")
    private Integer identityType;
    /**
     * 证件号码
     */
    @TableField("identity_no")
    private String identityNo;
    /**
     * 手机验证时间
     */
    @TableField("tel_validate_time")
    private Date telValidateTime;
    /**
     * 邮箱验证时间
     */
    @TableField("mail_validate_time")
    private Date mailValidateTime;
    /**
     * 账号类型:缺省
     */
    private Integer type;
    /**
     * 账号状态:1正常，0冻结
     */
    private Integer status;
    /**
     * 第一次登录ip
     */
    @TableField("user_first_ip")
    private String userFirstIp;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    private String extend1;
    private String extend2;
    private String extend3;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public String getRecommendCode() {
        return recommendCode;
    }

    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPhoneAreaCode() {
        return phoneAreaCode;
    }

    public void setPhoneAreaCode(String phoneAreaCode) {
        this.phoneAreaCode = phoneAreaCode;
    }

    public Integer getIsTelValidate() {
        return isTelValidate;
    }

    public void setIsTelValidate(Integer isTelValidate) {
        this.isTelValidate = isTelValidate;
    }

    public Integer getIsIdentityValidate() {
        return isIdentityValidate;
    }

    public void setIsIdentityValidate(Integer isIdentityValidate) {
        this.isIdentityValidate = isIdentityValidate;
    }

    public Integer getIsMailValidate() {
        return isMailValidate;
    }

    public void setIsMailValidate(Integer isMailValidate) {
        this.isMailValidate = isMailValidate;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public Date getTelValidateTime() {
        return telValidateTime;
    }

    public void setTelValidateTime(Date telValidateTime) {
        this.telValidateTime = telValidateTime;
    }

    public Date getMailValidateTime() {
        return mailValidateTime;
    }

    public void setMailValidateTime(Date mailValidateTime) {
        this.mailValidateTime = mailValidateTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserFirstIp() {
        return userFirstIp;
    }

    public void setUserFirstIp(String userFirstIp) {
        this.userFirstIp = userFirstIp;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", loginName=" + loginName +
        ", loginPassword=" + loginPassword +
        ", nickName=" + nickName +
        ", realName=" + realName +
        ", telePhone=" + telePhone +
        ", recommendCode=" + recommendCode +
        ", userEmail=" + userEmail +
        ", phoneAreaCode=" + phoneAreaCode +
        ", isTelValidate=" + isTelValidate +
        ", isIdentityValidate=" + isIdentityValidate +
        ", isMailValidate=" + isMailValidate +
        ", userAddress=" + userAddress +
        ", identityType=" + identityType +
        ", identityNo=" + identityNo +
        ", telValidateTime=" + telValidateTime +
        ", mailValidateTime=" + mailValidateTime +
        ", type=" + type +
        ", status=" + status +
        ", userFirstIp=" + userFirstIp +
        ", updateTime=" + updateTime +
        ", createTime=" + createTime +
        ", extend1=" + extend1 +
        ", extend2=" + extend2 +
        ", extend3=" + extend3 +
        "}";
    }
}
