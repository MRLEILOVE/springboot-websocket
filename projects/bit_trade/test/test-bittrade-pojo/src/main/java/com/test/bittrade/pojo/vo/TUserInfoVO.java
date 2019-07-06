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
public class TUserInfoVO extends BaseVO<TUserInfoVO> {

	private static final long serialVersionUID = 1L;

	private int userId;
	private int age;

}
