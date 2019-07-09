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
	 * @param bd
	 * @return
	 */
	public static final boolean isNullOrZero(BigDecimal bd) {
		return bd == null || bd.compareTo(BigDecimal.ZERO) == ICompareResultConstant.EQUAL;
	}
	
}
