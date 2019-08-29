package com.bittrade.uac.utils;

import com.bittrade.uac.model.dto.CurrentUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {

    private static final Logger LOG = LoggerFactory.getLogger(WebUtil.class);

    /**
     * 获取request
     *
     * @return
     */
    public static final HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取当前用户
     */
    public static final CurrentUserDto getCurrentUser() {
        return new CurrentUserDto();
    }

}
