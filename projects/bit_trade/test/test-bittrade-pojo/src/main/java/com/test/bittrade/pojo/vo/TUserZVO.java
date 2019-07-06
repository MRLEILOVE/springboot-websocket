package com.test.bittrade.pojo.vo;

import com.core.framework.base.VO.BaseVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TUserZVO extends BaseVO<TUserZVO> {

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
