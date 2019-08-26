package com.jdcloud.provider.web.sys;

import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.http.ServletUtils;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 
 * @author ourblue
 *
 */
@Controller
public class SysLoginController extends BaseController {

	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		if (ServletUtil.isAjaxRequest( request )) {
			return ServletUtil.renderString( response, "{\"code\":\"1\",\"message\":\"未登录或登录超时，请重新登录\"}" );
		}
		return "login";
	}

	@PostMapping("/login")
	@ResponseBody
	public Wrapper<String> ajaxLogin(String username, String password, Boolean rememberMe) {
		UsernamePasswordToken token = new UsernamePasswordToken( username, password, rememberMe );
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login( token );
			return success();
		} catch (AuthenticationException e) {
			return error( Optional.ofNullable( e.getMessage() ).orElse( "用户或密码错误" ) );
		}
	}

	@GetMapping("/unauth")
	public String unauth() {
		return "/error/unauth";
	}

}
