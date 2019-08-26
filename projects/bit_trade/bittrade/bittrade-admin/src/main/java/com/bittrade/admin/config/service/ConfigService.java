package com.bittrade.admin.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jdcloud.provider.service.SysConfigService;

/**
 * config 管理
 * 
 * @author ourblue
 *
 */
@Service("config")
public class ConfigService {

	@Autowired
	private SysConfigService sysConfigService;

	public String getKey(String configKey) {
		return sysConfigService.selectConfigByKey( configKey );
	}

}
