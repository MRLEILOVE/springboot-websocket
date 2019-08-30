package com.bittrade.admin.constant;

import java.math.BigDecimal;

/**
 * @title 全局常量
 * @author oublue
 *
 */
public class GlobalConstant {

	public static final String		ROOT_PREFIX				= "bittrade";

	// 费率基数
	public static final BigDecimal	CARDINALITY				= new BigDecimal( "1" );
	public static final BigDecimal	ENTRUST_LIMIT			= new BigDecimal( "40" );
	// IP解析常量
	public static final String		UNKNOWN					= "unknown";
	public static final String		X_FORWARDED_FOR			= "X-Forwarded-For";
	public static final String		X_REAL_IP				= "X-Real-IP";
	public static final String		PROXY_CLIENT_IP			= "Proxy-Client-IP";
	public static final String		WL_PROXY_CLIENT_IP		= "WL-Proxy-Client-IP";
	public static final String		HTTP_CLIENT_IP			= "HTTP_CLIENT_IP";
	public static final String		HTTP_X_FORWARDED_FOR	= "HTTP_X_FORWARDED_FOR";
	public static final String		LOCALHOST_IP			= "127.0.0.1";
	public static final String		LOCALHOST_IP_16			= "0:0:0:0:0:0:0:1";
	public static final String		CURRENT_YEAR			= "2019";
	public static final int			MAX_IP_LENGTH			= 15;

	// 密码长度限制
	public static final int			PASSWORD_MIN_LENGTH		= 5;
	public static final int			PASSWORD_MAX_LENGTH		= 20;
	// 用户名长度限制
	public static final int			USERNAME_MIN_LENGTH		= 2;
	public static final int			USERNAME_MAX_LENGTH		= 20;

	// 系统常量
	public static final class Sys {
		private Sys() {
		}

		// 全局用户名
		public static final String	TOKEN_AUTH_DTO			= "CURRENT_USER_DTO";

		public static final String	WEBSOCKET_TAG			= "WEBSOCKET_TAG";

		public static final String	WEBSOCKET_ID			= "WEBSOCKET_ID";

		public static final String	ADMIN					= "admin";

		// 系统成功字符串
		public static final String	SUCCESS_STR				= "success";

		// redis key default expire = 600秒
		public static final long	REDIS_DEFAULT_EXPIRE	= 600L;

		public final static String	MESSAGE_TYPE			= "text/html;charset=UTF-8";

		public final static String	DEFAULT_NUMBER			= "123456";

		public final static String	DEFAULT_SALT			= "bittrade";

		public final static String	ALL_PERMISSION			= "*:*:*";

		// 登录成功
		public static final String	LOGIN_SUCCESS			= "Success";

		// 注销
		public static final String	LOGOUT					= "Logout";

		// 登录失败
		public static final String	LOGIN_FAIL				= "Error";
	}

	// 系统外部配置头
	public static final class SysProperties {
		private SysProperties() {
		}

		public static final String	ROOT_PREFIX				= "bittrade";
		public static final String	SECURITY_PREFIX			= "bittrade.security";
		public static final String	SECURITY_FEIGN_PREFIX	= "bittrade.oauth2.client";
		public static final String	NETTY_PREFIX			= "bittrade.wss";

	}

	// 系统环境
	public static final class SysEnvironmental {
		private SysEnvironmental() {
		}

		public static final String	DEV_PROFILE		= "dev";

		public static final String	TEST_PROFILE	= "test";

		public static final String	PRO_PROFILE		= "prod";

		public static final String	UAT_PROFILE		= "uat";

		public static final String	AUTH_PROFILE	= "{" + DEV_PROFILE + "," + TEST_PROFILE + "," + PRO_PROFILE + "}";
	}

	// 代理IP筛选
	public static final class Symbol {
		private Symbol() {
		}

