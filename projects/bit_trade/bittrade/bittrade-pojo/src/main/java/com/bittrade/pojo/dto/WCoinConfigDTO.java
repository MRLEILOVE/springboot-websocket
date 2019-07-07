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

	private long id;
	private String coinType;
	private String token;
	private String contract;
	private long scanBlock;
	private int baseMultiple;
	private int minConfirm;
	private String bossAddress;
	private java.math.BigDecimal minCollectionAmount;
	private String valid;
	private String remark;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}
