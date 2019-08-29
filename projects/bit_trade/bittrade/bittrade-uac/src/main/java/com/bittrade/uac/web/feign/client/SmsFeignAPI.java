package com.bittrade.uac.web.feign.client;

import com.bittrade.uac.model.dto.RequestC5Dto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: xzc
 * @create: 2019/8/27 下午3:17
 * @description:
 **/
@FeignClient("http://hk.5c.com.cn/api/send")
public interface SmsFeignAPI {

    /**
     * 请求接口发送短信
     *
     * @param requestC5Dto
     * @return
     */
    @RequestMapping(value = "/index.php", method = RequestMethod.GET)
    String send(RequestC5Dto requestC5Dto);
}
