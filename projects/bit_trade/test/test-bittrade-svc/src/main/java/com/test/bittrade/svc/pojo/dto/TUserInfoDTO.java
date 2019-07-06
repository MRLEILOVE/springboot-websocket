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
public class TUserInfoDTO extends BaseDTO<TUserInfoDTO> {

	private static final long serialVersionUID = 1L;

	private int userId;
	private int age;

}
