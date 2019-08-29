package com.bittrade.uac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.uac.model.dto.UserAuthenticationDto;
import com.bittrade.uac.model.pojo.UserAuthentication;
import com.core.common.DTO.ReturnDTO;


/**
 * @author: xzc
 * @create: 2019/8/27 下午4:01
 * @description: 身份认证服务类
 **/
public interface UacAuthenticationService extends IService<UserAuthentication> {

    /**
     * 保存认证信息
     *
     * @param userAuthenticationDto
     * @return
     */
    ReturnDTO saveInfo(UserAuthenticationDto userAuthenticationDto);


}
