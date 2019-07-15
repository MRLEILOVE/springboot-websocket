package com.test.pojo.dto;

import com.core.framework.base.DTO.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TUserDTO extends BaseDTO<TUserDTO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;

}
