package com.bittrade.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The class Base query.
 *
 * @author who ?
 */
@Data
public abstract class BaseQuery implements Serializable {

	private static final long	serialVersionUID	= 3319698607712846427L;

	/**
	 * 当前页
	 */
	private Long				current				= 1L;

	/**
	 * 每页条数
	 */
	private Long				size				= 10L;

	/**
	 * 排序
	 */
	private String				orderBy;

	/**
	 * 开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date				beginTime;									// 开始时间

	/**
	 * 关闭时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date				closeTime;									// 关闭时间

	/**
	 * 参数
	 */
	private Map<String, String>	params				= new HashMap<>();

	/**
	 * 起始数据
	 */
	public Long getBegin() {
		return (this.current - 1) * this.size;
	}
}