		/**
		 * The constant COMMA.
		 */
		public static final String	COMMA		= ",";
		public static final String	SPOT		= ".";
		/**
		 * The constant UNDER_LINE.
		 */
		public final static String	UNDER_LINE	= "_";
		/**
		 * The constant PER_CENT.
		 */
		public final static String	PER_CENT	= "%";
		/**
		 * The constant AT.
		 */
		public final static String	AT			= "@";
		/**
		 * The constant PIPE.
		 */
		public final static String	PIPE		= "||";
		public final static String	SHORT_LINE	= "-";
		public final static String	SPACE		= " ";
		public static final String	SLASH		= "/";
		public static final String	MH			= ":";
	}

	// 无意义数字
	public interface Number {
		int	ZERO_0		= 0;
		int	ONE_1		= 1;
		int	TWO_2		= 2;
		int	THREE_3		= 3;
		int	FOUR_4		= 4;
		int	FIVE_5		= 5;
		int	SIX_6		= 6;
		int	SEVEN_7		= 7;
		int	EIGHT_8		= 8;
		int	NINE_9		= 9;
		int	TEN_10		= 10;
		int	ELEVEN_11	= 11;
		int	THREE_MIN	= 180;
		int	ONE_THOUSAND_EIGHT_HUNDRED	= 1800;
		int	THREE_LAKH	= 100000;
		int DAY_TIME_LONG = 86400;
	}

	public static final class UserConstant {

		/** 登录名称是否唯一的返回结果码 */
		public final static String	USER_NAME_UNIQUE		= "0";
		public final static String	USER_NAME_NOT_UNIQUE	= "1";

		/** 手机号码是否唯一的返回结果 */
		public final static String	USER_PHONE_UNIQUE		= "0";
		public final static String	USER_PHONE_NOT_UNIQUE	= "1";

		/** e-mail 是否唯一的返回结果 */
		public final static String	USER_EMAIL_UNIQUE		= "0";
		public final static String	USER_EMAIL_NOT_UNIQUE	= "1";

		/** 部门名称是否唯一的返回结果码 */
		public final static String	DEPT_NAME_UNIQUE		= "0";
		public final static String	DEPT_NAME_NOT_UNIQUE	= "1";

		/** 角色名称是否唯一的返回结果码 */
		public final static String	ROLE_NAME_UNIQUE		= "0";
		public final static String	ROLE_NAME_NOT_UNIQUE	= "1";

		/** 岗位名称是否唯一的返回结果码 */
		public final static String	POST_NAME_UNIQUE		= "0";
		public final static String	POST_NAME_NOT_UNIQUE	= "1";

		/** 角色权限是否唯一的返回结果码 */
		public final static String	ROLE_KEY_UNIQUE			= "0";
		public final static String	ROLE_KEY_NOT_UNIQUE		= "1";

		/** 岗位编码是否唯一的返回结果码 */
		public final static String	POST_CODE_UNIQUE		= "0";
		public final static String	POST_CODE_NOT_UNIQUE	= "1";

		/** 菜单名称是否唯一的返回结果码 */
		public final static String	MENU_NAME_UNIQUE		= "0";
		public final static String	MENU_NAME_NOT_UNIQUE	= "1";

		/** 字典类型是否唯一的返回结果码 */
		public final static String	DICT_TYPE_UNIQUE		= "0";
		public final static String	DICT_TYPE_NOT_UNIQUE	= "1";

		/** 参数键名是否唯一的返回结果码 */
		public final static String	CONFIG_KEY_UNIQUE		= "0";
		public final static String	CONFIG_KEY_NOT_UNIQUE	= "1";
	}

	public static final class PageAug {
		// 当前记录起始索引
		public static String	PAGE_NUM		= "pageNum";

		// 每页显示记录数
		public static String	PAGE_SIZE		= "pageSize";

		// 排序列
		public static String	ORDER_BY_COLUMN	= "orderByColumn";

		// 排序的方向 "desc" 或者 "asc".
		public static String	IS_ASC			= "isAsc";
	}
}