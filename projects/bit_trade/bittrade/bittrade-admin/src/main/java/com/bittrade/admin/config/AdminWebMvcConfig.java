//package com.bittrade.admin.config;
//
//import com.jdcloud.core.conf.RedisConfiguration;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.util.ResourceUtils;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * web mvc配置
// * 
// * @author ourblue
// *
// */
//@Configuration
//@Import({ RedisConfiguration.class })
//public class AdminWebMvcConfig extends WebMvcConfigurerAdapter {
//
//	@Value("${shiro.user.indexUrl}")
//	private String	indexUrl;
//
//	@Value("${user.file.profile}")
//	private String	profile;
//
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController( "/" ).setViewName( "forward:" + indexUrl );
//	}
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		// 文件上传路径
//		registry.addResourceHandler( "/profile/**" ).addResourceLocations( ResourceUtils.FILE_URL_PREFIX + profile );
//	}
//
//}
