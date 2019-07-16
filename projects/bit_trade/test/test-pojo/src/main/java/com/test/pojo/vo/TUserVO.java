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
public class TUserVO extends BaseVO<TUserVO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;

}