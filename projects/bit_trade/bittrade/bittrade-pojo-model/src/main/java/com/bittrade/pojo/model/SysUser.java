package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 * 用户信息表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="sys_user")
public class SysUser extends BaseModel<SysUser> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 用户ID
		 */
		public static final String USER_ID = "user_id";
		
		/**
		 * 头像路径
		 */
		public static final String AVATAR = "avatar";
		
		/**
		 * 部门ID
		 */
		public static final String DEPT_ID = "dept_id";
		
		/**
		 * 登录账号
		 */
		public static final String LOGIN_NAME = "login_name";
		
		/**
		 * 用户昵称
		 */
		public static final String USER_NAME = "user_name";
		
		/**
		 * 用户类型（00系统用户）
		 */
		public static final String USER_TYPE = "user_type";
		
		/**
		 * 用户邮箱
		 */
		public static final String EMAIL = "email";
		
		/**
		 * 手机号码
		 */
		public static final String PHONENUMBER = "phonenumber";
		
		/**
		 * 用户性别（0位置 1女 2男）
		 */
		public static final String SEX = "sex";
		
		/**
		 * 密码
		 */
		public static final String PASSWORD = "password";
		
		/**
		 * 盐加密
		 */
		public static final String SALT = "salt";
		
		/**
		 * 帐号状态（0停用 1正常）
		 */
		public static final String STATUS = "status";
		
		/**
		 * 删除标志（0代表删除 1标识存在）
		 */
		public static final String DEL_FLAG = "del_flag";
		
		/**
		 * 最后登陆IP
		 */
		public static final String LOGIN_IP = "login_ip";
		
		/**
		 * 最后登陆时间
		 */
		public static final String LOGIN_DATE = "login_date";
		
		/**
		 * 创建者
		 */
		public static final String CREATE_BY = "create_by";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
		/**
		 * 更新者
		 */
		public static final String UPDATE_BY = "update_by";
		
		/**
		 * 更新时间
		 */
		public static final String UPDATE_TIME = "update_time";
		
		/**
		 * 备注
		 */
		public static final String REMARK = "remark";
		
	};
	
	/**
	 * 用户ID
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "userId", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer userId;
	
	/**
	 * 头像路径
	 */
	private String avatar;
	
	/**
	 * 部门ID
	 */
	private Integer deptId;
	
	/**
	 * 登录账号
	 */
	private String loginName;
	
	/**
	 * 用户昵称
	 */
	private String userName;
	
	/**
	 * 用户类型（00系统用户）
	 */
	private String userType;
	
	/**
	 * 用户邮箱
	 */
	private String email;
	
	/**
	 * 手机号码
	 */
	private String phonenumber;
	
	/**
	 * 用户性别（0位置 1女 2男）
	 */
	private String sex;
	
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
	private String delFlag;
	
	/**
	 * 最后登陆IP
	 */
	private String loginIp;
	
	/**
	 * 最后登陆时间
	 */
	private java.time.LocalDateTime loginDate;
	
	/**
	 * 创建者
	 */
	private String createBy;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 更新者
	 */
	private String updateBy;
	
	/**
	 * 更新时间
	 */
	private java.time.LocalDateTime updateTime;
	
	/**
	 * 备注
	 */
	private String remark;
	
}
