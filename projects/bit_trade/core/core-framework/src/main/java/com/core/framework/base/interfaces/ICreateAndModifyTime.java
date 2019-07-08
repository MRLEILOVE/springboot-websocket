package com.core.framework.base.interfaces;

import java.util.Date;

/**
 * 
 * @author Administrator
 * @datetime Jul 8, 2019 12:03:55 PM
 *
 */
public interface ICreateAndModifyTime {
	
	/**
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime);
	
	/**
	 * 
	 * @param modifyTime
	 */
	public void setModifyTime(Date modifyTime);
	
}
