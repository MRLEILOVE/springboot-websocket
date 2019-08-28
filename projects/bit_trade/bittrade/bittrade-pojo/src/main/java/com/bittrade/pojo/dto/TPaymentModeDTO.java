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
public class TPaymentModeDTO extends BaseDTO<TPaymentModeDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Byte type;
	private String trueName;
	private String card;
	private String bankName;
	private String bankBranch;
	private String qrCode;
	private Byte status;
	private Byte displaySetting;
	private String belongCurrency;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}
