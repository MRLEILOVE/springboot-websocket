package com.core.tool;

import java.math.BigDecimal;

import com.core.common.constant.ICompareResultConstant;

/**
 * 
 * @author Administrator
 * @datetime Jul 9, 2019 11:33:15 AM
 *
 */
public /*static */final class BigDecimalUtil {
	
	/**
	 * 
	 * @param bd
	 * @return
	 */
	public static final boolean isZero(BigDecimal bd) {
		return bd.compareTo(BigDecimal.ZERO) == ICompareResultConstant.EQUAL;
	}
	
	/**
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static final boolean isEqual(BigDecimal bd1, BigDecimal bd2) {
		return bd1.compareTo(bd2) == ICompareResultConstant.EQUAL;
	}
	
	/**
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static final boolean isLess(BigDecimal bd1, BigDecimal bd2) {
		return bd1.compareTo(bd2) == ICompareResultConstant.LESS_THAN;
	}
	
	/**
	 * 
	 * @param bd
	 * @return
	 */
	public static final boolean isNullOrZero(BigDecimal bd) {
		return bd == null || bd.compareTo(BigDecimal.ZERO) == ICompareResultConstant.EQUAL;
	}
	
	/**
	 * 
	 * convert:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param val
	 * @return  
	 * @since JDK 1.8
	 */
	public static final BigDecimal convert(String val) {
		return new BigDecimal( val == null || val.length() == 0 ? "0" : val );
	}
	
}
