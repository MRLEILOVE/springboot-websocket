/**
 * This code is generated automatically. Please do not edit it.
 */
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
 * 在线用户记录
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = false) // true, because BeanUtil.copyObj .
@EqualsAndHashCode(callSuper = false)
@TableName(value="sys_user_online")
public class SysUserOnline extends BaseModel<SysUserOnline> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 用户会话id
		 */
		public static final String SESSIONID = "sessionId";
		
		/**
		 * 登录账号
		 */
		public static final String LOGIN_NAME = "login_name";
		
		/**
		 * 部门名称
		 */
		public static final String DEPT_NAME = "dept_name";
		
		/**
		 * 登录IP地址
		 */
		public static final String IPADDR = "ipaddr";
		
		/**
		 * 登录地点
		 */
		public static final String LOGIN_LOCATION = "login_location";
		
		/**
		 * 浏览器类型
		 */
		public static final String BROWSER = "browser";
		
		/**
		 * 操作系统
		 */
		public static final String OS = "os";
		
		/**
		 * 在线状态on_line在线off_line离线
		 */
		public static final String STATUS = "status";
		
		/**
		 * session创建时间
		 */
		public static final String START_TIMESTAMP = "start_timestamp";
		
		/**
		 * session最后访问时间
		 */
		public static final String LAST_ACCESS_TIME = "last_access_time";
		
		/**
		 * 超时时间，单位为分钟
		 */
		public static final String EXPIRE_TIME = "expire_time";
		
	};
	
	/**
	 * 用户会话id
	 */
	private String sessionId;
	
	/**
	 * 登录账号
	 */
	private String loginName;
	
	/**
	 * 部门名称
	 */
	private String deptName;
	
	/**
	 * 登录IP地址
	 */
	private String ipaddr;
	
	/**
	 * 登录地点
	 */
	private String loginLocation;
	
	/**
	 * 浏览器类型
	 */
	private String browser;
	
	/**
	 * 操作系统
	 */
	private String os;
	
	/**
	 * 在线状态on_line在线off_line离线
	 */
	private String status;
	
	/**
	 * session创建时间
	 */
	private java.time.LocalDateTime startTimestamp;
	
	/**
	 * session最后访问时间
	 */
	private java.time.LocalDateTime lastAccessTime;
	
	/**
	 * 超时时间，单位为分钟
	 */
	private Integer expireTime;
	
}
