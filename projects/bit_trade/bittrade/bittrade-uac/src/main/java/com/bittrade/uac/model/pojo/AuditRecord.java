package com.bittrade.uac.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xzc
 * @create: 2019/8/27 下午2:38
 * @description: 审核记录
 **/
@TableName("t_audit_record")
public class AuditRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 审核记录表主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户id
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
     * 证件类型：1身份证，2军官证，3护照，4台湾居民通行证，5港澳居民通行证
     */
    @TableField("identity_type")
    private String identityType;
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
     * 银行卡号
     */
    @TableField("bank_card_no")
    private String bankCardNo;
    /**
     * 开户行地址
     */
    @TableField("bank_address")
    private String bankAddress;
    /**
     * 银行卡正面照片
     */
    @TableField("bank_card_url")
    private String bankCardUrl;
    /**
     * 银行卡正面照片七牛key
     */
    @TableField("bank_card_key")
    private String bankCardKey;
    /**
     * 银行卡正面照片七牛hash
     */
    @TableField("bank_card_hash")
    private String bankCardHash;
    /**
     * 支付宝账号
     */
    @TableField("alipay_no")
    private String alipayNo;
    /**
     * 审核状态：1通过，0不通过
     */
    @TableField("audit_status")
    private Integer auditStatus;
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
     * 审核意见
     */
    @TableField("audit_remark")
    private String auditRemark;
    /**
     * 审核级别，多级审核可用
     */
    @TableField("audit_level")
    private String auditLevel;
    /**
     * 审核类型：1身份认证审核，....
     */
    @TableField("audit_type")
    private String auditType;
    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改人
     */
    private String updater;
    /**
     * 修改时间
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

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
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

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
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

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getAuditLevel() {
        return auditLevel;
    }

    public void setAuditLevel(String auditLevel) {
        this.auditLevel = auditLevel;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "AuditRecord{" +
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
                ", bankCardNo=" + bankCardNo +
                ", bankAddress=" + bankAddress +
                ", bankCardUrl=" + bankCardUrl +
                ", bankCardKey=" + bankCardKey +
                ", bankCardHash=" + bankCardHash +
                ", alipayNo=" + alipayNo +
                ", auditStatus=" + auditStatus +
                ", auditor=" + auditor +
                ", auditTime=" + auditTime +
                ", auditRemark=" + auditRemark +
                ", auditLevel=" + auditLevel +
                ", auditType=" + auditType +
                ", creater=" + creater +
                ", createTime=" + createTime +
                ", updater=" + updater +
                ", updateTime=" + updateTime +
                "}";
    }
}
