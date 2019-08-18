package com.core.framework.base.DTO;

import java.io.Serializable;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;

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
	@TableField(exist = false)
	protected Integer				current, size;
	
	@TableField(exist = false)
	private Map<String, Object[]>	map_in;
	
	public BaseDTO<DTO> in(String name, Object[] values) {
		if (map_in == null) {
			map_in = new java.util./* concurrent.Concurrent */HashMap<>();
		}
		map_in.put( name, values );

		return this;
	}

}
