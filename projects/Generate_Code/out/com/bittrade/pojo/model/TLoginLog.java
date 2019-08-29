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
 * 
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_login_log")
public class TLoginLog extends BaseModel<TLoginLog> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 主键
		 */
		public static final String ID = "id";
		
		/**
		 * 登录用户ID
		 */
		public static final String USER_ID = "user_id";
		
		/**
		 * 登录名称
		 */
		public static final String LOGIN_NAME = "login_name";
		
		/**
		 * 登录方式:缺省
		 */
		public static final String LGOIN_TYPE = "lgoin_type";
		
		/**
		 * 登录IP
		 */
		public static final String LOGIN_IP = "login_ip";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
		/**
		 * 更新时间
		 */
		public static final String UPDATE_TIME = "update_time";
		
		/**
		 * 缺省状态
		 */
		public static final String STATUS = "status";
		
		/**
		 * 缺省1
		 */
		public static final String EXTEND1 = "extend1";
		
		/**
		 * 缺省2
		 */
		public static final String EXTEND2 = "extend2";
		
		/**
		 * 缺省3
		 */
		public static final String EXTEN3 = "exten3";
		
	};
	
	/**
	 * 主键
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long id;
	
	/**
	 * 登录用户ID
	 */
	private Long userId;
	
	/**
	 * 登录名称
	 */
	private Long loginName;
	
	/**
	 * 登录方式:缺省
	 */
	private Byte lgoinType;
	
	/**
	 * 登录IP
	 */
	private String loginIp;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 更新时间
	 */
	private java.time.LocalDateTime updateTime;
	
	/**
	 * 缺省状态
	 */
	private Byte status;
	
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
	
}
