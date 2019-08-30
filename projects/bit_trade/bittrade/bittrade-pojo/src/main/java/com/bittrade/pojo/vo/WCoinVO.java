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
public class WCoinVO extends BaseVO<WCoinVO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String coinType;
	private String token;
	private String contract;
	private String isRecharge;
	private String isWithdraw;
	private Byte status;
	private String remark;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}
