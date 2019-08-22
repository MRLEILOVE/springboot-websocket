package com.core.framework;

import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.core.common.constant.IConstant;
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
//@com.spring4all.swagger.EnableSwagger2Doc
public abstract class BaseApplication {

	private static final String NAME_KEY = "data.application.name";
	/**
	 * <pre>
	 * project name .
	 * Key values can be entered from multiple paths .
	 * Can be transported anywhere .
	 * </pre>
	 */
	public static final String NAME;
	
	static {
		Map<String, Object> map_yaml = YamlUtil.loadYaml(IConstant.DEFAULT_SPRING_CONFIG_FILE_NAME);
		NAME = YamlUtil.getYamlValue(map_yaml, NAME_KEY);
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
		if (name != null && name.length() > 0) {
			System.setProperty("svc_name", name);
		}
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
		return run(cls, args, NAME);
	}

}
