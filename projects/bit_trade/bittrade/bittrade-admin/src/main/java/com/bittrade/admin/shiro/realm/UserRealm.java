package com.bittrade.admin.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.bittrade.admin.exception.CaptchaException;
import com.bittrade.admin.exception.RoleBlockedException;
import com.bittrade.admin.exception.UserBlockedException;
import com.bittrade.admin.exception.UserNotExistsException;
import com.bittrade.admin.exception.UserPasswordNotMatchException;
import com.bittrade.admin.exception.UserPasswordRetryLimitExceedException;
import com.bittrade.admin.service.sys.SysMenuService;
import com.bittrade.admin.service.sys.SysRoleService;
import com.bittrade.admin.shiro.service.LoginService;
import com.bittrade.admin.util.ShiroUtil;
import com.bittrade.pojo.model.SysUser;
import com.core.common.constant.GlobalConstant.Sys;

import lombok.extern.slf4j.Slf4j;

/**
 * realm shiro
 * 
 * @author who ?
 *
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private LoginService	loginService;

	@Autowired
	private SysRoleService	sysRoleService;

	@Autowired
	private SysMenuService	sysMenuService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		SysUser user = ShiroUtil.getUser();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 如果是唯一ADMIN,解锁所有姿势
		if (user.getLoginName().equals( Sys.ADMIN ) ) {
			info.addRole( Sys.ADMIN );
			info.addStringPermission( Sys.ALL_PERMISSION );
		} else {
			// 角色
			info.setRoles( sysRoleService.selectRoleKeys( user.getUserId() ) );
			// 权限
			info.setStringPermissions( sysMenuService.selectPermsByUserId( user.getUserId() ) );
		}
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
		String username = uptoken.getUsername();
		String password = uptoken.getPassword() == null ? "" : new String( uptoken.getPassword() );
		SysUser user = null;

		try {
			user = loginService.login( username, password );
		} catch (CaptchaException e) {
			throw new AuthenticationException( e.getMessage(), e );

		} catch (UserNotExistsException e) {
			throw new UnknownAccountException( e.getMessage(), e );

		} catch (UserPasswordNotMatchException e) {
			throw new IncorrectCredentialsException( e.getMessage(), e );

		} catch (UserPasswordRetryLimitExceedException e) {
			throw new ExcessiveAttemptsException( e.getMessage(), e );

		} catch (UserBlockedException e) {
			throw new LockedAccountException( e.getMessage(), e );

		} catch (RoleBlockedException e) {
			throw new LockedAccountException( e.getMessage(), e );

		} catch (Exception e) {
			log.info( "==>对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage() );
			throw new AuthenticationException( e.getMessage(), e );
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo( user, password, getName() );
		return info;
	}

	public void clearCachedAuthorizationInfo() {
		this.clearCachedAuthorizationInfo( SecurityUtils.getSubject().getPrincipals() );
	}

}