package com.bittrade.currency.conf;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.core.common.constant.IConstant;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * 跨域配置
	 * 
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 设置允许跨域的路径
		registry.addMapping( "/**" )
				// 设置允许跨域请求的域名
				.allowedOrigins( "*" )
				// 是否允许证书 不再默认开启
				.allowCredentials( true )
				// 设置允许的方法
				.allowedMethods( "GET", "POST" )
				// 跨域允许时间
				.maxAge( 3600 );
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler( "/static/**" ).addResourceLocations( "classpath:/static/" );
		registry.addResourceHandler( "/templates/**" ).addResourceLocations( "classpath:/templates/" );
		// 放行swagger
		registry.addResourceHandler( "swagger-ui.html" ).addResourceLocations( "classpath:/META-INF/resources/" );
		registry.addResourceHandler( "/webjars/**" ).addResourceLocations( "classpath:/META-INF/resources/webjars/" );
	}

	/**
	 * 添加json转换
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// 1.定义一个Converter 转换消息
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		// 2.添加fastjson配置信息, 比如 是否格式化数据
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures( SerializerFeature.PrettyFormat );
		fastJsonConfig.setDateFormat( IConstant.DATETIME_PATTERN );
		// 3.在Converter中添加配置信息
		fastConverter.setFastJsonConfig( fastJsonConfig );
		// 4.将Converter 添加到 List converters当中
		converters.add( fastConverter );
	}

}
