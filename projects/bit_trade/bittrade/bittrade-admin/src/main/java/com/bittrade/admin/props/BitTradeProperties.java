package com.bittrade.admin.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.bittrade.admin.constant.GlobalConstant;

import lombok.Data;

/**
 * The class jdcloud properties.
 */

@Data
@Configuration
@ConfigurationProperties(prefix = GlobalConstant.ROOT_PREFIX)
public class BitTradeProperties {

	private AsyncTaskProperties	task	= new AsyncTaskProperties();
	private QiniuProperties		qiniu	= new QiniuProperties();
	private JuheSmsProperties	jh		= new JuheSmsProperties();
	private LanjingProperties	lanjing	= new LanjingProperties();
	private C5SmsProperties		c5		= new C5SmsProperties();
	private AliyunProperties	aliyun	= new AliyunProperties();
	private ZookeeperProperties	zookeeper	= new ZookeeperProperties();
}
