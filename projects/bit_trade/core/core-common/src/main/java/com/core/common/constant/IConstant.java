package com.core.common.constant;

/**
 * 常量区
 * @author Administrator
 * @datetime Jul 4, 2019 11:30:30 AM
 *
 */
public interface IConstant {

	/**
	 * 成功
	 */
	public static final int SUCCESS = 200;

	/**
	 * 失败
	 */
	public static final int FAILURE = -1;

	/**
	 * 成功
	 */
	public static final String SUCCESS_TXT = "成功";

	/**
	 * 失败
	 */
	public static final String FAILURE_TXT = "失败";

	/**
	 * 默认页码
	 */
	public static final int PAGE_INDEX = 1;

	/**
	 * 默认页条数
	 */
	public static final int PAGE_SIZE = 10;
	
	/**
	 * UTF-8
	 */
	public static final String UTF_8 = "UTF-8";
	
	/**
	 * 日期时间的格式
	 */
	public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * application.yaml
	 */
	public static final String DEFAULT_SPRING_CONFIG_FILE_NAME = "application.yaml";

}
