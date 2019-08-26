package com.bittrade.admin.model.domain;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bittrade.admin.annotation.Excel;
import com.bittrade.admin.model.domain.base.BaseEntity;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@TableName("sys_user")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Excel(name = "用户序号")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    /**
     * 部门ID
     */
    @TableField("dept_id")
    private Integer deptId;
    /**
     * 登录账号
     */
    @TableField("login_name")
    @Excel(name = "登录名称")
    private String loginName;
    /**
     * 用户昵称
     */
    @TableField("user_name")
    @Excel(name = "用户名称")
    private String userName;
    /**
     * 用户类型（00系统用户）
     */
    @TableField("user_type")
    private String userType;
    /**
     * 用户邮箱
     */
    @Excel(name = "用户邮箱")
    private String email;
    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private String phonenumber;
    /**
     * 用户性别（0位置 1女 2男）
     */
    private String sex;
    /**
     * 头像路径
     */
    private String avatar;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐加密
     */
    private String salt;
    /**
     * 帐号状态（0停用 1正常）
     */
    private String status;
    /**
     * 删除标志（0代表删除 1标识存在）
     */
    @TableField("del_flag")
    private String delFlag;
    /**
     * 最后登录IP
     */
    @TableField("login_ip")
    private String loginIp;
    /**
     * 最后登录时间
     */
    @TableField("login_date")
    private Date loginDate;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;
    
    /** 角色组 */
    @TableField(exist=false)
    private Integer[] roleIds;
    
    @TableField(exist=false)
    private List<SysRole> roles;
    
    /** 部门对象 */
    @TableField(exist=false)
    private SysDept dept;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
    	return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public Integer[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public SysDept getDept() {
		return dept;
	}

	public void setDept(SysDept dept) {
		this.dept = dept;
	}

	@Override
    public String toString() {
        return "SysUser{" +
        "userId=" + userId +
        ", deptId=" + deptId +
        ", loginName=" + loginName +
        ", userName=" + userName +
        ", userType=" + userType +
        ", email=" + email +
        ", phonenumber=" + phonenumber +
        ", sex=" + sex +
        ", avatar=" + avatar +
        ", password=" + password +
        ", salt=" + salt +
        ", status=" + status +
        ", delFlag=" + delFlag +
        ", loginIp=" + loginIp +
        ", loginDate=" + loginDate +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", remark=" + remark +
        "}";
    }
}
