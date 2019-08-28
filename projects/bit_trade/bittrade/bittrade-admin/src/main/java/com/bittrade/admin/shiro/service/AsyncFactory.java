package com.bittrade.admin.shiro.service;

import java.util.Date;

import com.bittrade.admin.enums.UserEnum.UserState;
import com.bittrade.admin.model.domain.SysLogininfor;
import com.bittrade.admin.model.domain.SysOperLog;
import com.bittrade.admin.model.domain.SysUserOnline;
import com.bittrade.admin.service.impl.sys.SysLogininforServiceImpl;
import com.bittrade.admin.service.impl.sys.SysUserOnlineServiceImpl;
import com.bittrade.admin.service.sys.SysOperLogService;
import com.bittrade.admin.shiro.session.OnlineSession;
import com.bittrade.admin.util.SpringUtil;
import com.core.common.constant.GlobalConstant.Sys;
import com.core.tool.AddressUtil;
import com.core.tool.ConvertUtil;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * .异步任务
 * 
 * @author who ?
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
		online.setLoginLocation( AddressUtil.getRealAddressByIP( session.getHost() ) );
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
		operLog.setOperLocation( AddressUtil.getRealAddressByIP( operLog.getOperIp() ) );
		SpringUtil.getBean( SysOperLogService.class ).save( operLog );
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
		logininfor.setLoginLocation( AddressUtil.getRealAddressByIP( ip ) );
		logininfor.setBrowser( browser );
		logininfor.setLoginTime( new Date() );
		logininfor.setOs( os );
		logininfor.setMsg( message );
		// 日志状态
		if (Sys.LOGIN_FAIL.equals( status )) {
			logininfor.setStatus( UserState.LOCK_USER.getCode() + "" );
		}
		SpringUtil.getBean( SysLogininforServiceImpl.class ).save( logininfor );
	}

}
