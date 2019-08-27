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
public class SysDictDataVO extends BaseVO<SysDictDataVO> {

	private static final long serialVersionUID = 1L;

	private Integer dictCode;
	private Integer dictSort;
	private String dictLabel;
	private String dictValue;
	private String dictType;
	private String cssClass;
	private String listClass;
	private String isDefault;
	private String status;
	private String createBy;
	private java.time.LocalDateTime createTime;
	private String updateBy;
	private java.time.LocalDateTime updateTime;
	private String remark;

}
