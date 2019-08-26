package com.bittrade.admin.props;

import lombok.Data;

/**
 * <p>
 * 阿里云设置
 * <p>
 *
 * @author yongheng
 * @since 2018/12/6
 */
@Data
public class AliyunProperties {
	private String	accessKeyId;
	private String	accessKeySecret;
	private String	endpoint;		// 地域节点
	private String	bucketName;		// 空间名称
	private String	bucketDomain;	// 外部链接
	// private String uploadPath; // 上传地址
	private String region;
}