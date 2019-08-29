package com.bittrade.uac.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 〈一句话功能简述〉
 * <p>
 *
 * @author yongheng
 * @since 2018/11/22
 */
@Data
public class AuthenticationDto implements Serializable {
	private static final long	serialVersionUID	= -7808871334808024475L;
	// 条件参数
	private Date				beginTime;									// 开始时间
	private Date				closeTime;									// 关闭时间
	private String				status;										// 状态
	private String				keyWord;									// 关键字

	// 返回参数
	private Long				userId;										// 用户Id
	private String				loginName;									// 登录名
	private String				realName;									// 姓名
	private Integer				fhasRealValidate;							// 审核状态
	private Date				submitTime;									// 提交时间
	private Date				registerTime;								// 注册时间
	private String				auditor;									// 审核人
	private Date				auditTime;									// 审核时间
}