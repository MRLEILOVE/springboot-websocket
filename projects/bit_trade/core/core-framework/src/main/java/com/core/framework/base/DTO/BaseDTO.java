package com.core.framework.base.DTO;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Administrator
 *
 * @param <DTO>
 */
public abstract class BaseDTO<DTO extends BaseDTO<DTO>> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
//	@TableField(exist = false)
	protected Integer				current, size;
	
//	@TableField(exist = false)
	private Map<String, Object[]>	map_in, map_eq, map_le, map_ge;
	
	/**
	 * in 存在于
	 * @param name
	 * @param values
	 * @return
	 */
	public BaseDTO<DTO> in(String name, Object... values) {
		if (map_in == null) {
			map_in = new java.util./* concurrent.Concurrent */HashMap<>();
		}
		map_in.put( name, values );

		return this;
	}
	
	/**
	 * 等于 =
	 * @param name
	 * @param values
	 * @return
	 */
	public BaseDTO<DTO> eq(String name, Object... values) {
		if (map_eq == null) {
			map_eq = new java.util./* concurrent.Concurrent */HashMap<>();
		}
		map_eq.put( name, values );

		return this;
	}
	
	/**
	 * 小于等于 &lt;=
	 * @param name
	 * @param values
	 * @return
	 */
	public BaseDTO<DTO> le(String name, Object... values) {
		if (map_le == null) {
			map_le = new java.util./* concurrent.Concurrent */HashMap<>();
		}
		map_le.put( name, values );

		return this;
	}
	
	/**
	 * 大于等于 &gt;=
	 * @param name
	 * @param values
	 * @return
	 */
	public BaseDTO<DTO> ge(String name, Object... values) {
		if (map_ge == null) {
			map_ge = new java.util./* concurrent.Concurrent */HashMap<>();
		}
		map_ge.put( name, values );

		return this;
	}

}
