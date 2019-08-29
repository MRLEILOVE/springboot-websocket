package com.bittrade.uac.web.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * @author: xzc
 * @create: 2019/8/27 下午3:17
 * @description:
 **/
@FeignClient(name = "5c", url = "http://hk.5c.com.cn")
public interface SmsFeignClient {

    /**
     * 请求接口发送短信
     *
     * @param username
     * @param password
     * @param mobile
     * @param apiKey
     * @param content
     * @param encode
     * @return
     */
    @RequestMapping(value = "/api/send/index.php", method = RequestMethod.GET)
    String send(@RequestParam("username") String username,
                @RequestParam("password_md5") String password,
                @RequestParam("mobile") String mobile,
                @RequestParam("apikey") String apiKey,
                @RequestParam("content") String content,
                @RequestParam("encode") String encode);
}
