package com.core.web.common.config.swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import com.core.framework.BaseApplication;
import com.core.web.common.props.OAuth2Properties;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@org.springframework.web.servlet.config.annotation.EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {

	private static final String OAUTH2 = "oauth2";
	@Autowired
	private OAuth2Properties oAuth2Properties;

	public SwaggerConfig() {
		super();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API 接口服务").description("API 接口服务")
				.termsOfServiceUrl("http://www.cnblogs.com/irving").version("v1").license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.contact(new Contact("irving", "http://www.cnblogs.com/irving", "zhouyongtao@outlook.com")).build();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select().apis(RequestHandlerSelectors.basePackage("com.bittrade." + BaseApplication.NAME + ".controller")) // .any()  不可以用通配符？
				// .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)) // 只显示添加@Api注解的类
				.paths(PathSelectors.any()).build()
				// 开启权限认证。
				.securityContexts(Collections.singletonList(securityContext()))
				.securitySchemes(Arrays.asList(securitySchema(), apiKey(), apiCookieKey()))
//				.securityContexts(securityContexts())
//				.securitySchemes(securitySchemes())
				;
	}

	private List<SecurityReference> defaultAuth() {
		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
		authorizationScopes[0] = new AuthorizationScope("read", "read all");
		authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
		authorizationScopes[2] = new AuthorizationScope("write", "write all");
//		return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes)); // oauth2 Authorization
		// Arrays.asList com.google.common.collect.Lists.newArrayList
		return Arrays.asList(
				new SecurityReference(OAUTH2, authorizationScopes)
				, new SecurityReference(HttpHeaders.AUTHORIZATION, authorizationScopes)
				);
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private OAuth securitySchema() {
		List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
//		authorizationScopeList.add(new AuthorizationScope("read", "read all"));
//		authorizationScopeList.add(new AuthorizationScope("write", "access all"));
		authorizationScopeList.add(new AuthorizationScope("all", "access all"));
		List<GrantType> grantTypes = new ArrayList<>();
		GrantType passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(oAuth2Properties.getAccessTokenUri());
		grantTypes.add(passwordCredentialsGrant);

		return new OAuth(OAUTH2, authorizationScopeList, grantTypes);
	}

	@Bean
	public SecurityScheme apiKey() {
//		return new ApiKey(HttpHeaders.AUTHORIZATION, "apiKey", "header");
		return new ApiKey(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION, "header");
	}

	@Bean
	public SecurityScheme apiCookieKey() {
		return new ApiKey(HttpHeaders.COOKIE, "apiKey", "cookie");
	}

	@Bean
	SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder().clientId(oAuth2Properties.getClientId()).clientSecret(oAuth2Properties.getClientSecret())
				.realm("test-app-realm").appName("test-app").scopeSeparator(",").additionalQueryStringParams(null)
				.useBasicAuthenticationWithAccessCodeGrant(false).build();
	}

	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().deepLinking(true).displayOperationId(false).defaultModelsExpandDepth(1)
				.defaultModelExpandDepth(1).defaultModelRendering(ModelRendering.EXAMPLE).displayRequestDuration(false)
				.docExpansion(DocExpansion.NONE).filter(false).maxDisplayedTags(null)
				.operationsSorter(OperationsSorter.ALPHA).showExtensions(false).tagsSorter(TagsSorter.ALPHA)
				.validatorUrl(null).build();
	}

}