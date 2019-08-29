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
 * 参数配置表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_param_config")
public class TParamConfig extends BaseModel<TParamConfig> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 主键自增
		 */
		public static final String ID = "id";
		
		/**
		 * 参数键
		 */
		public static final String PARAM_KEY = "param_key";
		
		/**
		 * 参数值
		 */
		public static final String PARAM_VALUE = "param_value";
		
		/**
		 * 启动状态,1：开启, 0:不开启
		 */
		public static final String PARAM_STATUS = "param_status";
		
		/**
		 * 参数备注
		 */
		public static final String PARAM_REMARK = "param_remark";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
		/**
		 * 创建人
		 */
		public static final String CREATER = "creater";
		
		/**
		 * 更新时间
		 */
		public static final String UPDATE_TIME = "update_time";
		
		/**
		 * 更新人
		 */
		public static final String UPDATER = "updater";
		
	};
	
	/**
	 * 主键自增
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer id;
	
	/**
	 * 参数键
	 */
	private String paramKey;
	
	/**
	 * 参数值
	 */
	private String paramValue;
	
	/**
	 * 启动状态,1：开启, 0:不开启
	 */
	private Integer paramStatus;
	
	/**
	 * 参数备注
	 */
	private String paramRemark;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 创建人
	 */
	private String creater;
	
	/**
	 * 更新时间
	 */
	private java.time.LocalDateTime updateTime;
	
	/**
	 * 更新人
	 */
	private String updater;
	
}
