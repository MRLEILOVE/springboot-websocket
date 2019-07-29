package com.td.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@org.springframework.web.servlet.config.annotation.EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {

	public SwaggerConfig() {
		super();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.contact(new Contact("Swagger测试","localhost:8080/ruleengine/swagger-ui.html","baidu@qq.com"))
				.title("开放接口API")  //粗标题
				.description("HTTP对外开放接口")   //描述
				.version("1.0.0")   //api version
				.termsOfServiceUrl("http://xxx.xxx.com")
				.license("LICENSE")   //链接名称
				.licenseUrl("http://xxx.xxx.com")   //链接地址
				.build();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())  //显示所有类 RequestHandlerSelectors.basePackage("com.example")
				//.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))  //只显示添加@Api注解的类
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

}
