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
public class TAcceptDealerVO extends BaseVO<TAcceptDealerVO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String acceptName;
	private String acceptBusinessId;
	private String acceptKey;
	private String acceptRechargeUrl;
	private String iconUrl;
	private Integer sort;
	private Byte status;
	private Byte type;
	private String remark;
	private java.time.LocalDateTime createTime;
	private String creater;
	private java.time.LocalDateTime updateTime;
	private String updater;

}
