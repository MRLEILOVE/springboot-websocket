package com.bittrade.admin.aspectj;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.bittrade.admin.annotation.Log;
import com.bittrade.admin.enums.UserEnum;
import com.bittrade.admin.model.domain.SysOperLog;
import com.bittrade.admin.model.domain.SysUser;
import com.bittrade.admin.service.sys.SysOperLogService;
import com.bittrade.admin.util.JSONUtil;
import com.bittrade.admin.util.ServletUtil;
import com.bittrade.admin.util.ShiroUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * annotation:Log =>aspect
 * 
 * @author who ?
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

	@Autowired
	private TaskExecutor		taskExecutor;
	@Autowired
	private SysOperLogService	sysOperLogService;

	@Pointcut("@annotation(com.bittrade.admin.annotation.Log)")
	public void logPointCut() {
	}

	@AfterReturning(pointcut = "logPointCut()")
	public void doBefore(JoinPoint joinPoint) {
		handleLog( joinPoint, null );
	}

	@AfterThrowing(value = "logPointCut()", throwing = "e")
	public void doAfter(JoinPoint joinPoint, Exception e) {
		handleLog( joinPoint, e );
	}
	
	/**
	 * 处理日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	protected void handleLog(final JoinPoint joinPoint, final Exception e) {
		try {
			Log controllerLog = getAnnotationLog( joinPoint );
			if (controllerLog == null) {
				return;
			}
			// 获取当前的用户
			SysUser currentUser = ShiroUtil.getUser();
			SysOperLog operLog = new SysOperLog();
			operLog.setStatus( UserEnum.UserState.NORMAL_USER.getCode() );
			String ip = ShiroUtil.getIp();
			operLog.setOperIp( ip );
			operLog.setOperTime( new Date() );
			operLog.setOperUrl( ServletUtil.getRequest().getRequestURI() );
			if (currentUser != null) {
				operLog.setOperName( currentUser.getLoginName() );
				if (null != currentUser.getDept() && StringUtils.isNotEmpty( currentUser.getDept().getDeptName() )) {
					operLog.setDeptName( currentUser.getDept().getDeptName() );
				}
			}
			if (e != null) {
				operLog.setStatus( UserEnum.UserState.LOCK_USER.getCode() );
				operLog.setErrorMsg( StringUtils.substring( e.getMessage(), 0, 2000 ) );
			}
			// 设置方法名称
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			operLog.setMethod( className + "." + methodName + "()" );
			// 处理注解参数
			getControllerMethodDescription( controllerLog, operLog );
			taskExecutor.execute( () -> {
				sysOperLogService.save( operLog );
			} );
		} catch (Exception e2) {
			log.error( "<== log拦截异常" + e2.getMessage() );
		}

	}
	
	/**
	 * .获取注解中方法的描述信息
	 * 
	 * @param log
	 * @param operLog
	 * @throws Exception
	 */
	public void getControllerMethodDescription(Log log, SysOperLog operLog) throws Exception {
		// 设置action动作
        operLog.setBusinessType(log.buinessType().ordinal());
        // 设置标题
        operLog.setTitle(log.title());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData())
        {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(operLog);
        }
	}
	
	 /**
     * 获取请求的参数，放到log中
     * 
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(SysOperLog operLog) throws Exception
    {
        Map<String, String[]> map = ServletUtil.getRequest().getParameterMap();
        String params = JSONUtil.marshal(map);
        operLog.setOperParam(StringUtils.substring(params, 0, 255));
    }

	/**
	 * .是否存在注解，如果存在就获取
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		if (method != null) {
			return method.getAnnotation( Log.class );
		}
		return null;
	}

}