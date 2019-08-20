package com.bittrade.pojo.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 法币虚拟币
 * <br/>
 *
 * @author ：leigq
 * @date ：2019/8/20 18:52
 */
@Data
@Builder
public class LegalCurrencyCoinVO implements Serializable {

	private static final long serialVersionUID = -8138148232979178040L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 名称（英文）
	 */
	private String name;

	/**
	 * 标题（中文）
	 */
	private String title;

	/**
	 * 图标
	 */
	private String img;

	/**
	 * 状态(0 禁用 1 启用)
	 */
	private Byte status;
}
