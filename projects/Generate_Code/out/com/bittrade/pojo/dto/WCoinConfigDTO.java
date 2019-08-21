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
public class WCoinConfigDTO extends BaseDTO<WCoinConfigDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String coinType;
	private String token;
	private String contract;
	private Long scanBlock;
	private Integer baseMultiple;
	private Integer minConfirm;
	private String bossAddress;
	private java.math.BigDecimal minCollectionAmount;
	private String valid;
	private String remark;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}
