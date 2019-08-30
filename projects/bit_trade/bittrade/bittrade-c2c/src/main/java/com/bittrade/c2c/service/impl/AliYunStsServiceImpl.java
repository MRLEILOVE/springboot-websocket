package com.bittrade.c2c.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.bittrade.c2c.conf.AliYunConfig;
import com.bittrade.c2c.service.AliYunStsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 阿里云STS服务
 */
@Service
@Slf4j
public class AliYunStsServiceImpl implements AliYunStsService {

	@Autowired
	private AliYunConfig aliYunConfig;

	@Override
	public AssumeRoleResponse createOssToken(HttpServletRequest httpServletRequest) {
		/*
		 * 子账号的 accessKeyId、accessKeySecret、roleArn，注意：子账号需赋予 AliyunSTSAssumeRoleAccess(调用STS服务AssumeRole接口的权限)权限
		 * */
		String accessKeyId = aliYunConfig.getAccessKeyId();
		String accessKeySecret = aliYunConfig.getAccessKeySecret();
		String roleArn = aliYunConfig.getRoleArn();

		//roleSessionName时临时Token的会话名称，自己指定用于标识你的用户，或者用于区分Token颁发给谁
		//要注意roleSessionName的长度和规则，不要有空格，只能有'-'和'_'字母和数字等字符
		String roleSessionName = "session-name";
		// 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
		try {
			DefaultProfile.addEndpoint("", "", "Sts", aliYunConfig.getBucketDomain());
			IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
			DefaultAcsClient client = new DefaultAcsClient(profile);
			// 创建一个 AssumeRoleRequest 并设置请求参数
			final AssumeRoleRequest request = new AssumeRoleRequest();
			//POST请求
			request.setMethod(MethodType.POST);
			//https协议
			request.setProtocol(ProtocolType.HTTPS);
			//持续时间, 只能设置 15min - 1hr 之间
			request.setDurationSeconds((long) (15 * 60));
			//角色id
			request.setRoleArn(roleArn);
			//应用程序标识(自己定义)
			request.setRoleSessionName(roleSessionName);
			// 发起请求，并得到response
			return client.getAcsResponse(request);
		} catch (ClientException e) {
			log.error("创建阿里云 OSS 临时token异常", e);
			return null;
		}
	}
}
