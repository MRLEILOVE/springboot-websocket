package com.bittrade.admin.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.admin.service.sys.SysConfigService;

/**
 * config 管理
 * 
 * @author who ?
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
