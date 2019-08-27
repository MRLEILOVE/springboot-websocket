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
 * 
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_user")
public class TUser extends BaseModel<TUser> {
	
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
		public static final String ID = "id";
		
		/**
		 * 是否证件绑定:1绑定，0无效
		 */
		public static final String IS_IDENTITY_VALIDATE = "is_identity_validate";
		
		/**
		 * 是否邮箱绑定:1绑定，0无效
		 */
		public static final String IS_MAIL_VALIDATE = "is_mail_validate";
		
		/**
		 * 登录名
		 */
		public static final String LOGIN_NAME = "login_name";
		
		/**
		 * 登录密码
		 */
		public static final String LOGIN_PASSWORD = "login_password";
		
		/**
		 * 用户昵称
		 */
		public static final String NICK_NAME = "nick_name";
		
		/**
		 * 真实姓名
		 */
		public static final String REAL_NAME = "real_name";
		
		/**
		 * 电话号码
		 */
		public static final String TELE_PHONE = "tele_phone";
		
		/**
		 * 推荐码
		 */
		public static final String RECOMMEND_CODE = "recommend_code";
		
		/**
		 * 邮箱地址
		 */
		public static final String USER_EMAIL = "user_email";
		
		/**
		 * 手机区域码
		 */
		public static final String PHONE_AREA_CODE = "phone_area_code";
		
		/**
		 * 是否电话绑定:1绑定，0无效
		 */
		public static final String IS_TEL_VALIDATE = "is_tel_validate";
		
		/**
		 * 用户地址
		 */
		public static final String USER_ADDRESS = "user_address";
		
		/**
		 * 证件类型:证件类型:1.身份证 2.军官证，3.护照 4.台湾居民通行证 5.港澳居民通行证
		 */
		public static final String IDENTITY_TYPE = "identity_type";
		
		/**
		 * 证件号码
		 */
		public static final String IDENTITY_NO = "identity_no";
		
		/**
		 * 手机验证时间
		 */
		public static final String TEL_VALIDATE_TIME = "tel_validate_time";
		
		/**
		 * 邮箱验证时间
		 */
		public static final String MAIL_VALIDATE_TIME = "mail_validate_time";
		
		/**
		 * 账号类型:缺省
		 */
		public static final String TYPE = "type";
		
		/**
		 * 账号状态:1正常，0冻结
		 */
		public static final String STATUS = "status";
		
		/**
		 * 第一次登陆ip
		 */
		public static final String USER_FIRST_IP = "user_first_ip";
		
		/**
		 * 更新时间
		 */
		public static final String UPDATE_TIME = "update_time";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
		/**
		 * 
		 */
		public static final String EXTEND1 = "extend1";
		
		/**
		 * 
		 */
		public static final String EXTEND2 = "extend2";
		
		/**
		 * 
		 */
		public static final String EXTEND3 = "extend3";
		
	};
	
	/**
	 * 用户ID
	 */
	private Long id;
	
	/**
	 * 是否证件绑定:1绑定，0无效
	 */
	private Byte isIdentityValidate;
	
	/**
	 * 是否邮箱绑定:1绑定，0无效
	 */
	private Byte isMailValidate;
	
	/**
	 * 登录名
	 */
	private String loginName;
	
	/**
	 * 登录密码
	 */
	private String loginPassword;
	
	/**
	 * 用户昵称
	 */
	private String nickName;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 电话号码
	 */
	private String telePhone;
	
	/**
	 * 推荐码
	 */
	private String recommendCode;
	
	/**
	 * 邮箱地址
	 */
	private String userEmail;
	
	/**
	 * 手机区域码
	 */
	private String phoneAreaCode;
	
	/**
	 * 是否电话绑定:1绑定，0无效
	 */
	private Byte isTelValidate;
	
	/**
	 * 用户地址
	 */
	private String userAddress;
	
	/**
	 * 证件类型:证件类型:1.身份证 2.军官证，3.护照 4.台湾居民通行证 5.港澳居民通行证
	 */
	private Byte identityType;
	
	/**
	 * 证件号码
	 */
	private String identityNo;
	
	/**
	 * 手机验证时间
	 */
	private java.time.LocalDateTime telValidateTime;
	
	/**
	 * 邮箱验证时间
	 */
	private java.time.LocalDateTime mailValidateTime;
	
	/**
	 * 账号类型:缺省
	 */
	private Byte type;
	
	/**
	 * 账号状态:1正常，0冻结
	 */
	private Byte status;
	
	/**
	 * 第一次登陆ip
	 */
	private String userFirstIp;
	
	/**
	 * 更新时间
	 */
	private java.time.LocalDateTime updateTime;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 
	 */
	private String extend1;
	
	/**
	 * 
	 */
	private String extend2;
	
	/**
	 * 
	 */
	private String extend3;
	
}
