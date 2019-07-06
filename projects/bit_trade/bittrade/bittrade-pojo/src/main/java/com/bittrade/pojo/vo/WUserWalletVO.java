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
public class WUserWalletVO extends BaseVO<WUserWalletVO> {

	private static final long serialVersionUID = 1L;

	private long id;
	private String platform;
	private long userId;
	private String coinType;
	private String address;
	private String privateKey;
	private byte flag;
	private String valid;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}
