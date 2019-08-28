package com.bittrade.uac.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author oublue
 * @since 2018-10-18
 */
@TableName("t_login_log")
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 登录用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 登录名称
     */
    @TableField("login_name")
    private Long loginName;
    /**
     * 登录方式:缺省
     */
    @TableField("lgoin_type")
    private Integer lgoinType;
    /**
     * 登录IP
     */
    @TableField("login_ip")
    private String loginIp;
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
    /**
     * 缺省状态
     */
    private Integer status;
    /**
     * 缺省1
     */
    private String extend1;
    /**
     * 缺省2
     */
    private String extend2;
    /**
     * 缺省3
     */
    private String exten3;


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

    public Long getLoginName() {
        return loginName;
    }

    public void setLoginName(Long loginName) {
        this.loginName = loginName;
    }

    public Integer getLgoinType() {
        return lgoinType;
    }

    public void setLgoinType(Integer lgoinType) {
        this.lgoinType = lgoinType;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getExten3() {
        return exten3;
    }

    public void setExten3(String exten3) {
        this.exten3 = exten3;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
        "id=" + id +
        ", userId=" + userId +
        ", loginName=" + loginName +
        ", lgoinType=" + lgoinType +
        ", loginIp=" + loginIp +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", status=" + status +
        ", extend1=" + extend1 +
        ", extend2=" + extend2 +
        ", exten3=" + exten3 +
        "}";
    }
}
