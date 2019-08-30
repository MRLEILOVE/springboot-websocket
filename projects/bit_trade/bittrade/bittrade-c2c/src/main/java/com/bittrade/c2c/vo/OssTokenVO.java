package com.bittrade.c2c.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 阿里OSS Token VO
 *
 * @author leigq
 */
@Data
@Builder
public class OssTokenVO {

    private String region;

    private String accessKeyId;

    private String accessKeySecret;

    private String securityToken;

    private String bucket;

    private String expiration;

    private String uploadPath;

}
