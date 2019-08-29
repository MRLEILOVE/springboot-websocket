package com.bittrade.uac.service.impl;

import com.bittrade.uac.model.dto.CurrentUserDto;
import com.bittrade.uac.model.enums.ConstantEnum;
import com.bittrade.uac.service.UploadService;
import com.bittrade.uac.mapper.UserAuthenticationMapper;
import com.bittrade.uac.model.dto.UserAuthenticationDto;
import com.bittrade.uac.model.pojo.UserAuthentication;
import com.bittrade.uac.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: xzc
 * @create: 2019/8/27 下午2:38
 * @description: 用户认证上传图片
 **/
@Component
@EnableAsync
public class UploadServiceImpl implements UploadService {

    @Autowired
    private AliYunFileService aliYunFileService;

    @Autowired
    private UserAuthenticationMapper authenticationMapper;

    @Value("${bitTrade.profile}")
    private String profile;

    /**
     * 身份认证上传图片
     *
     * @param userAuthenticationDto
     */
    @Async
    @Override
    public void upload(UserAuthenticationDto userAuthenticationDto) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String nowTime = localDateTime.format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        CurrentUserDto currentUser = WebUtil.getCurrentUser();
        Long userId = currentUser.getUserId();
        // 上传图片
        String path = profile + ConstantEnum.AliyunConstant.AUTHENTICATION_PATH.getCode();
        String identityFrontUrl = aliYunFileService.upload(userAuthenticationDto.getIdentityFrontKey(),
                path + userId + "_" + nowTime + "_identityFront." + userAuthenticationDto.getIdentityFrontSuffix());
        String identityBackUrl = aliYunFileService.upload(userAuthenticationDto.getIdentityBackKey(),
                path + userId + "_" + nowTime + "_identiglobalExceptionHandlertyBack." + userAuthenticationDto.getIdentityBackSuffix());
        String bankCardUrl = aliYunFileService.upload(userAuthenticationDto.getBankCardKey(),
                path + userId + "_" + nowTime + "_bankCard." + userAuthenticationDto.getBankCardSuffix());
        UserAuthentication authentication = new UserAuthentication();
        authentication.setUserId(userId);
        authentication.setIdentityFrontUrl(identityFrontUrl);
        authentication.setIdentityBackUrl(identityBackUrl);
        authentication.setBankCardUrl(bankCardUrl);
        authenticationMapper.updateByUserId(authentication);
    }

}