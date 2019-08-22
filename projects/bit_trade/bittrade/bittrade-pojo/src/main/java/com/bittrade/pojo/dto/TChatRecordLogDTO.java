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
public class TChatRecordLogDTO extends BaseDTO<TChatRecordLogDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long senderId;
	private Long receiverId;
	private Byte[] content;
	private Byte messageType;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}
