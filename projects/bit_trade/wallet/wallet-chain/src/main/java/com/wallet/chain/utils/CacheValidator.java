package com.wallet.chain.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import com.wallet.chain.constant.ErrorConstant;
import com.wallet.chain.exception.CommonException;

import java.util.Map;

@Slf4j
public class CacheValidator {

    public static void addValidate(String key) {
        if (StringUtils.isEmpty(key)) {
            log.error("CacheValidator.addValidate redis Key不能为空");
            throw CommonException.INSTANCE(ErrorConstant.FAILURE, "系统异常 !");
        }
    }

    public static void addValidate(String key, int timeout) {
        addValidate(key);
        if (timeout < 0) {
            log.error("CacheValidator.addValidate 超时时间不能为负数");
            throw CommonException.INSTANCE(ErrorConstant.FAILURE, "系统异常 !");
        }
    }

    public static void mapEntryValidate(Map.Entry<?, ?> entry) {
        if (entry == null) {
            log.error("CacheValidator.addValidate entry不能为空");
            throw CommonException.INSTANCE(ErrorConstant.FAILURE, "系统异常 !");
        }
        if (entry.getKey() == null) {
            log.error("CacheValidator.addValidate   entry key不能为空");
            throw CommonException.INSTANCE(ErrorConstant.FAILURE, "系统异常 !");
        }
        if (!(entry.getKey() instanceof String)) {
            log.error("CacheValidator.addValidate entry key必须是String类型");
            throw CommonException.INSTANCE(ErrorConstant.FAILURE, "系统异常 !");
        }
    }

}