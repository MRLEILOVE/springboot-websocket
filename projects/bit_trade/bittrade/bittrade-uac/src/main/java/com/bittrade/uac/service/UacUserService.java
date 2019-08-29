package com.bittrade.uac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.uac.model.dto.*;
import com.bittrade.uac.model.pojo.User;
import com.bittrade.uac.model.vo.*;

/**
 * @author: xzc
 * @create: 2019/8/27 下午2:38
 * @description:
 **/
public interface UacUserService extends IService<User> {

    /**
     * 发送短信
     *
     * @param sendSmsVo
     */
    void sendSms(SendSmsVo sendSmsVo);

    /**
     * 用户注册
     *
     * @param registerVo
     */
    void register(UserRegisterVo registerVo);

    /**
     * 找回密码
     *
     * @param findPwdVo
     */
    void findPwd(UserFindPwdVo findPwdVo);

    /**
     * 修改密码手机
     *
     * @param changePasswordDto
     */
    void updatePassword(ChangePasswordDto changePasswordDto);

    /**
     * 是否存在邮箱
     *
     * @param email 邮箱地址
     * @return true 存在 false 不存在
     */
    boolean hasEmail(String email);

    /**
     * 是否存在手机号码
     *
     * @param mobile 手机号码
     * @return true 存在 false 不存在
     */
    boolean hasMobile(String mobile);

    /**
     * 发送邮件:
     *
     * @param sendEmailVo
     */
    void sendEmail(SendEmailVo sendEmailVo);

    /**
     * 发送找回密码的验证码
     *
     * @param loginName
     */
    void sendRetrieve(String loginName);

    /**
     * 验证码 验证
     *
     * @param checkCodeVo
     * @return
     */
    void checkCode(CheckCodeVo checkCodeVo);
}