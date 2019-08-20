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
public class TTickerVO extends BaseVO<TTickerVO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String symbol;
	private String last;
	private java.time.LocalDateTime createTime;

}
