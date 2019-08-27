package com.bittrade.admin.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 用户状态
 *
 * @author who ?
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEnum {

	// 用户状态
	public enum UserState {

		NORMAL_USER(1, "正常"), LOCK_USER(0, "禁用");
		UserState(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;

	}

	// 用户状态
	public enum Internal {

		NORMAL_ACCOUNT(1, "正常账户"), INTERNAL_ACCOUNT(2, "内部账户");
		Internal(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;

	}

	// 订单状态
	public enum OrderState {

		OPEN_ORDER(1, "建仓"), CLOSE_ORDER(2, "平仓"), BOMB_ORDER(3, "爆仓"), TOKEN_HIDE(10, "体验金订单隐藏") ;
		OrderState(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;

	}

	// 用户账户类型
	public enum UserAuccountType {

		MOBILE_AUCCOUNT(0, "手机用户"), EMAIL_AUCCOUNT(1, "邮箱用户");
		UserAuccountType(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;

	}

	// 合约类型
	public enum ContractType {

		SHORT_CONTRACT(1, "微盘合约"), LASTING_CONTRACT(2, "永续合约");
		ContractType(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;

	}

	// 合约类型
	public enum DirectionType {

		DIRECTION_DOWN(1, "看跌"), DIRECTION_UP(2, "看涨");
		DirectionType(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;

	}

	@AllArgsConstructor
	@Getter
	public enum ValidateStatus{
		VALIDATE_N(0, "未验证"), VALIDATE_Y(1, "验证"), VALIDATE_W(2, "待审核");

		private Integer	code;
		private String	value;
	}

	/*// 手机是否验证
	public enum UserTelValidate {

		VALIDATE_N(0, "未验证"), VALIDATE_Y(1, "验证");
		UserTelValidate(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;
	}

	// 邮箱是否验证
	public enum UserEmailValidate {

		VALIDATE_N(0, "未验证"), VALIDATE_Y(1, "验证");
		UserEmailValidate(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;
	}*/

	// 证件类型：1.身份证，2.军官证，3.护照，4.台湾居民通行证，5.港澳居民通行证
	public enum UserIdentityType {

		IDENTIFICAT(1, "身份证"), OFFICER(2, "军官证"), PASSPORT(3, "护照"), HKM(4, "港澳通行证");
		UserIdentityType(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;
	}

	// 性别
	public enum UserSex {

		MAN(1, "女"), WOMAN(2, "男");
		UserSex(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;

	}

	//账号类型
	@Getter
	@AllArgsConstructor
	public enum AccountType{
		COMMON_ACCOUNT(1, "正常账户"),
		INNER_ACCOUNT(2, "内部账户");

		private Integer code;
		private String value;
	}

	// 一二推荐人，推荐关系 二级-->一级-->当前用户
	public enum userLabour {
		parentUserLabour("parentUserLabour", "一级推荐人"), grandpaUserLabour("grandpaUserLabour", "二级推荐人");

		userLabour(String key, String value) {
			this.key = key;
			this.value = value;
		}

		@Getter
		private String key;
		@Getter
		private String value;
	}

}