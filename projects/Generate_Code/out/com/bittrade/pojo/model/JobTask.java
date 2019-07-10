package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="job_task")
public class JobTask extends BaseModel<JobTask> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 
		 */
		public static final String ID = "id";
		
		/**
		 * 
		 */
		public static final String CONTENT = "content";
		
		/**
		 * 
		 */
		public static final String SEND_TIME = "send_time";
		
		/**
		 * 
		 */
		public static final String STATUS = "status";
		
	};
	
	/**
	 * 
	 */
	private long id;
	
	/**
	 * 
	 */
	private String content;
	
	/**
	 * 
	 */
	private long sendTime;
	
	/**
	 * 
	 */
	private int status;
	
}
