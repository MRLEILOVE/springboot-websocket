package com.core.framework.props;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.transaction", ignoreUnknownFields = true)
public class TransactionProperties {

	private Properties attributes;
	private String expression;

}
