package com.core.framework.base.model;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Administrator
 *
 * @param <Model>
 */
// @com.fasterxml.jackson.annotation.JsonFilter(value = "")
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseModel<Model extends com.baomidou.mybatisplus.extension.activerecord.Model<Model>>
		extends com.baomidou.mybatisplus.extension.activerecord.Model<Model> {

	/**
	 * 
	 */
	private static final long						serialVersionUID	= 1L;

	@TableField(exist = false)
	// @com.alibaba.fastjson.annotation.JSONField(serialize = false, deserialize = false)
	// @com.fasterxml.jackson.annotation.JsonIgnore
	/* transient */protected Integer				current, size;

	@TableField(exist = false)
	// @com.alibaba.fastjson.annotation.JSONField(serialize = false, deserialize = false)
	// @com.fasterxml.jackson.annotation.JsonIgnore
	/* transient */private Map<String, Object[]>	map_in, map_like;			// 其他的也可以类似的加，yes，类似蕾丝的。

	public BaseModel<Model> in(String name, Object[] values) {
		if (map_in == null) {
			map_in = new java.util./* concurrent.Concurrent */HashMap<>();
		}
		map_in.put( name, values );

		return this;
	}

	public BaseModel<Model> in(String name, List<Object> values) {
		return in( name, values.toArray( /* new Object[values.size()] */ ) );
	}

	public BaseModel<Model> like(String name, Object[] values) {
		if (map_like == null) {
			map_like = new java.util./* concurrent.Concurrent */HashMap<>();
		}
		map_like.put( name, values );

		return this;
	}

	public BaseModel<Model> like(String name, List<Object> values) {
		return like( name, values.toArray( /* new Object[values.size()] */ ) );
	}

}
