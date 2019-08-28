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
public class TPaymentVO extends BaseVO<TPaymentVO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String payNameCn;
	private String payNameEn;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}
