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
 * 定时任务调度表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="sys_job")
public class SysJob extends BaseModel<SysJob> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 任务ID
		 */
		public static final String JOB_ID = "job_id";
		
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
		 * cron执行表达式
		 */
		public static final String CRON_EXPRESSION = "cron_expression";
		
		/**
		 * 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
		 */
		public static final String MISFIRE_POLICY = "misfire_policy";
		
		/**
		 * 是否并发执行（0允许 1禁止）
		 */
		public static final String CONCURRENT = "concurrent";
		
		/**
		 * 状态（0正常 1暂停）
		 */
		public static final String STATUS = "status";
		
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
		 * 备注信息
		 */
		public static final String REMARK = "remark";
		
	};
	
	/**
	 * 任务ID
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "jobId", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long jobId;
	
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
	 * cron执行表达式
	 */
	private String cronExpression;
	
	/**
	 * 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
	 */
	private String misfirePolicy;
	
	/**
	 * 是否并发执行（0允许 1禁止）
	 */
	private String concurrent;
	
	/**
	 * 状态（0正常 1暂停）
	 */
	private String status;
	
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
	 * 备注信息
	 */
	private String remark;
	
}
