package com.core.tool;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * @author Administrator
 *
 */
public class BeanUtil {
	
	/**
	 * 
	 * @param src
	 * @param dest
	 */
	public static final void copyObj(Object src, Object dest) {
		try {
			BeanUtils.copyProperties(dest, src);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
}
