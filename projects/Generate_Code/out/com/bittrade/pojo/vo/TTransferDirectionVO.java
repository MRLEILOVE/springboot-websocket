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
public class TTransferDirectionVO extends BaseVO<TTransferDirectionVO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;
	private Integer mainAccountId;
	private Integer targetAccountId;
	private Integer status;

}
