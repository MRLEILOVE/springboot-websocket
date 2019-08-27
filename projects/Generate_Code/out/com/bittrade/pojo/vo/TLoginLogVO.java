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
public class TLoginLogVO extends BaseVO<TLoginLogVO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Long loginName;
	private Byte lgoinType;
	private String loginIp;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;
	private Byte status;
	private String extend1;
	private String extend2;
	private String exten3;

}
