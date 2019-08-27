package com.bittrade.pojo.dto;

import com.core.framework.base.DTO.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserDTO extends BaseDTO<SysUserDTO> {

	private static final long serialVersionUID = 1L;

	private Integer userId;
	private String avatar;
	private Integer deptId;
	private String loginName;
	private String userName;
	private String userType;
	private String email;
	private String phonenumber;
	private String sex;
	private String password;
	private String salt;
	private String status;
	private String delFlag;
	private String loginIp;
	private java.time.LocalDateTime loginDate;
	private String createBy;
	private java.time.LocalDateTime createTime;
	private String updateBy;
	private java.time.LocalDateTime updateTime;
	private String remark;

}
