package com.bittrade.c2c.util;

import com.alibaba.fastjson.JSON;
import com.core.web.common.entity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xzc
 * @date 2019-08-20 16:52
 * @description
 */
@Slf4j
public class RequestUtils {

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取当前登入用户
     *
     * @return
     */
    public static LoginUser getCurrentUser() {
        String authorization = getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(authorization)) {
            return null;
        }
        String[] authorizations = authorization.split("\\.");
        if (authorizations.length == 3) {
            final java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
            try {
                String loginStr = new String(decoder.decode(authorizations[1]), "UTF-8");
                LoginUser loginUser = JSON.parseObject(loginStr, LoginUser.class);
                return loginUser;
            } catch (Exception e) {
                log.error("base64解码失败", e);
            }
        }
        return null;
    }
}
