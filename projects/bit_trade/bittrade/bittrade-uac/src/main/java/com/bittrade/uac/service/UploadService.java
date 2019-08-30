package com.bittrade.uac.service;

import com.bittrade.uac.model.dto.UserAuthenticationDto;

/**
 *
 */
public interface UploadService {

    /**
     * 上传
     *
     * @param userAuthenticationDto
     */
    void upload(UserAuthenticationDto userAuthenticationDto);
}