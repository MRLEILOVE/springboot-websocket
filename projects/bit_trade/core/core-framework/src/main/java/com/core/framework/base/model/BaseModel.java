package com.core.framework.base.model;

import java.util.List;
import java.util.Map;

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
	
	// 其他的也可以类似的加， yes， 类似蕾丝的。
	private Map<String, Object[]> map_in, map_like;
	
	public BaseModel<Model> in(String name, Object[] values) {
		if (map_in == null) {
			map_in = new java.util./*concurrent.Concurrent*/HashMap<>();
		}
		map_in.put(name, values);
		
		return this;
	}
	
	public BaseModel<Model> in(String name, List<Object> values) {
		return in(name, values.toArray( /*new Object[values.size()]*/ ));
	}
	
	public BaseModel<Model> like(String name, Object[] values) {
		if (map_like == null) {
			map_like = new java.util./*concurrent.Concurrent*/HashMap<>();
		}
		map_like.put(name, values);
		
		return this;
	}
	
	public BaseModel<Model> like(String name, List<Object> values) {
		return like(name, values.toArray( /*new Object[values.size()]*/ ));
	}
	
}
