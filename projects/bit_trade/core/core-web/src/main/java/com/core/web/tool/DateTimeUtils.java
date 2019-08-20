package com.core.web.tool;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ClassName: DateTimeUtils <br/>
 * Function: 时间工具类. <br/>
 * date: 2018年5月8日 下午5:58:59 <br/>
 * 
 * @author yangaobiao
 * @version
 * @since JDK 1.7
 */
public class DateTimeUtils {

	private static Logger logger = LoggerFactory.getLogger( DateTimeUtils.class );

	public static String formatDate(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat( format ); // yyyyMMddHHmmss
		return formatter.format( date );
	}

	/**
	 * 获取当前日期(默认:yyyyMMdd)
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		String pattern = "yyyyMMdd";
		return getCurrentDate( pattern );
	}

	/**
	 * 获取当前时分整型(默认:HHmm)
	 *
	 * @return
	 */
	public static Integer getMinuteSecondInt() {
		String pattern = "HHmm";
		String str = getCurrentDate( pattern );
		return Integer.parseInt(str);
	}

    /**
     * 获取当前时分秒整型(默认:HHmm)
     *
     * @return
     */
    public static Integer getHHmmssInt() {
        String pattern = "HHmmss";
        String str = getCurrentDate( pattern );
        return Integer.parseInt(str);
    }

    /**
     * 获取当前时分秒整型(默认:HHmm)
     *
     * @return
     */
    public static String getHHmmssStr() {
        String pattern = "HHmmss";
        return getCurrentDate( pattern );
    }

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getCurrentTime() {
		return new Date();
	}

	/**
	 * 获取当前的日期时间(默认:yyyy-MM-dd HH:mm:ss)
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		return dateFormat.format( Calendar.getInstance().getTime() );
	}

	/**
	 * 获取当前的日期时间(默认:yyyy-MM-dd HH:mm:00)
	 *
	 * @return
	 */
	public static Long getCurrentLong() {
		Calendar c = Calendar.getInstance();
		c.setTime( new Date() );
		c.add( Calendar.MINUTE, 1 );
		c.set( Calendar.SECOND, 0 );
		c.set( Calendar.MILLISECOND, 0 );
		return calTwoTimeDifferences(c.getTime(),getCurrentTime());
	}

