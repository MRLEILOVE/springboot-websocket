package com.test.bittrade.svc.pojo.dto;

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
	private long tenantId;
	private String name;
	private int age;
	private int testType;
	private java.util.Date testDate;
	private long role;
	private String phone;

}
