package com.bittrade.uac.web;

import com.bittrade.uac.model.dto.ReturnDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: xzc
 * @create: 2019-08-13 10:19
 * @description: 全局异常拦截器
 **/
@Slf4j
@RestControllerAdvice
public class UacExceptionHandler {

    /**
     * 全局异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ReturnDTO illegalArgumentException(Exception e) {
        log.info("异常={}", e.getMessage(), e);
        return ReturnDTO.error(e.getMessage());
    }
}