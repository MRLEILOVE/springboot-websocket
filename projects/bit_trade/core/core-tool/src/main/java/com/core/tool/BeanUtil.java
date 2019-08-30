package com.core.tool;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.cglib.beans.BeanCopier;

/**
 * 
 * @author Administrator
 *
 */
public class BeanUtil {

	/*
		注： 
		(1)相同属性名，且类型不匹配时候的处理，ok，但是未满足的属性不拷贝； 
		(2)get和set方法不匹配的处理，创建拷贝的时候报错，无法拷贝任何属性(当且仅当sourceClass的get方法超过set方法时出现) 
		(3)BeanCopier  
		初始化例子：BeanCopier copier = BeanCopier.create(Source.class, Target.class, useConverter=true) 
		第三个参数userConverter,是否开启Convert,默认BeanCopier只会做同名，同类型属性的copier,否则就会报错. 
		copier = BeanCopier.create(source.getClass(), target.getClass(), false); 
		copier.copy(source, target, null); 
		(4)修复beanCopier对set方法强限制的约束 
		改写net.sf.cglib.beans.BeanCopier.Generator.generateClass(ClassVisitor)方法 
		将133行的 
		MethodInfo write = ReflectUtils.getMethodInfo(setter.getWriteMethod()); 
		预先存一个names2放入 
		 109        Map names2 = new HashMap(); 
		 110        for (int i = 0; i < getters.length; ++i) { 
		 111          names2.put(setters[i].getName(), getters[i]); 
					} 
		调用这行代码前判断查询下，如果没有改writeMethod则忽略掉该字段的操作，这样就可以避免异常的发生。
    */
	
	private static final Map<String, BeanCopier> MAP__BEAN_COPIER = new HashMap<>();
	
	private static final BeanCopier getBeanCopier(Object source, Object target) {
		String str_key = source.getClass()/*.toString()*/ + "," + target.getClass()/*.toString()*/;
		if (MAP__BEAN_COPIER.containsKey(str_key)) {
			return MAP__BEAN_COPIER.get(str_key);
		} else {
			BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
			MAP__BEAN_COPIER.put(str_key, beanCopier);
			return beanCopier;
		}
	}
	
	/**
	 * 
	 * @param src
	 * @param dst
	 */
	public static final void copyObj(Object src, Object dst) {
		try {
//			org.apache.commons.beanutils.BeanUtils.copyProperties(dst, src);
//			org.apache.commons.beanutils.PropertyUtils.copyProperties(dst, src);
//			cn.hutool.core.bean.BeanUtil.copyProperties(src, dst);
			getBeanCopier(src, dst).copy(src, dst, null);
//		} catch (java.lang.IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (java.lang.reflect.InvocationTargetException e) {
//			e.printStackTrace();
//		} catch (java.lang.NoSuchMethodException e) {
//			e.printStackTrace();
		} finally {
			
		}
	}
	
	/**
	 * 
	 * @param <T>
	 * @param src
	 * @param dstCls
	 * @return
	 */
	public static final <T> T copyObj(Object src, Class<T> dstCls) {
		T dst = null;
		
		try {
			dst = dstCls/*.getDeclaredConstructor()*/.newInstance();
			
			copyObj(src, dst);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return dst;
	}
	
	/** Bean方法名中属性名开始的下标 */
	private static final int		BEAN_METHOD_PROP_INDEX	= 3;

	/** * 匹配getter方法的正则表达式 */
	private static final Pattern	GET_PATTERN				= Pattern.compile( "get(\\p{javaUpperCase}\\w*)" );

	/** * 匹配setter方法的正则表达式 */
	private static final Pattern	SET_PATTERN				= Pattern.compile( "set(\\p{javaUpperCase}\\w*)" );

	/**
	 * Bean属性复制工具方法。
	 * 
	 * @param dst
	 *            目标对象
	 * @param src
	 *            源对象
	 */
	public static void copyBeanProp(Object dst, Object src) {
		List<Method> dstSetters = getSetterMethods( dst );
		List<Method> srcGetters = getGetterMethods( src );
		try {
			for (Method setter : dstSetters) {
				for (Method getter : srcGetters) {
					if (isMethodPropEquals( setter.getName(), getter.getName() )
							&& setter.getParameterTypes()[ 0 ].equals( getter.getReturnType() )) {
						setter.invoke( dst, getter.invoke( src ) );
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取对象的setter方法。
	 * 
	 * @param obj
	 *            对象
	 * @return 对象的setter方法列表
	 */
	public static List<Method> getSetterMethods(Object obj) {
		// setter方法列表
		List<Method> setterMethods = new ArrayList<Method>();

		// 获取所有方法
		Method[] methods = obj.getClass().getMethods();

		// 查找setter方法

		for (Method method : methods) {
			Matcher m = SET_PATTERN.matcher( method.getName() );
			if (m.matches() && (method.getParameterTypes().length == 1)) {
				setterMethods.add( method );
			}
		}
		// 返回setter方法列表
		return setterMethods;
	}

	/**
	 * 获取对象的getter方法。
	 * 
	 * @param obj
	 *            对象
	 * @return 对象的getter方法列表
	 */

	public static List<Method> getGetterMethods(Object obj) {
		// getter方法列表
		List<Method> getterMethods = new ArrayList<Method>();
		// 获取所有方法
		Method[] methods = obj.getClass().getMethods();
		// 查找getter方法
		for (Method method : methods) {
			Matcher m = GET_PATTERN.matcher( method.getName() );
			if (m.matches() && (method.getParameterTypes().length == 0)) {
				getterMethods.add( method );
			}
		}
		// 返回getter方法列表
		return getterMethods;
	}

	/**
	 * 检查Bean方法名中的属性名是否相等。<br>
	 * 如getName()和setName()属性名一样，getName()和setAge()属性名不一样。
	 * 
	 * @param m1
	 *            方法名1
	 * @param m2
	 *            方法名2
	 * @return 属性名一样返回true，否则返回false
	 */

	public static boolean isMethodPropEquals(String m1, String m2) {
		return m1.substring( BEAN_METHOD_PROP_INDEX ).equals( m2.substring( BEAN_METHOD_PROP_INDEX ) );
	}
	
}
