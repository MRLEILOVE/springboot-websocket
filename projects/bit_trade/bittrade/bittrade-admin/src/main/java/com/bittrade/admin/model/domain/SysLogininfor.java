package com.bittrade.admin.model.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bittrade.admin.annotation.Excel;
import com.bittrade.admin.model.domain.base.BaseEntity;

/**
 * <p>
 * 系统访问记录
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
@TableName("sys_logininfor")
public class SysLogininfor extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 访问ID
     */
    @Excel(name = "序号")
    @TableId(value = "info_id", type = IdType.AUTO)
    private Integer infoId;
    /**
     * 登录账号
     */
    @TableField("login_name")
    @Excel(name = "用户账号")
    private String loginName;
    /**
     * 登录IP地址
     */
    @Excel(name = "登录地址")
    private String ipaddr;
    /**
     * 登录地点
     */
    @TableField("login_location")
    @Excel(name = "登录地点")
    private String loginLocation;
    /**
     * 浏览器类型
     */
    @Excel(name = "浏览器")
    private String browser;
    /**
     * 操作系统
     */
    @Excel(name = "操作系统 ")
    private String os;
    /**
     * 登录状态（1成功 0失败）
     */
    private String status;
    /**
     * 提示消息
     */
    private String msg;
    /**
     * 访问时间
     */
    @TableField("login_time")
    private Date loginTime;


    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "SysLogininfor{" +
        "infoId=" + infoId +
        ", loginName=" + loginName +
        ", ipaddr=" + ipaddr +
        ", loginLocation=" + loginLocation +
        ", browser=" + browser +
        ", os=" + os +
        ", status=" + status +
        ", msg=" + msg +
        ", loginTime=" + loginTime +
        "}";
    }
}
