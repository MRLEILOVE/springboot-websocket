/**  
 * Project Name:core-tool  
 * File Name:MathematicsUtil.java  
 * Package Name:com.core.tool  
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/

package com.core.tool;

/**
 * <p>
 *   数学实用类<br />
 *   You like algorithms ?
 * </p>
 * ClassName:MathematicsUtil <br/>
 * Description: TODO 添加描述. <br/>
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * 
 * @author Administrator
 * @version
 * @since JDK 1.8
 * @see
 */
public class MathematicsUtil {
	
	/**
	 * 得到总页数。
	 * @param size 每页条数。
	 * @param totalSize 总条数。
	 * @return 总页数。
	 */
	public static final int getTotalPage(int size, int totalSize) {
		return (totalSize - 1) / size + 1;
	}
	
	/**
	 * 得到当前页第 1 条的索引。
	 * @param page 当前页码。
	 * @param size 每页条数。
	 * @return 当前页第一条。
	 */
	public static final int getPageBeginIndex(int page, int size) {
		return (page - 1) * size;
	}
	
	/**
	 * 得到最后页第 number 条。
	 * @param size 每页条数。
	 * @param totalSize 总条数。
	 * @param number 第几条？
	 * @return 最后页第 number 条。
	 */
	public static final int getLastPageBegin(int size, int totalSize, int number) {
		return getPageBeginIndex(getTotalPage(size, totalSize), size) + number;
	}
	
	/**
	 * 得到最后页第一条。
	 * @param size 每页条数。
	 * @param totalSize 总条数。
	 * @return 最后页第一条。
	 */
	public static final int getLastPageBegin(int size, int totalSize) {
		return getLastPageBegin(size, totalSize, 1);
	}
	
}
