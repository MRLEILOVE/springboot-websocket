package com.bittrade.uac.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户认证VO
 * </p>
 *
 * @author yongheng
 * @since 2018-10-22
 */
@Data
public class UserAuthenticationVo implements Serializable {

	private static final long	serialVersionUID	= 1L;

	/**
	 * 用户ID
	 */
	private Long				userId;
	/**
	 * 用户真实姓名
	 */
	private String				realName;
	/**
	 * 证件号码
	 */
	private String				identityNo;
	/**
	 * 证件类型:证件类型:1.身份证 2.军官证，3.护照 4.台湾居民通行证 5.港澳居民通行证
	 */
	private Integer				identityType;
	/**
	 * 证件正面照片
	 */
	private String				identityFrontUrl;
	/**
	 * 证件背面照片
	 */
	private String				identityBackUrl;
	/**
	 * 证件是否提交:1提交，0无效
	 */
	private Integer				postRealValidate;
	/**
	 * 证件审核：0未审核，1通过，2审核中
	 */
	private Integer				fhasRealValidate;
	/**
	 * 银行卡号
	 */
	private String				bankCardNo;
	/**
	 * 开户行
	 */
	private String				bankAddress;
	/**
	 * 银行卡正面照片
	 */
	private String				bankCardUrl;
	/**
	 * 支付宝账号
	 */
	private String				alipayNo;
	/**
	 * 描述
	 */
	private String				remark;
}
