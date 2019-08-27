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
public class TUserVO extends BaseVO<TUserVO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Byte isIdentityValidate;
	private Byte isMailValidate;
	private String loginName;
	private String loginPassword;
	private String nickName;
	private String realName;
	private String telePhone;
	private String recommendCode;
	private String userEmail;
	private String phoneAreaCode;
	private Byte isTelValidate;
	private String userAddress;
	private Byte identityType;
	private String identityNo;
	private java.time.LocalDateTime telValidateTime;
	private java.time.LocalDateTime mailValidateTime;
	private Byte type;
	private Byte status;
	private String userFirstIp;
	private java.time.LocalDateTime updateTime;
	private java.time.LocalDateTime createTime;
	private String extend1;
	private String extend2;
	private String extend3;

}
