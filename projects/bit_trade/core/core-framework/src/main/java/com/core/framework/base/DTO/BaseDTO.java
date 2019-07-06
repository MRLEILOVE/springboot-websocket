package com.core.framework.base.DTO;

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

}
