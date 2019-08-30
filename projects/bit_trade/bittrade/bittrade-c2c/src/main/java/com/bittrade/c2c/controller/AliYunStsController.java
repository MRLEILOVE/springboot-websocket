package com.bittrade.c2c.controller;

import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.bittrade.c2c.conf.AliYunConfig;
import com.bittrade.c2c.service.AliYunStsService;
import com.bittrade.c2c.vo.OssTokenVO;
import com.core.common.DTO.ReturnDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 阿里云OSS STS
 *
 * @author leigq
 */
@Slf4j
@RestController
@RequestMapping("/aliyun/sts")
public class AliYunStsController {

    @Autowired
    private AliYunConfig aliYunConfig;

    @Autowired
    private AliYunStsService aliYunStsService;

    /**
     * 获取 OSS 签名授权
     *
     * @return
     * @author leigq
     * @date 2018-12-17 15:08:55
     **/
    @GetMapping("/oss/tokens")
    public ReturnDTO<Object> getOssToken(HttpServletRequest request) {
        AssumeRoleResponse ossToken = aliYunStsService.createOssToken(request);
        if (Objects.isNull(ossToken)) {
            return ReturnDTO.error("获取 OSS 签名授权失败！");
        }
        // 阿里 OSS 配置
        AssumeRoleResponse.Credentials credentials = ossToken.getCredentials();
        OssTokenVO ossTokenVO = OssTokenVO.builder()
                .region(aliYunConfig.getRegion())
                .accessKeyId(credentials.getAccessKeyId())
                .accessKeySecret(credentials.getAccessKeySecret())
                .securityToken(credentials.getSecurityToken())
                .expiration(credentials.getExpiration())
                .bucket(aliYunConfig.getBucketName())
                .build();
        return ReturnDTO.ok(ossTokenVO);
    }
}
