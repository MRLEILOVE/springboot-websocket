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
 * 定时任务调度日志表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="sys_job_log")
public class SysJobLog extends BaseModel<SysJobLog> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 任务日志ID
		 */
		public static final String JOB_LOG_ID = "job_log_id";
		
		/**
		 * 任务名称
		 */
		public static final String JOB_NAME = "job_name";
		
		/**
		 * 任务组名
		 */
		public static final String JOB_GROUP = "job_group";
		
		/**
		 * 任务方法
		 */
		public static final String METHOD_NAME = "method_name";
		
		/**
		 * 方法参数
		 */
		public static final String METHOD_PARAMS = "method_params";
		
		/**
		 * 日志信息
		 */
		public static final String JOB_MESSAGE = "job_message";
		
		/**
		 * 执行状态（0正常 1失败）
		 */
		public static final String STATUS = "status";
		
		/**
		 * 异常信息
		 */
		public static final String EXCEPTION_INFO = "exception_info";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
	};
	
	/**
	 * 任务日志ID
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "jobLogId", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long jobLogId;
	
	/**
	 * 任务名称
	 */
	private String jobName;
	
	/**
	 * 任务组名
	 */
	private String jobGroup;
	
	/**
	 * 任务方法
	 */
	private String methodName;
	
	/**
	 * 方法参数
	 */
	private String methodParams;
	
	/**
	 * 日志信息
	 */
	private String jobMessage;
	
	/**
	 * 执行状态（0正常 1失败）
	 */
	private String status;
	
	/**
	 * 异常信息
	 */
	private String exceptionInfo;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
}
