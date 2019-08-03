/**  
 * Project Name:core-tool  
 * File Name:DateUtil.java  
 * Package Name:com.core.tool  
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/

package com.core.tool;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.core.common.constant.IConstant;

/**
 * <p>
 *   日期时间实用类
 * </p>
 * ClassName:DateTimeUtil <br/>
 * Description: TODO 添加描述. <br/>
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * 
 * @author Administrator
 * @version
 * @since JDK 1.8
 * @see
 */
public class DateTimeUtil {
	
	private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern(IConstant.DATETIME_PATTERN);
	
	/**
	 * 
	 * toString:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param dateTime
	 * @return  
	 * @since JDK 1.8
	 */
	public static final String toString(LocalDateTime dateTime) {
		return DTF.format(dateTime);
	}
	
	/**
	 * 
	 * getBeginMinute:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param dateTime
	 * @return  
	 * @since JDK 1.8
	 */
	public static final LocalDateTime getMinuteBegin(LocalDateTime dateTime) {
//		Date dt = new Date(date.getTime());
//		dt.setSeconds( 0 );
//		return dt;
		
		dateTime = dateTime.truncatedTo( ChronoUnit.MINUTES );
		
		return dateTime;
	}

	private static final int getRangeBegin(int val, int range) {
		while (val % range >0) {
			val--;
		}
		return val;
	}
	
	/**
	 * <p>
	 *   得到分的开始
	 * </p>
	 * getMinuteBegin:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param dt
	 * @param minutes
	 * @return  
	 * @since JDK 1.8
	 */
	public static final LocalDateTime getMinuteBegin(LocalDateTime dt, int minutes) {
		int i_rangeBegin = getRangeBegin(dt.getMinute(), minutes);
		
		LocalDateTime dt_ret = LocalDateTime.of( dt.getYear(), dt.getMonth(), dt.getDayOfMonth(), dt.getHour(), i_rangeBegin, 0, 0 );
		
		return dt_ret;
	}
	
	/**
	 * <p>
	 *   得到时的开始
	 * </p>
	 * getMinuteBegin:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param dt
	 * @param hours
	 * @return  
	 * @since JDK 1.8
	 */
	public static final LocalDateTime getHourBegin(LocalDateTime dt, int hours) {
		int i_rangeBegin = getRangeBegin(dt.getHour(), hours);
		
		LocalDateTime dt_ret = LocalDateTime.of( dt.getYear(), dt.getMonth(), dt.getDayOfMonth(), i_rangeBegin, 0, 0, 0 );
		
		return dt_ret;
	}

	/**
	 * <p>
	 *   得到天的开始
	 * </p>
	 * getDayBegin:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param dt
	 * @param days
	 * @return  
	 * @since JDK 1.8
	 */
	public static final LocalDateTime getDayBegin(LocalDateTime dt, int days) {
		int i_rangeBegin = getRangeBegin(dt.getDayOfMonth(), days);
		
		LocalDateTime dt_ret = LocalDateTime.of( dt.getYear(), dt.getMonth(), i_rangeBegin + 1, 0, 0, 0, 0 );
		
		return dt_ret;
	}

	/**
	 * isoToUtc:(iso转utc). <br/>
	 * 
	 * @author zale
	 * @param UTC
	 * @throws ParseException
	 */
	public static final LocalDateTime _UTC2ISO(String UTC) /*throws ParseException */{
//		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" );
//		format.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
//		return format.parse( UTC );
		
//		return LocalDateTime.parse( UTC, DateTimeFormatter.ofPattern(PATTERN).withZone(ZoneOffset.UTC) ); // DateTimeFormatter.ISO_DATE_TIME

//		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN).withZone(ZoneOffset.UTC);
//		LocalDateTime localDateTime = LocalDateTime.parse(UTC, dateTimeFormatter);
//		ZonedDateTime utcZonedDateTime = ZonedDateTime.of(localDateTime, ZoneOffset.UTC);
//		ZoneOffset localZoneOffset = java.time.OffsetDateTime.now().getOffset();
//		ZonedDateTime localZonedDateTime = utcZonedDateTime.withZoneSameInstant(localZoneOffset);
//		return localZonedDateTime;
		
		Instant instant = Instant.parse( UTC );
//		ZonedDateTime zdt = ZonedDateTime.ofInstant( instant, ZoneId.systemDefault() ); // ZoneOffset.UTC instant.atZone(ZoneOffset.UTC);
//		return zdt.toLocalDateTime();
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}
	
	private static final class Tester {
		private static final void test_1() {
			LocalDateTime dt = LocalDateTime.now();
			System.out.println( "1 dt=" + DateTimeUtil.toString(dt) );
			LocalDateTime dt_2 = getMinuteBegin(dt);
			System.out.println( "2 dt=" + DateTimeUtil.toString(dt) );
			System.out.println( "2 dt_2=" + DateTimeUtil.toString(dt_2) );
			System.out.println( dt == dt_2 );
			System.out.println( dt_2.compareTo( dt ) );
		}
		
		private static final void test_2() {
			System.out.println( _UTC2ISO("2019-07-19T15:06:27.561Z") );
		}
	};
	
	public static void _main(String[] args) {
//		Tester.test_1();
//		Tester.test_2();
		
//		LocalDateTime ldt = LocalDateTime.now();
//		System.out.println( toString(ldt) );
//		System.out.println(  );
//		System.out.println( toString(getMinuteBegin(ldt)) );
//		System.out.println( toString(getMinuteBegin(ldt, 3)) );
//		System.out.println( toString(getMinuteBegin(ldt, 5)) );
//		System.out.println( toString(getMinuteBegin(ldt, 15)) );
//		System.out.println(  );
//		System.out.println( toString(getHourBegin(ldt, 15)) );
//		System.out.println( toString(getHourBegin(ldt, 4)) );
	}
	
}
