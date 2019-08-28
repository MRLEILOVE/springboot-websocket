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
public class SysDeptDTO extends BaseDTO<SysDeptDTO> {

	private static final long serialVersionUID = 1L;

	private Integer deptId;
	private Integer parentId;
	private String ancestors;
	private String deptName;
	private Integer orderNum;
	private String leader;
	private String phone;
	private String email;
	private String status;
	private String delFlag;
	private String createBy;
	private java.time.LocalDateTime createTime;
	private String updateBy;
	private java.time.LocalDateTime updateTime;

}
