package com.core.framework.base.DTO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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

}
