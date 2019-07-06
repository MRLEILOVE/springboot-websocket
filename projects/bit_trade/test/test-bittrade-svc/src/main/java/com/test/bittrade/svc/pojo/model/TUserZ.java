package com.test.bittrade.svc.pojo.model;

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
	 * 主键ID
	 */
	private long testId;
	
	/**
	 * 租户ID
	 */
	private long tenantId;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 年龄
	 */
	private int age;
	
	/**
	 * 测试下划线字段命名类型
	 */
	private int testType;
	
	/**
	 * 日期
	 */
	private java.util.Date testDate;
	
	/**
	 * 测试
	 */
	private long role;
	
	/**
	 * 手机号码
	 */
	private String phone;
	
}
