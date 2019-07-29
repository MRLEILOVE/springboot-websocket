package com.td.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler( "/html/**" ).addResourceLocations( ResourceUtils.CLASSPATH_URL_PREFIX + "/html/" );
//		registry.addResourceHandler( "/static/**" ).addResourceLocations( "classpath:/static/" );
//		registry.addResourceHandler( "/templates/**" ).addResourceLocations( "classpath:/templates/" );
//		// 放行swagger
//		registry.addResourceHandler( "swagger-ui.html" ).addResourceLocations( "classpath:/META-INF/resources/" );
//		registry.addResourceHandler( "/webjars/**" ).addResourceLocations( "classpath:/META-INF/resources/webjars/" );
	}
}
