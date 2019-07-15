package com.test.pojo.vo;

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

	private Long testId;
	private Integer age;
	private String name;
	private String phone;
	private Long role;
	private Long tenantId;
	private java.util.Date testDate;
	private Integer testType;

}
