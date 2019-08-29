package com.bittrade.uac.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 用户id公共类
 * <p>
 *
 * @author yongheng
 * @since 2018/11/10
 */
@Data
public class UserIdDto implements Serializable {
	private static final long	serialVersionUID	= 1L;

	@NotNull
	private Long				userId;

}