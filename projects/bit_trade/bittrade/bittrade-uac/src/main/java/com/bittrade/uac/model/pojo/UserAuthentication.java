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
 * @author yongheng
 * @since 2018-11-22
 */
@TableName("t_user_authentication")
public class UserAuthentication implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户认证表主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 用户真实姓名
     */
    @TableField("real_name")
    private String realName;
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
     * 证件正面照片七牛key
     */
    @TableField("identity_front_key")
    private String identityFrontKey;
    /**
     * 证件正面照片七牛hash
     */
    @TableField("identity_front_hash")
    private String identityFrontHash;
    /**
     * 证件背面照片
     */
    @TableField("identity_back_url")
    private String identityBackUrl;
    /**
     * 证件背面照片七牛key
     */
    @TableField("identity_back_key")
    private String identityBackKey;
    /**
     * 证件背面照片七牛hash
     */
    @TableField("identity_back_hash")
    private String identityBackHash;
    /**
     * 证件是否提交:1提交，0无效
     */
    @TableField("post_real_validate")
    private Integer postRealValidate;
    /**
     * 证件审核：0未审核，1通过，2审核中
     */
    @TableField("fhas_real_Validate")
    private Integer fhasRealValidate;
    /**
     * 银行卡号
     */
    @TableField("bank_card_no")
    private String bankCardNo;
    /**
     * 开户行
     */
    @TableField("bank_address")
    private String bankAddress;
    /**
     * 银行卡正面
     */
    @TableField("bank_card_url")
    private String bankCardUrl;
    @TableField("bank_card_key")
    private String bankCardKey;
    @TableField("bank_card_hash")
    private String bankCardHash;
    /**
     * 支付宝账号
     */
    @TableField("alipay_no")
    private String alipayNo;
    /**
     * 缺省状态
     */
    private Integer status;
    /**
     * 提交时间
     */
    @TableField("submit_time")
    private Date submitTime;
    /**
     * 审核人
     */
    private String auditor;
    /**
     * 审核时间
     */
    @TableField("audit_time")
    private Date auditTime;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public Integer getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    public String getIdentityFrontUrl() {
        return identityFrontUrl;
    }

    public void setIdentityFrontUrl(String identityFrontUrl) {
        this.identityFrontUrl = identityFrontUrl;
    }

    public String getIdentityFrontKey() {
        return identityFrontKey;
    }

    public void setIdentityFrontKey(String identityFrontKey) {
        this.identityFrontKey = identityFrontKey;
    }

    public String getIdentityFrontHash() {
        return identityFrontHash;
    }

    public void setIdentityFrontHash(String identityFrontHash) {
        this.identityFrontHash = identityFrontHash;
    }

    public String getIdentityBackUrl() {
        return identityBackUrl;
    }

    public void setIdentityBackUrl(String identityBackUrl) {
        this.identityBackUrl = identityBackUrl;
    }

    public String getIdentityBackKey() {
        return identityBackKey;
    }

    public void setIdentityBackKey(String identityBackKey) {
        this.identityBackKey = identityBackKey;
    }

    public String getIdentityBackHash() {
        return identityBackHash;
    }

    public void setIdentityBackHash(String identityBackHash) {
        this.identityBackHash = identityBackHash;
    }

    public Integer getPostRealValidate() {
        return postRealValidate;
    }

    public void setPostRealValidate(Integer postRealValidate) {
        this.postRealValidate = postRealValidate;
    }

    public Integer getFhasRealValidate() {
        return fhasRealValidate;
    }

    public void setFhasRealValidate(Integer fhasRealValidate) {
        this.fhasRealValidate = fhasRealValidate;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankCardUrl() {
        return bankCardUrl;
    }

    public void setBankCardUrl(String bankCardUrl) {
        this.bankCardUrl = bankCardUrl;
    }

    public String getBankCardKey() {
        return bankCardKey;
    }

    public void setBankCardKey(String bankCardKey) {
        this.bankCardKey = bankCardKey;
    }

    public String getBankCardHash() {
        return bankCardHash;
    }

    public void setBankCardHash(String bankCardHash) {
        this.bankCardHash = bankCardHash;
    }

    public String getAlipayNo() {
        return alipayNo;
    }

    public void setAlipayNo(String alipayNo) {
        this.alipayNo = alipayNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserAuthentication{" +
        "id=" + id +
        ", userId=" + userId +
        ", realName=" + realName +
        ", identityNo=" + identityNo +
        ", identityType=" + identityType +
        ", identityFrontUrl=" + identityFrontUrl +
        ", identityFrontKey=" + identityFrontKey +
        ", identityFrontHash=" + identityFrontHash +
        ", identityBackUrl=" + identityBackUrl +
        ", identityBackKey=" + identityBackKey +
        ", identityBackHash=" + identityBackHash +
        ", postRealValidate=" + postRealValidate +
        ", fhasRealValidate=" + fhasRealValidate +
        ", bankCardNo=" + bankCardNo +
        ", bankAddress=" + bankAddress +
        ", bankCardUrl=" + bankCardUrl +
        ", bankCardKey=" + bankCardKey +
        ", bankCardHash=" + bankCardHash +
        ", alipayNo=" + alipayNo +
        ", status=" + status +
        ", submitTime=" + submitTime +
        ", auditor=" + auditor +
        ", auditTime=" + auditTime +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
