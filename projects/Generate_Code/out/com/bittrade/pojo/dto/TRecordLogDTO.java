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
public class TRecordLogDTO extends BaseDTO<TRecordLogDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Byte equipmentType;
	private String businessType;
	private String equipmentMark;
	private String content;
	private java.time.LocalDateTime createTime;

}
