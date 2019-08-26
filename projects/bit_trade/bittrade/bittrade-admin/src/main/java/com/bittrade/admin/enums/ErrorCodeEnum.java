package com.bittrade.admin.enums;


/**
 * The class Error code enum.
 *
 */
public enum ErrorCodeEnum {
	/**
	 * Gl 99990100 error code enum.
	 */
	GL500001(500001, "验证token失败"),
	GL500002(500002, "解析header失败"),
	GL500100(500100, "网关抛异常"),
	GL500101(500101, "网关授权失败"),
	GL500102(500102, "客户端认证异常"),
	GL500103(500103, "灰度发布中"),

	TX500200(500200, "提现风险控制"),
	GBETA2(2, "订单结算中，请稍后再试"),
	BETA2(2, "操作频繁，请稍后再试！"),
	C2C2(2, "操作频繁，请稍后再试！"),
	VERIFICATIONCODE(2, "验证码错误！"),
	HOUSE2(2, "操作频繁，请稍后再试！"),
	BIZERROR2(2, "操作频繁，请稍后再试！"),
	SMSERROR(2, "获取验证码过于频繁，请稍后再试！"),
	;

	private int code;
	private String msg;

	/**
	 * Msg string.
	 *
	 * @return the string
	 */
	public String msg() {
		return msg;
	}

	/**
	 * Code int.
	 *
	 * @return the int
	 */
	public int code() {
		return code;
	}

	ErrorCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * Gets enum.
	 *
	 * @param code the codeg
	 *
	 * @return the enum
	 */
	public static ErrorCodeEnum getEnum(int code) {
		for (ErrorCodeEnum ele : ErrorCodeEnum.values()) {
			if (ele.code() == code) {
				return ele;
			}
		}
		return null;
	}
}
