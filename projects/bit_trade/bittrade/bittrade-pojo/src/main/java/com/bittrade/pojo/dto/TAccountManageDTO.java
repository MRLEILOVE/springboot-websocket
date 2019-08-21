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
public class TAccountManageDTO extends BaseDTO<TAccountManageDTO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;
	private String name;
	private String keyName;
	private String value;
	private String locality;

}
