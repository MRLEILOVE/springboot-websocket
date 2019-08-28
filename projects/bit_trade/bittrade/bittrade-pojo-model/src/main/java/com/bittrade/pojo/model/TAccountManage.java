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
 * 账户配置表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_account_manage")
public class TAccountManage extends BaseModel<TAccountManage> {
	
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
		public static final String CREATE_TIME = "create_time";
		
		/**
		 * 
		 */
		public static final String UPDATE_TIME = "update_time";
		
		/**
		 * 账户名称
		 */
		public static final String NAME = "name";
		
		/**
		 * 关键词
		 */
		public static final String KEY_NAME = "key_name";
		
		/**
		 * 值
		 */
		public static final String VALUE = "value";
		
		/**
		 * 位置
		 */
		public static final String LOCALITY = "locality";
		
	};
	
	/**
	 * 
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer id;
	
	/**
	 * 
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 
	 */
	private java.time.LocalDateTime updateTime;
	
	/**
	 * 账户名称
	 */
	private String name;
	
	/**
	 * 关键词
	 */
	private String keyName;
	
	/**
	 * 值
	 */
	private String value;
	
	/**
	 * 位置
	 */
	private String locality;
	
}
