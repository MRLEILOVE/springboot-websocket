package com.bittrade.c2c.service;

import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * 阿里云STS服务
 */
public interface AliYunStsService {

	/**
	 * 创建阿里云 OSS 临时token
	 * @param httpServletRequest
	 * @return
	 */
	AssumeRoleResponse createOssToken(HttpServletRequest httpServletRequest);

}
