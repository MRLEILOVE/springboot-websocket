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
public class TRecordLogVO extends BaseVO<TRecordLogVO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Byte equipmentType;
	private String businessType;
	private String equipmentMark;
	private String content;
	private java.time.LocalDateTime createTime;

}
