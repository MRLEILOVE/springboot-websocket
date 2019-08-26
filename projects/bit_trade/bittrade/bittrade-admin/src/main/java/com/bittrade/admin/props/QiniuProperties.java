package com.bittrade.admin.props;

import lombok.Data;

@Data
public class QiniuProperties {

	private QiniuKeyProperties	key	= new QiniuKeyProperties();
	private QiniuOssProperties	oss	= new QiniuOssProperties();

	@Data
	public class QiniuKeyProperties {
		private String	accessKey;
		private String	secretKey;
	}

	@Data
	public class QiniuOssProperties {
		private String	publicHost;
		private String  bucketName;
	}

}
