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
public class TUserZDTO extends BaseDTO<TUserZDTO> {

	private static final long serialVersionUID = 1L;

	private Long testId;
	private Integer age;
	private String name;
	private String phone;
	private Long role;
	private Long tenantId;
	private java.util.Date testDate;
	private Integer testType;

}
