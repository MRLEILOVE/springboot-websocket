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
@TableName(value="sys_config")
public class SysConfig extends BaseModel<SysConfig> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 参数主键
		 */
		public static final String CONFIG_ID = "config_id";
		
		/**
		 * 参数名称
		 */
		public static final String CONFIG_NAME = "config_name";
		
		/**
		 * 参数键名
		 */
		public static final String CONFIG_KEY = "config_key";
		
		/**
		 * 参数键值
		 */
		public static final String CONFIG_VALUE = "config_value";
		
		/**
		 * 系统内置（Y是 N否）
		 */
		public static final String CONFIG_TYPE = "config_type";
		
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
		 * 备注
		 */
		public static final String REMARK = "remark";
		
	};
	
	/**
	 * 参数主键
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "configId", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer configId;
	
	/**
	 * 参数名称
	 */
	private String configName;
	
	/**
	 * 参数键名
	 */
	private String configKey;
	
	/**
	 * 参数键值
	 */
	private String configValue;
	
	/**
	 * 系统内置（Y是 N否）
	 */
	private String configType;
	
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
	 * 备注
	 */
	private String remark;
	
}
