package com.wallet.biz.pojo.dto;

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
public class TCurrencyDTO extends BaseDTO<TCurrencyDTO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String shortName;
	private Byte status;
	private Byte type;
	private Byte isRecharge;
	private Byte isWithdraw;
	private String desc;
	private java.time.LocalDateTime createTime;

}
