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
 * 操作日志记录
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="sys_oper_log")
public class SysOperLog extends BaseModel<SysOperLog> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 日志主键
		 */
		public static final String OPER_ID = "oper_id";
		
		/**
		 * 模块标题
		 */
		public static final String TITLE = "title";
		
		/**
		 * 业务类型（0其它 1新增 2修改 3删除）
		 */
		public static final String BUSINESS_TYPE = "business_type";
		
		/**
		 * 方法名称
		 */
		public static final String METHOD = "method";
		
		/**
		 * 操作类别（0其它 1后台用户 2手机端用户）
		 */
		public static final String OPERATOR_TYPE = "operator_type";
		
		/**
		 * 操作人员
		 */
		public static final String OPER_NAME = "oper_name";
		
		/**
		 * 部门名称
		 */
		public static final String DEPT_NAME = "dept_name";
		
		/**
		 * 请求URL
		 */
		public static final String OPER_URL = "oper_url";
		
		/**
		 * 主机地址
		 */
		public static final String OPER_IP = "oper_ip";
		
		/**
		 * 操作地点
		 */
		public static final String OPER_LOCATION = "oper_location";
		
		/**
		 * 请求参数
		 */
		public static final String OPER_PARAM = "oper_param";
		
		/**
		 * 操作状态（1正常 0异常）
		 */
		public static final String STATUS = "status";
		
		/**
		 * 错误消息
		 */
		public static final String ERROR_MSG = "error_msg";
		
		/**
		 * 操作时间
		 */
		public static final String OPER_TIME = "oper_time";
		
	};
	
	/**
	 * 日志主键
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "operId", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer operId;
	
	/**
	 * 模块标题
	 */
	private String title;
	
	/**
	 * 业务类型（0其它 1新增 2修改 3删除）
	 */
	private Integer businessType;
	
	/**
	 * 方法名称
	 */
	private String method;
	
	/**
	 * 操作类别（0其它 1后台用户 2手机端用户）
	 */
	private Integer operatorType;
	
	/**
	 * 操作人员
	 */
	private String operName;
	
	/**
	 * 部门名称
	 */
	private String deptName;
	
	/**
	 * 请求URL
	 */
	private String operUrl;
	
	/**
	 * 主机地址
	 */
	private String operIp;
	
	/**
	 * 操作地点
	 */
	private String operLocation;
	
	/**
	 * 请求参数
	 */
	private String operParam;
	
	/**
	 * 操作状态（1正常 0异常）
	 */
	private Integer status;
	
	/**
	 * 错误消息
	 */
	private String errorMsg;
	
	/**
	 * 操作时间
	 */
	private java.time.LocalDateTime operTime;
	
}
