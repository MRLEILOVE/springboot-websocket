package com.walletbiz.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 钱包
 * </p>
 *
 * @author helen
 * @since 2019-07-25
 */
@TableName("w_user_wallet")
public class WUserWallet implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 默认角色
     */
    private String platform;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 币种大类
     */
    @TableField("coin_type")
    private String coinType;
    /**
     * 地址
     */
    private String address;
    /**
     * 私钥
     */
    @TableField("private_key")
    private String privateKey;
    /**
     * 二维码
     */
    @TableField("code_qr")
    private String codeQr;
    /**
     * 是否需要处理  0:否   1是
     */
    private Integer flag;
    /**
     * 是否有效：D无效E有效
     */
    private String valid;
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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getCodeQr() {
        return codeQr;
    }

    public void setCodeQr(String codeQr) {
        this.codeQr = codeQr;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
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
        return "WUserWallet{" +
        "id=" + id +
        ", platform=" + platform +
        ", userId=" + userId +
        ", coinType=" + coinType +
        ", address=" + address +
        ", privateKey=" + privateKey +
        ", codeQr=" + codeQr +
        ", flag=" + flag +
        ", valid=" + valid +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
