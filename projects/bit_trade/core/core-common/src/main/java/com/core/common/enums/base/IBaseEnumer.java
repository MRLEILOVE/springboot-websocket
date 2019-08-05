package com.core.common.enums.base;

/**
 * 
 * @author Administrator
 * @datetime Jul 5, 2019 10:00:58 AM
 *
 */
public interface IBaseEnumer<T> {

	/**
	 * 
	 * @return
	 */
	public T getCode();

	/**
	 * 
	 * @return
	 */
	public String getName();

}