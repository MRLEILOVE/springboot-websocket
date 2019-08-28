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
 * 操作日志记录
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
@TableName("sys_oper_log")
public class SysOperLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    @TableId(value = "oper_id", type = IdType.AUTO)
    @Excel(name = "操作序号")
    private Integer operId;
    /**
     * 模块标题
     */
    @Excel(name = "操作模块")
    private String title;
    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @TableField("business_type")
    @Excel(name = "业务类型")
    private Integer businessType;
    /**
     * 方法名称
     */
    @Excel(name = "请求方法")
    private String method;
    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    @TableField("operator_type")
    private Integer operatorType;
    /**
     * 操作人员
     */
    @TableField("oper_name")
    @Excel(name = "操作人员")
    private String operName;
    /**
     * 部门名称
     */
    @TableField("dept_name")
    @Excel(name = "部门名称")
    private String deptName;
    /**
     * 请求URL
     */
    @TableField("oper_url")
    @Excel(name = "请求地址")
    private String operUrl;
    /**
     * 主机地址
     */
    @TableField("oper_ip")
    private String operIp;
    /**
     * 操作地点
     */
    @TableField("oper_location")
    @Excel(name = "操作地址")
    private String operLocation;
    /**
     * 请求参数
     */
    @TableField("oper_param")
    private String operParam;
    /**
     * 操作状态（1正常 0异常）
     */
    private Integer status;
    /**
     * 错误消息
     */
    @TableField("error_msg")
    private String errorMsg;
    /**
     * 操作时间
     */
    @TableField("oper_time")
    private Date operTime;


    public Integer getOperId() {
        return operId;
    }

    public void setOperId(Integer operId) {
        this.operId = operId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public String getOperLocation() {
        return operLocation;
    }

    public void setOperLocation(String operLocation) {
        this.operLocation = operLocation;
    }

    public String getOperParam() {
        return operParam;
    }

    public void setOperParam(String operParam) {
        this.operParam = operParam;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    @Override
    public String toString() {
        return "SysOperLog{" +
        "operId=" + operId +
        ", title=" + title +
        ", businessType=" + businessType +
        ", method=" + method +
        ", operatorType=" + operatorType +
        ", operName=" + operName +
        ", deptName=" + deptName +
        ", operUrl=" + operUrl +
        ", operIp=" + operIp +
        ", operLocation=" + operLocation +
        ", operParam=" + operParam +
        ", status=" + status +
        ", errorMsg=" + errorMsg +
        ", operTime=" + operTime +
        "}";
    }
}
