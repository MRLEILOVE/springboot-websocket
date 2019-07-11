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
public class WConfigWalletVO extends BaseVO<WConfigWalletVO> {

	private static final long serialVersionUID = 1L;

	private long id;
	private String coinType;
	private String walletType;
	private String address;
	private String keystore;
	private String valid;
	private String remark;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}
