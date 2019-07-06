package com.test.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 系统用户
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_user")
public class TUser extends BaseModel<TUser> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * PK
		 */
		public static final String ID = "id";
		
		/**
		 * 名称
		 */
		public static final String NAME = "name";
		
	};
	
	/**
	 * PK
	 */
	private int id;
	
	/**
	 * 名称
	 */
	private String name;
	
}
