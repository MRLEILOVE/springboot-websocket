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
public class TAccountManageVO extends BaseVO<TAccountManageVO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;
	private String name;
	private String keyName;
	private String value;
	private String locality;

}
