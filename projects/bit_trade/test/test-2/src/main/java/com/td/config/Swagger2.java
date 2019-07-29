//package com.td.config;
//
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.google.common.collect.Lists;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
////@org.springframework.web.servlet.config.annotation.EnableWebMvc
//@EnableSwagger2
//public class Swagger2 {
//
//	@Bean
//	public Docket createRestApi() {
//		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
//				.apis(RequestHandlerSelectors.basePackage("com"))
//				.paths(PathSelectors.any()).build().securityContexts(securityContexts())
//				.securitySchemes(securitySchemes());
//	}
//
//	private ApiInfo apiInfo() {
//
//		Contact contact = new Contact("南山", "", "luhaiyou@yunlizhihui.com");
//
//		return new ApiInfoBuilder().title("GIS服务接口").description("提供地图数据的管理服务").version("1.0").contact(contact).build();
//	}
//
//	private List<ApiKey> securitySchemes() {
//
//		return Lists.newArrayList(new ApiKey("Authorization", "Authorization", "header"));
//
//	}
//
//	private List<SecurityContext> securityContexts() {
//
//		SecurityContext context = SecurityContext.builder().securityReferences(defaultAuth())
//				// .forPaths(PathSelectors.regex("^(?!auth).*$"))
//				.build();
//
//		return Lists.newArrayList(context);
//
//	}
//
//	private List<SecurityReference> defaultAuth() {
//
//		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//		authorizationScopes[0] = authorizationScope;
//
//		return Lists.newArrayList(new SecurityReference("Authorization", authorizationScopes));
//
//	}
//
//}
