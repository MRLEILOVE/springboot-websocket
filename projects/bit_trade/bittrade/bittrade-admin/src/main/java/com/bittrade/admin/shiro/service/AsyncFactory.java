package com.bittrade.admin.shiro.service;

import java.util.Date;
import com.jdcloud.base.constant.GlobalConstant.Sys;
import com.jdcloud.base.enums.UserEnum.UserState;
import com.jdcloud.core.support.SpringUtils;
import com.jdcloud.provider.pojo.SysLogininfor;
import com.jdcloud.provider.pojo.SysOperLog;
import com.jdcloud.provider.pojo.SysUserOnline;
import com.jdcloud.provider.service.SysOperLogService;
import com.jdcloud.provider.service.impl.SysLogininforServiceImpl;
import com.jdcloud.provider.service.impl.SysUserOnlineServiceImpl;
import com.jdcloud.provider.shiro.session.OnlineSession;
import com.jdcloud.util.Convert;
import com.jdcloud.util.http.AddressUtils;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * .异步任务
 * 
 * @author ourblue
 *
 */
public class AsyncFactory {

	/**
	 * 同步session到数据库
	 * 
	 * @param session
	 * @return 任务task
	 */
	public static void syncSessionToDb(final OnlineSession session) {
		SysUserOnline online = new SysUserOnline();
		online.setSessionId( String.valueOf( session.getId() ) );
		online.setDeptName( session.getDeptName() );
		online.setLoginName( session.getLoginName() );
		online.setStartTimestamp( session.getStartTimestamp() );
		online.setLastAccessTime( session.getLastAccessTime() );
		online.setExpireTime( ConvertUtil.toInt( session.getTimeout() ) );
		online.setIpaddr( session.getHost() );
		online.setLoginLocation( AddressUtils.getRealAddressByIP( session.getHost() ) );
		online.setBrowser( session.getBrowser() );
		online.setOs( session.getOs() );
		online.setStatus( session.getStatus().toString() );
		SpringUtil.getBean( SysUserOnlineServiceImpl.class ).saveOnline( online );
	}

	/**
	 * .操作日志记录
	 * 
	 * @param operLog
	 *            操作日志信息
	 * @return 任务task
	 */
	public static void recordOper(final SysOperLog operLog) {
		// 远程查询操作地点
		operLog.setOperLocation( AddressUtils.getRealAddressByIP( operLog.getOperIp() ) );
		SpringUtil.getBean( SysOperLogService.class ).insert( operLog );
	}

	/**
	 * .登录信息保存
	 * 
	 * @param username
	 * @param status
	 * @param message
	 * @param args
	 */
	public static void recordLoginInfo(final String ip, final UserAgent userAgent, final String username, final String status, final String message,
			final Object... args) {
		// 获取客户端操作系统
		String os = userAgent.getOperatingSystem().getName();
		// 获取客户端浏览器
		String browser = userAgent.getBrowser().getName();
		SysLogininfor logininfor = new SysLogininfor();
		logininfor.setLoginName( username );
		logininfor.setIpaddr( ip );
		logininfor.setLoginLocation( AddressUtils.getRealAddressByIP( ip ) );
		logininfor.setBrowser( browser );
		logininfor.setLoginTime( new Date() );
		logininfor.setOs( os );
		logininfor.setMsg( message );
		// 日志状态
		if (Sys.LOGIN_FAIL.equals( status )) {
			logininfor.setStatus( UserState.LOCK_USER.getCode() + "" );
		}
		SpringUtil.getBean( SysLogininforServiceImpl.class ).insert( logininfor );
	}

}
