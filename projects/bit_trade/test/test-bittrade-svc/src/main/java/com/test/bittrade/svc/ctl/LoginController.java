//package com.test.bittrade.svc.ctl;
//
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.baomidou.mybatisplus.extension.api.R;
//import com.google.code.kaptcha.impl.DefaultKaptcha;
//
//@Controller
//public class LoginController {
// 
//    @Autowired
//    private AuthenticationManager myAuthenticationManager;
// 
//    @Autowired
//    DefaultKaptcha defaultKaptcha;
// 
//    @RequestMapping(value = "/userLogin")
//    public String userLogin(HttpServletRequest request) {
// 
//        User userInfo = new User();
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String kaptcha = request.getParameter("kaptcha");
// 
//        userInfo.setUsername(username);
//        userInfo.setPassword(password);
//        String s = request.getSession().getAttribute(ConstantVal.CHECK_CODE).toString();
//        if (StringUtils.isEmpty(kaptcha) || !s.equals(kaptcha)) {
//            return "redirect:login-error?error=1";
//        }
// 
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
// 
// 
//        try{
//            //使用SpringSecurity拦截登陆请求 进行认证和授权
//            Authentication authenticate = myAuthenticationManager.authenticate(usernamePasswordAuthenticationToken);
// 
//            SecurityContextHolder.getContext().setAuthentication(authenticate);
//            //使用redis session共享
//            HttpSession session = request.getSession();
//            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆
//        }catch (Exception e){
//            e.printStackTrace();
//            return "redirect:login-error?error=2";
//        }
// 
// 
//        return "redirect:index";
//    }
// 
// 
//    @RequestMapping("/captcha.jpg")
//    @ResponseBody
//    public R applyCheckCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        R r = new R();
// 
//        response.setHeader("Cache-Control", "no-store, no-cache");
//        response.setContentType("image/jpeg");
// 
//        //生成文字验证码
//        String text = defaultKaptcha.createText();
//        //生成图片验证码
//        BufferedImage image = defaultKaptcha.createImage(text);
//        //保存到session
//        request.getSession().setAttribute(ConstantVal.CHECK_CODE, text);
//        ServletOutputStream out = response.getOutputStream();
//        ImageIO.write(image, "jpg", out);
//        return r;
//    }
//}---------------------作者：别等时光染了梦想 来源：CSDN 原文：https:// blog.csdn.net/wangxueqing52/article/details/82628407
//版权声明：本文为博主原创文章，转载请附上博文链接！