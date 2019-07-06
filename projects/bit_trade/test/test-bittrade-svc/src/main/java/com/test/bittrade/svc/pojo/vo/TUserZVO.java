package com.test.bittrade.svc.pojo.vo;

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
	private long tenantId;
	private String name;
	private int age;
	private int testType;
	private java.util.Date testDate;
	private long role;
	private String phone;

}
