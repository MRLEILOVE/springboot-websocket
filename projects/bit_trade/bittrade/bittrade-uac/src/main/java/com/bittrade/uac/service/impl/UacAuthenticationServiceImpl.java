package com.bittrade.uac.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.uac.mapper.UserAuthenticationMapper;
import com.bittrade.uac.model.enums.ConstantEnum;
import com.bittrade.uac.model.enums.UserEnum;
import com.bittrade.uac.service.UacAuthenticationService;
import com.bittrade.uac.service.UacUserService;
import com.bittrade.uac.service.UploadService;
import com.bittrade.uac.model.dto.UserAuthenticationDto;
import com.bittrade.uac.model.pojo.User;
import com.bittrade.uac.model.pojo.UserAuthentication;
import com.bittrade.uac.service.UserAccountService;
import com.bittrade.uac.utils.PubUtils;
import com.core.common.DTO.ReturnDTO;
import com.core.web.constant.entity.LoginUser;
import com.core.web.tool.WebUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: xzc
 * @create: 2019/8/27 下午4:01
 * @description: 身份认证服务
 **/
@Service
public class UacAuthenticationServiceImpl extends ServiceImpl<UserAuthenticationMapper, UserAuthentication> implements UacAuthenticationService {

    @Autowired
    private UacUserService uacUserService;

    @Autowired
    private UacAuthenticationService uacAuthenticationService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UploadService uploadService;

    /**
     * 身份认证信息保存
     *
     * @param userAuthenticationDto
     * @return
     */
    @Override
    public ReturnDTO saveInfo(UserAuthenticationDto userAuthenticationDto) {
        long begin = System.currentTimeMillis();
        System.out.println(begin);
        LoginUser loginUser = WebUtil.getLoginUser();
        Long userId = loginUser.getUser_id();
        // 参数校验
        if (UserEnum.UserIdentityType.IDENTIFICAT.getCode().equals(userAuthenticationDto.getIdentityType())) {
            if (!PubUtils.isIDNumber(userAuthenticationDto.getIdentityNo())) {
                return ReturnDTO.error("请输入正确的身份证！");
            }
        }
        // 校验用户
        User user = uacUserService.getById(userId);
        if (user == null) {
            return ReturnDTO.error("用户不存在！");
        }

        // 根据用户id查询用户的审核状态
        UserAuthentication authentication = new UserAuthentication();
        authentication.setUserId(userId);
        Wrapper<UserAuthentication> wrapper = Wrappers.lambdaQuery(authentication);
        UserAuthentication authentication1 = uacAuthenticationService.getOne(wrapper);
        if (authentication1 != null &&
                authentication1.getFhasRealValidate().equals(Integer.valueOf(ConstantEnum.AuthenticationConstant.AUDITED.getCode()))) {
            return ReturnDTO.error("已通过审核！");
        }
        // 身份证唯一校验
        UserAuthentication checkIdNo = new UserAuthentication();
        checkIdNo.setIdentityNo(userAuthenticationDto.getIdentityNo());
        UserAuthentication authentication2 = uacAuthenticationService.getOne(Wrappers.lambdaQuery(checkIdNo));
        if (authentication2 != null && authentication2.getUserId().compareTo(userId) != 0) {
            return ReturnDTO.error("该身份证号码已认证！");
        }

        // 同步用户账户认证状态
        userAccountService.submitAuditing(userId, ConstantEnum.AuthenticationConstant.AUDITING);

        //上传图片
        uploadService.upload(userAuthenticationDto);

        // 修改用户表状态
        user.setIsIdentityValidate(UserEnum.ValidateStatus.VALIDATE_W.getCode());
        user.setIdentityNo(userAuthenticationDto.getIdentityNo());
        user.setIdentityType(userAuthenticationDto.getIdentityType());
        uacUserService.updateById(user);

        // 数据组装
        BeanUtils.copyProperties(userAuthenticationDto, authentication);
        authentication.setPostRealValidate(Integer.valueOf(ConstantEnum.AuthenticationConstant.SUBMITTED.getCode()));
        authentication.setFhasRealValidate(Integer.valueOf(ConstantEnum.AuthenticationConstant.AUDITING.getCode()));
        authentication.setIdentityFrontUrl(null);
        authentication.setIdentityBackUrl(null);
        authentication.setBankCardUrl(null);
        authentication.setIdentityFrontKey(null);
        authentication.setIdentityBackKey(null);
        authentication.setBankCardKey(null);
        authentication.setSubmitTime(new Date());
        // 保存信息，等待审核
        if (authentication1 == null) {
            authentication.setCreateTime(new Date());
            authentication.setUpdateTime(new Date());
            uacAuthenticationService.save(authentication);
        } else {
            authentication.setId(authentication1.getId());
            authentication.setUpdateTime(new Date());
            uacAuthenticationService.updateById(authentication);
        }
        System.out.println(System.currentTimeMillis() - begin);
        return ReturnDTO.ok("身份认证信息提交成功，等待管理员审核。");
    }
}
