package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * 系统访问记录
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="sys_logininfor")
public class SysLogininfor extends BaseModel<SysLogininfor> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 访问ID
		 */
		public static final String INFO_ID = "info_id";
		
		/**
		 * 登录账号
		 */
		public static final String LOGIN_NAME = "login_name";
		
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
		 * 登录状态（1成功 0失败）
		 */
		public static final String STATUS = "status";
		
		/**
		 * 提示消息
		 */
		public static final String MSG = "msg";
		
		/**
		 * 访问时间
		 */
		public static final String LOGIN_TIME = "login_time";
		
	};
	
	/**
	 * 访问ID
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "infoId", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer infoId;
	
	/**
	 * 登录账号
	 */
	private String loginName;
	
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
	private java.time.LocalDateTime loginTime;
	
}
