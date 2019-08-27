package com.bittrade.pojo.dto;

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
public class TPaymentDTO extends BaseDTO<TPaymentDTO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String payNameCn;
	private String payNameEn;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}
