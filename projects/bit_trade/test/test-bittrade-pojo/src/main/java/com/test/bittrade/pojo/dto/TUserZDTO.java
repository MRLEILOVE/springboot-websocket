package com.test.bittrade.pojo.dto;

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

	private long testId;
	private int age;
	private String name;
	private String phone;
	private long role;
	private long tenantId;
	private java.util.Date testDate;
	private int testType;

}
