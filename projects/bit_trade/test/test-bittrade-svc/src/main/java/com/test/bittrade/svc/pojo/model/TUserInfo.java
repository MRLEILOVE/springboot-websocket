package com.test.bittrade.svc.pojo.model;

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
	 * 用户ID
	 */
	private int userId;
	
	/**
	 * 年龄
	 */
	private int age;
	
}
