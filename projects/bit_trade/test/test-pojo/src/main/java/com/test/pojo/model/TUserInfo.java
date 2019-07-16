package com.test.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 用户信息
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_user_info")
public class TUserInfo extends BaseModel<TUserInfo> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 用户ID
		 */
		public static final String USER_ID = "user_id";
		
		/**
		 * 年龄
		 */
		public static final String AGE = "age";
		
	};
	
	/**
	 * 用户ID
	 */
	private Integer userId;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
}
