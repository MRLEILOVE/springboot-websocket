package com.bittrade.uac.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author: xzc
 * @create: 2019/8/27 下午4:01
 * @description:
 **/
@Slf4j
@Service
public class AliYunFileService {

    @Autowired
    private AliYunConfiguration aliYunConfiguration;

    /**
     * 创建OSSClient
     *
     * @return
     */
    public OSSClient createOSSClient() {
        String endpoint = aliYunConfiguration.getEndpoint();
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录
        // https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = aliYunConfiguration.getAccessKeyId();
        String accessKeySecret = aliYunConfiguration.getAccessKeySecret();
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        log.info("<== Create OSSClient OK.");
        return ossClient;
    }

    /**
     * 上传图片
     *
     * @param base64Str
     * @param path
     * @return
     */
    public String upload(String base64Str, String path) {
        OSSClient ossClient = null;
        try {
            ossClient = this.createOSSClient();
            String bucketName = aliYunConfiguration.getBucketName();
            // 将字符串转换为byte数组
            byte[] bytes = Base64.decodeBase64(base64Str);
            ossClient.putObject(bucketName, path, new ByteArrayInputStream(bytes));
            // 返回路径
            return aliYunConfiguration.getBucketDomain() + path;
        } catch (Exception e) {
            log.error("图片上传失败", e);
            throw new RuntimeException("图片上传失败！");
        } finally {
            this.shutdown(ossClient);
        }
    }

    /**
     * 上传图片
     *
     * @param file
     * @param path
     * @return
     */
    public String uploadFileInputStream(InputStream file, String path) {
        OSSClient ossClient = null;
        try {
            ossClient = this.createOSSClient();
            String bucketName = aliYunConfiguration.getBucketName();
            ossClient.putObject(bucketName, path, file);
            // 返回路径
            return aliYunConfiguration.getBucketDomain() + path;
        } catch (Exception e) {
            log.error("图片上传失败", e);
            throw new RuntimeException("图片上传失败！");
        } finally {
            this.shutdown(ossClient);
        }
    }

    /**
     * 删除oss图片
     *
     * @param
     * @param img 文件名
     * @return
     */
    public boolean delete_oss(String img) {
        OSSClient ossClient = null;
        try {
            ossClient = this.createOSSClient();
            boolean exist = ossClient.doesObjectExist(aliYunConfiguration.getBucketName(), img);
            if (!exist) {
                return false;
            }
            ossClient.deleteObject(aliYunConfiguration.getBucketName(), img);
        } catch (Exception e) {
            log.error("图片删除失败", e);
            throw new RuntimeException("图片删除失败！");
        } finally {
            this.shutdown(ossClient);
        }
        return true;
    }

    /**
     * 获取文件名称
     *
     * @param url
     * @return
     */
    public static String ossGetURL(String url) {
        String[] strArray = url.split(".com/");
        String fileName = strArray[strArray.length - 1];
        return fileName;
    }

    /**
     * 关闭OSSClient
     *
     * @param ossClient
     * @return
     */
    public Boolean shutdown(OSSClient ossClient) {
        if (ossClient != null) {
            ossClient.shutdown();
            log.info("<== shutdown OSSClient OK.");
        }
        return Boolean.TRUE;
    }

    /**
     * 创建阿里云 OSS 临时token
     *
     * @param httpServletRequest
     * @return
     */
    public AssumeRoleResponse createOssToken(HttpServletRequest httpServletRequest) {
        /*
         * 子账号的 accessKeyId、accessKeySecret、roleArn，注意：子账号需赋予 AliyunSTSAssumeRoleAccess(调用STS服务AssumeRole接口的权限)权限
         * */
        String accessKeyId = "LTAIkkuOrFulnOno";
        String accessKeySecret = "zJzSvihiClPxPmFtVHAhWYwTpJmuQA";
        String roleArn = "acs:ram::1770136303143339:role/oss-sts";

        //roleSessionName时临时Token的会话名称，自己指定用于标识你的用户，或者用于区分Token颁发给谁
        //要注意roleSessionName的长度和规则，不要有空格，只能有'-'和'_'字母和数字等字符
        String roleSessionName = "session-name";
        try {
            // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
            DefaultProfile.addEndpoint("", "", "Sts", "sts.aliyuncs.com");
            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            // 创建一个 AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest request = new AssumeRoleRequest();
            //POST请求
            request.setMethod(MethodType.POST);
            //https协议
            request.setProtocol(ProtocolType.HTTPS);
            //持续时间, 只能设置 15min - 1hr 之间
            request.setDurationSeconds(900L);
            //角色id
            request.setRoleArn(roleArn);
            //应用程序标识(自己定义)
            request.setRoleSessionName(roleSessionName);
            // 发起请求，并得到response
            AssumeRoleResponse acsResponse = client.getAcsResponse(request);
            return acsResponse;
        } catch (ClientException e) {
            log.error("创建阿里云 OSS 临时token异常", e);
        }
        return null;
    }


    /**
     * @author: xzc
     * @create: 2019/8/27 下午2:38
     * @description: 阿里云存储服务器配置
     **/
    @Getter
    @Setter
    @ToString
    @Component
    @ConfigurationProperties(prefix = "upload.aliyun")
    public class AliYunConfiguration {
        private String accessKeyId;
        private String accessKeySecret;
        /**
         * 地域节点
         */
        private String endpoint;
        /**
         * 空间名称
         */
        private String bucketName;
        /**
         * 外部链接
         */
        private String bucketDomain;

        private String region;
    }
}
