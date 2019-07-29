//package com.td.config;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//
//import com.google.common.collect.Lists;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.OAuthBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.GrantType;
//import springfox.documentation.service.OAuth;
//import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.service.SecurityScheme;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.web.DocExpansion;
//import springfox.documentation.swagger.web.ModelRendering;
//import springfox.documentation.swagger.web.OperationsSorter;
//import springfox.documentation.swagger.web.SecurityConfiguration;
//import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
//import springfox.documentation.swagger.web.TagsSorter;
//import springfox.documentation.swagger.web.UiConfiguration;
//import springfox.documentation.swagger.web.UiConfigurationBuilder;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
////@org.springframework.web.servlet.config.annotation.EnableWebMvc
//@EnableSwagger2
//public class SwaggerConfig22 {
//
//	// 这个东西是项目的根路径，也就是“/oauth/token”前面的那一串
//	// 这个东西在配置文件里写的，大家也可以直接写死在配置文件中
//	private String AUTH_SERVER = "http://localhost:8080/oauth/token";
//
//	/**
//	 * 主要是这个方法，其他的方法是抽出去的，所以大家不要害怕为啥有这么多方法 在 basePackage 里面写需要生成文档的 controller 路径
//	 */
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.any()/*.basePackage("com")*/).paths(PathSelectors.any())
//				.build().apiInfo(apiInfo()).securitySchemes(Collections.singletonList(securityScheme()))
//				.securityContexts(Collections.singletonList(securityContext()));
//	}
//
//	/**
//	 * 这个方法主要是写一些文档的描述
//	 */
//	private ApiInfo apiInfo() {
//		return new ApiInfo("某某系统API", "This is a very pretty document!", "1.0", "",
//				new Contact("师父领进门", "", "qixiazhen@qq.com"), "", "", Collections.emptyList());
//	}
//
//	/**
//	 * 这个类决定了你使用哪种认证方式，我这里使用密码模式 其他方式自己摸索一下，完全莫问题啊
//	 */
//	private SecurityScheme securityScheme() {
//		GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(AUTH_SERVER );
//
//		return new OAuthBuilder().name("spring_oauth").grantTypes(Collections.singletonList(grantType))
//				.scopes(Arrays.asList(scopes())).build();
//	}
//
//	/**
//	 * 这里设置 swagger2 认证的安全上下文
//	 */
//	private SecurityContext securityContext() {
//		return SecurityContext.builder()
//				.securityReferences(Collections.singletonList(new SecurityReference("spring_oauth", scopes())))
//				.forPaths(PathSelectors.any()).build();
//	}
//
//	/**
//	 * 这里是写允许认证的scope
//	 */
//	private AuthorizationScope[] scopes() {
//		return new AuthorizationScope[] { new AuthorizationScope("all", "All scope is trusted!") };
//	}
//
//}
