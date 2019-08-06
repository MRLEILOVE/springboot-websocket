package com.core.framework;

import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.core.tool.YamlUtil;

//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@MapperScan(value = {
		//"com.**.__default.DAO", 
		"com.**.dao"
		})
@ComponentScan(basePackages = { 
		// .
		"com.core.framework.props", 
		"com.core.framework.config", 
		// web .
		"com.core.web.common.props", 
		"com.core.web.common.config", 
		"com.core.web.common.exception" 
		})
@EnableTransactionManagement
public abstract class BaseApplication {

	private static final String name;
	
	static {
		Map<String, Object> map_yaml = YamlUtil.loadYaml("application.yaml");
		name = YamlUtil.getYamlValue(map_yaml, "data.application.name");
		System.out.println(name);
	};
	
	/**
	 * 
	 * @param cls
	 * @param args
	 * @param name
	 * @return
	 */
	@Deprecated
	protected static final ConfigurableApplicationContext run(Class<?> cls, String[] args, String name) {
		System.setProperty("svc_name", name);
//		System.setProperty("spring.devtools.restart.enabled", "false");

//		SpringApplication.run(cls, args);
		SpringApplication sa = new SpringApplication(cls);
//		sa.setWebApplicationType(org.springframework.boot.WebApplicationType.NONE);
		return sa.run(args);
	}
	
	/**
	 * 
	 * @param cls
	 * @param args
	 * @return
	 */
	protected static final ConfigurableApplicationContext run(Class<?> cls, String[] args) {
		return run(cls, args, name);
	}

}
