package com.core.framework.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Administrator
 *
 * @param <Model>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseModel<Model extends com.baomidou.mybatisplus.extension.activerecord.Model<Model>> extends com.baomidou.mybatisplus.extension.activerecord.Model<Model> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int current;
	protected int size;
	
}
