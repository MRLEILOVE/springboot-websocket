package com.test.bittrade.svc.pojo.model;

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
	 * PK
	 */
	private int id;
	
	/**
	 * 名称
	 */
	private String name;
	
}
