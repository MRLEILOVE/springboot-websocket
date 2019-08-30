package com.bittrade.uac.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: xzc
 * @create: 2019/8/27 下午2:38
 * @description:
 **/
@Data
public class UserAuthenticationDto implements Serializable {

	private static final long	serialVersionUID	= 1L;

	/**
	 * 用户真实姓名
	 */
	@NotBlank
	private String				realName;
	/**
	 * 证件号码
	 */
	@NotBlank
	private String				identityNo;
	/**
	 * 证件类型:证件类型:1.身份证 2.军官证，3.护照 4.台湾居民通行证 5.港澳居民通行证
	 */
	@NotNull
	private Integer				identityType;
	/**
	 * 证件正面照片
	 */
	private String				identityFrontUrl;
	/**
	 * 证件正面照片后缀名
	 */
	private String				identityFrontSuffix;
	/**
	 * 证件正面照片七牛key
	 */
	@NotBlank
	private String				identityFrontKey;
	/**
	 * 证件正面照片七牛hash
	 */
	private String				identityFrontHash;
	/**
	 * 证件背面照片
	 */
	private String				identityBackUrl;
	/**
	 * 证件背面照片后缀名
	 */
	private String				identityBackSuffix;
	/**
	 * 证件背面照片七牛key
	 */
	@NotBlank
	private String				identityBackKey;
	/**
	 * 证件背面照片七牛hash
	 */
	private String				identityBackHash;
	/**
	 * 银行卡号
	 */
	@NotBlank
	private String				bankCardNo;
	/**
	 * 开户行
	 */
	@NotBlank
	private String				bankAddress;
	/**
	 * 银行卡正面照片
	 */
	private String				bankCardUrl;
	/**
	 * 银行卡正面照片后缀名
	 */
	private String				bankCardSuffix;
	/**
	 * 银行卡正面照片七牛key
	 */
	@NotBlank
	private String				bankCardKey;
	/**
	 * 银行卡正面照片七牛hash
	 */
	private String				bankCardHash;
	/**
	 * 支付宝账号
	 */
	private String				alipayNo;
	/**
	 * 证件审核：0未审核，1通过，2审核中
	 */
	private Integer				fhasRealValidate;
	/**
	 * 登录账户
	 */
	private String				loginName;
	/**
	 * 审核人
	 */
	private String				auditor;
}
