/**  
 * Project Name:core-web-common  
 * File Name:OAuth2Properties.java  
 * Package Name:com.core.web.common.props  
 * DateTime: Aug 5, 2019 3:23:29 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/  
  
package com.core.web.common.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**  
 * ClassName:OAuth2Properties <br/>  
 * Description: TODO 添加描述. <br/>  
 * DateTime: Aug 5, 2019 3:23:29 PM <br />
 * @author   Administrator  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security.oauth2.client", ignoreUnknownFields = true)
public class OAuth2Properties {
	
	private String clientId;
	private String clientSecret;
	private String accessTokenUri;
	
}
