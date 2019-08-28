package com.bittrade.pojo.vo;

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
public class WWalletAddressVO extends BaseVO<WWalletAddressVO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Long userId;
	private String name;
	private String tokenType;
	private Integer currencyId;
	private String address;
	private Byte status;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}