	/**
	 * 获取当前的日期时间(默认:yyyyMMddHHmmsss)
	 * 
	 * @return
	 */
	public static String getCurrentFullDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyyMMddHHmmss" );
		return dateFormat.format( Calendar.getInstance().getTime() );
	}

	/**
	 * 获取当前的日期时间(默认:yyyy-MM-dd HH:mm:sss)
	 * 
	 * @return
	 */
	public static Timestamp getCurrentDateTimeStamp() {
		String currentDateTime = getCurrentDateTime();
		return Timestamp.valueOf( currentDateTime );
	}

	/**
	 * 获取当前日期
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getCurrentDate(String pattern) {
		Calendar calendar = Calendar.getInstance();
		return calendarToString( calendar, pattern );
	}

	/**
	 * 将String类型转换为Date类型
	 * 
	 * @param inputString
	 * @param pattern
	 * @return
	 */
	public static Date stringToDate(String inputString, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat( pattern );
		try {
			return dateFormat.parse( inputString );
		} catch (ParseException e) {
			String message = "格式化结束时间发生异常,传入的格式化参数为:" + pattern + ",参数为:" + inputString;
			logger.error( message + ",格式化异常信息:", e );
			return null;
		}
	}

	/**
	 * 将String类型转换为Calendar类型
	 * 
	 * @param inputString
	 * @param pattern
	 * @return
	 */
	public static Calendar stringToCalendar(String inputString, String pattern) {
		Date date = stringToDate( inputString, pattern );
		Calendar calendar = dateToCalendar( date );
		return calendar;
	}

	/**
	 * 将Date类型转换为String类型
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		return DateFormatUtils.format( date, pattern );
	}

	/**
	 * 将Date类型转换为Calendar类型
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( date );
		return calendar;
	}

	/**
	 * 将Calendar类型转换为Date类型
	 * 
	 * @param calendar
	 * @return
	 */
	public static Date calendarToDate(Calendar calendar) {
		return calendar.getTime();
	}

	/**
	 * 将Calendar类型转换为String类型
	 * 
	 * @param calendar
	 * @param pattern
	 * @return
	 */
	public static String calendarToString(Calendar calendar, String pattern) {
		Date date = calendar.getTime();
		return dateToString( date, pattern );
	}

	/**
	 * 格式化当天时间
	 * 
	 * @return
	 */
	public static String toCurrentDateTime(String pattern) {
		Date date = new Date();
		return dateToString( date, pattern );
	}

	/**
	 * 根据字符串时间获取结束时间
	 * @param pattern
	 * @return
	 */
	public static String getEndTime(String pattern) {
		Calendar calendar = Calendar.getInstance();
		calendar.set( Calendar.HOUR_OF_DAY, 23 );
		calendar.set( Calendar.MINUTE, 59 );
		calendar.set( Calendar.SECOND, 59 );
		calendar.set(Calendar.MILLISECOND, 999);
		SimpleDateFormat dateFormat = new SimpleDateFormat( pattern );
		return dateFormat.format( calendar.getTime() );
	}

	/**
	 * 根据时间获取结束时间
	 * @param pattern
	 * @return
	 */
	public static Date getEndTime(Date pattern) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(pattern);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

    /**
     * 根据时间获取开始时间
     * @param pattern
     * @return
     */
    public static Date getActinTime(Date pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(pattern);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

	/**
	 * 获取当月最后结束时间
	 * 
	 * @param pattern
	 * @return Date
	 */
	public static String getMonthEndTime(String pattern) {
		Calendar calendar = Calendar.getInstance();
		calendar.set( Calendar.DATE, calendar.getActualMaximum( Calendar.DATE ) );
		calendar.set( Calendar.HOUR_OF_DAY, 23 );
		calendar.set( Calendar.MINUTE, 59 );
		calendar.set( Calendar.SECOND, 59 );
		SimpleDateFormat dateFormat = new SimpleDateFormat( pattern );
		return dateFormat.format( calendar.getTime() );
	}


	/**
	 * 当月的第一天
	 */
	@Deprecated
	public static Date getMonthBegin(){
		Date d = new Date();
		int m = d.getMonth();
		int y = d.getYear();
		Date firstDay = new Date(y,m+1,1) ;
		int min = 24*60*60*1000;
		Date from = new Date(y,m,1);
		Date to = new Date(firstDay.getTime()-min);
		return from;
	}


	/**
	 * 获取当前日期的年月日
	 *
	 * @return
	 */
	public static Date getDays() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( new Date() );
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND, 0 );
		calendar.set( Calendar.MILLISECOND, 0 );
		final Date time = calendar.getTime();
		return time;
	}


	/**
	 * 获取当前日期的年月第一天
	 *
	 * @return
	 */
	public static Date getMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( new Date() );
		calendar.set(Calendar.DAY_OF_MONTH,1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND, 0 );
		calendar.set( Calendar.MILLISECOND, 0 );
		final Date time = calendar.getTime();
		return time;
	}

	/**
	 * 获取多少天后日期的年月日
	 *
	 * @return
	 */
	public static Date getDays(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( new Date() );
		calendar.add(Calendar.DATE,day);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND, 0 );
		calendar.set( Calendar.MILLISECOND, 0 );
		final Date time = calendar.getTime();
		return time;
	}

	/**
	 * 获取指定日期多少天后日期的年月日
	 * @param dayTime 指定日期
	 * @return
	 */
	public static Date getDays(Date dayTime,int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dayTime);
		calendar.add(Calendar.DATE,day);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND, 0 );
		calendar.set( Calendar.MILLISECOND, 0 );
		final Date time = calendar.getTime();
		return time;
	}

	/**
	 * 获取当前日期的年月日时
	 *
	 * @return
	 */
	public static Date getHours() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( new Date() );
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND, 0 );
		calendar.set( Calendar.MILLISECOND, 0 );
		final Date time = calendar.getTime();
		return time;
	}

	/**
	 * 获取当前日期的年月日时分
	 *
	 * @return
	 */
	public static Date getMinute() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( new Date() );
		calendar.set( Calendar.SECOND, 0 );
		calendar.set( Calendar.MILLISECOND, 0 );
		final Date time = calendar.getTime();
		return time;
	}

	/**
	 * getTimeYYYYMMDDHHMMSS:(时间戳转时间yyyy-MM-dd HH:mm:ss). <br/>
	 *
	 * @author yangaobiao
	 * @param time
	 * @return
	 * @since JDK 1.7
	 */
	public static String getTimeYYYYMMDDHHMMSS(Long time) {
		Date date = new Date( time );
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		return sdf.format( date );
	}

	/**
	 * getTimeYYYYMMDDHHMMSS:(时间戳转时间yyyy-MM-dd HH:mm). <br/>
	 *
	 * @author yangaobiao
	 * @param time
	 * @return
	 * @since JDK 1.7
	 */
	public static String getTimeYYYYMMDDHHMM(Long time) {
		Date date = new Date( time );
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
		return sdf.format( date );
	}

	/**
	 * daysBetween:(计算两个日期之间相差的天数). <br/>
	 * 
	 * @author yangaobiao
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 * @since JDK 1.7
	 */
	public static int daysBetween(Date smdate, Date bdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime( smdate );
		long time1 = cal.getTimeInMillis();
		cal.setTime( bdate );
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt( String.valueOf( between_days ) );
	}

	/**
	 * daysBetween:(计算两个日期之间相差的分钟数). <br/>
	 *
	 * @author yangaobiao
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 * @since JDK 1.7
	 */
	public static int minuteBetween(Date smdate, Date bdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime( smdate );
		long time1 = cal.getTimeInMillis();
		cal.setTime( bdate );
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 60);
		return Integer.parseInt( String.valueOf( between_days ) );
	}

	/**
	 * String类加减时间处理(转换为Date)
	 *
	 * @param inputString
	 *            需要操作的时间
	 * @param amount
	 *            需要操作的时间区间（正数：加时间，负数：减时间， 单位--分钟）
	 * @return
	 */
	public static Date addDateTimeToDate(Date inputString, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( inputString );
		calendar.add( Calendar.MINUTE, amount );
		final Date time = calendar.getTime();
		return time;
	}

	/**
	 * String类加减时间处理(转换为Date)
	 *
	 * @param inputString
	 *            需要操作的时间
	 * @param amount
	 *            需要操作的时间区间（正数：加时间，负数：减时间， 单位--分钟）
	 * @return
	 */
	public static Date addDateTimeToDate(int unit, Date inputString, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( inputString );
		calendar.add( unit, amount );
		final Date time = calendar.getTime();
		return time;
	}

	/**
	 * 时间加减，秒位清零
	 *
	 * @param inputString
	 *            需要操作的时间
	 * @param amount
	 *            需要操作的时间（正数：加天，负数：减天， 单位--天）
	 * @return
	 */
	public static Date getDayOperation(Date inputString, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( inputString );
		calendar.add( Calendar.HOUR_OF_DAY, amount );
		final Date time = calendar.getTime();
		return time;
	}

	/**
	 * 时间加减，秒位清零
	 *
	 * @param inputString
	 *            需要操作的时间
	 * @param amount
	 *            需要操作的时间区间（正数：加时间，负数：减时间， 单位--分钟）
	 * @return
	 */
	public static Date getTimeOperationSec(Date inputString, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( inputString );
		calendar.set( Calendar.SECOND, 0 );
		calendar.set( Calendar.MILLISECOND, 0 );
		calendar.add( Calendar.MINUTE, amount );
		final Date time = calendar.getTime();
		return time;
	}

	/**
	 * 时间加减，秒位清零_交割时间一分钟时处理办法
	 *
	 * @param inputString
	 *            需要操作的时间
	 * @param amount
	 *            需要操作的时间区间（正数：加时间，负数：减时间， 单位--分钟）
	 * @return
	 */
	public static Date getTimeOperationSec_one(Date inputString, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( new Date() );
		int minute = calendar.get( Calendar.SECOND );
		if (minute > 30) {
			calendar.add(Calendar.MINUTE, 1);
		}
		calendar.set(Calendar.SECOND, 0);
		calendar.set( Calendar.MILLISECOND, 0 );
		calendar.add( Calendar.MINUTE, 1 );
		final Date time = calendar.getTime();
		return time;
	}

	/**
	 * 时间秒位清零
	 *
	 * @param inputString
	 *            需要操作的时间
	 * @return
	 */
	public static Date getTimeSecond(Date inputString) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( inputString );
		calendar.set( Calendar.SECOND, 0 );
		calendar.set( Calendar.MILLISECOND, 0 );
		final Date time = calendar.getTime();
		return time;
	}

	/**
	 * 时分秒位清零
	 *
	 * @param inputString
	 *            需要操作的时间
	 * @return
	 */
	public static Date getTimeHours(Date inputString) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( inputString );
		calendar.set( Calendar.HOUR, 0 );
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND, 0 );
		calendar.set( Calendar.MILLISECOND, 0 );
		final Date time = calendar.getTime();
		return time;
	}

	/**
	 * 获取下一分钟并且秒位清零
	 *
	 * @param inputString
	 *            需要操作的时间
	 * @return
	 */
	public static Date getTimeSecond_minute(Date inputString) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( inputString );
		int minute = calendar.get( Calendar.MINUTE );
		calendar.set( Calendar.MINUTE, minute + 1 );
		calendar.set( Calendar.SECOND, 0 );
		final Date time = calendar.getTime();
		return time;
	}

	/**
	 * 对指定时间秒位做计算
	 *
	 * @param inputString
	 *            需要操作的时间
	 * @return
	 */
	public static Date getTime_second_calculate(Date inputString,int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( inputString );
		calendar.add( Calendar.SECOND, num );
		calendar.set( Calendar.MILLISECOND, 0 );
		final Date time = calendar.getTime();
		return time;
	}

	/**
	 * 获取上一分钟的最后一秒
	 *
	 * @param inputString
	 *            需要操作的时间
	 * @return
	 */
	public static Date getTime_last(Date inputString) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( inputString );
		calendar.set( Calendar.SECOND, -1 );
		final Date time = calendar.getTime();
		return time;
	}

	/**
	 * headDate:(取到 hours 以前时间). <br/>
	 * 
	 * @author fate
	 * @param date
	 * @param hours
	 * @return
	 * @throws ParseException
	 * @since JDK 1.7
	 */
	public static Date headDate(String date, int hours) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).parse( date ) );
		cal.add( Calendar.HOUR_OF_DAY, hours );
		return cal.getTime();
	}

	/**
	 * calTwoTimeDifferences:(计算两个时间相差多少秒). <br/>
	 * 
	 * @author RLY
	 * @param time1
	 * @param time2
	 * @return
	 * @since JDK 1.7
	 */
	public static long calTwoTimeDifferences(Date time1, Date time2) {
		return (time1.getTime() - time2.getTime()) / 1000;
	}

	/**
	 * @Description: 获取今天开始时间
	 * @Author: zjun
	 * @Date: 2019/6/20 10:34
	 */
	public static Long getStartTimeOfToday() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime().getTime();
	}

	/**
	 * @Description: 获取昨天结束时间
	 * @Author: zjun
	 * @Date: 2019/6/20 10:34
	 */
	public static Date getEndTimeOfYesterday() {
		Calendar c = Calendar.getInstance();
		c.setTime( new Date() );
		c.add( Calendar.DAY_OF_MONTH, -1 );
		c.set( Calendar.HOUR_OF_DAY, 23 );
		c.set( Calendar.MINUTE, 59 );
		c.set( Calendar.SECOND, 59 );
		c.set( Calendar.MILLISECOND, 999 );
		// 昨天的结束时间
		Date endTime = c.getTime();
		return endTime;
	}

	/**
	 * 时间加减 毫秒清0
	 *
	 * @param inputString
	 *            需要操作的时间
	 * @param amount
	 *            需要操作的时间区间（正数：加时间，负数：减时间， 单位--秒）
	 * @return
	 */
	public static Date getTimeSecondOperationSec(Date inputString, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( inputString );
		calendar.set(Calendar.MILLISECOND,0);
		calendar.add( Calendar.SECOND, amount );
		final Date time = calendar.getTime();
		return time;
	}

	public static void main(String[] args) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime( new Date() );
//		calendar.set( Calendar.SECOND, 0 );
//		calendar.add( Calendar.MINUTE, 20 );
//		final Date time = calendar.getTime();
//		System.out.println( time );
//		Date now=new Date();
//		System.out.println(now.getTime());
//		System.out.println(getTimeSecondOperationSec(now,-12*3600).getTime());
		Date utcTime = DateTimeUtils.stringToDate( "2019-05-25 00:00:00", "yyyy-MM-dd HH:mm:ss" );// utc时间
		System.out.println(utcTime.getTime());


      Date date =  getActinTime(new Date());
      System.out.println("今天的开始时间 ：  "+new Date());

	}
}
