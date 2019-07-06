//package com.td.config;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.shiro.authz.UnauthorizedException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//
//
//
///**
// *  shiro权限异常处理
// */
//public class MyExceptionResolver implements HandlerExceptionResolver{
//
//    private static final Logger log = LoggerFactory.getLogger(MyExceptionResolver.class);
//
//    public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
//        System.out.println("==============异常处理=============");
//        //如果是shiro无权操作，因为shiro 在操作auno等一部分不进行转发至无权限url
//        if(ex instanceof UnauthorizedException){
//            ModelAndView mv = new ModelAndView("permission");
//            return mv;
//        }
//        ex.printStackTrace();
//        ModelAndView mv = new ModelAndView("permission");
//        log.info("错误信息： "+ex.toString());
////        mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
//        return mv;
//    }
//
//}