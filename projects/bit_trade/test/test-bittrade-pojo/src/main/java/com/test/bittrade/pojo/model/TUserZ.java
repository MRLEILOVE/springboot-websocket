package com.test.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * abc用户
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_user_z")
public class TUserZ extends BaseModel<TUserZ> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 主键ID
		 */
		public static final String TEST_ID = "test_id";
		
		/**
		 * 年龄
		 */
		public static final String AGE = "age";
		
		/**
		 * 名称
		 */
		public static final String NAME = "name";
		
		/**
		 * 手机号码
		 */
		public static final String PHONE = "phone";
		
		/**
		 * 测试
		 */
		public static final String ROLE = "role";
		
		/**
		 * 租户ID
		 */
		public static final String TENANT_ID = "tenant_id";
		
		/**
		 * 日期
		 */
		public static final String TEST_DATE = "test_date";
		
		/**
		 * 测试下划线字段命名类型
		 */
		public static final String TEST_TYPE = "test_type";
		
	};
	
	/**
	 * 主键ID
	 */
	private long testId;
	
	/**
	 * 年龄
	 */
	private int age;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 手机号码
	 */
	private String phone;
	
	/**
	 * 测试
	 */
	private long role;
	
	/**
	 * 租户ID
	 */
	private long tenantId;
	
	/**
	 * 日期
	 */
	private java.util.Date testDate;
	
	/**
	 * 测试下划线字段命名类型
	 */
	private int testType;
	
}
