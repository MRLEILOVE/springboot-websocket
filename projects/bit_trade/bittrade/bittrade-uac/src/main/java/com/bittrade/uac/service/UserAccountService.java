package com.bittrade.uac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.uac.model.enums.ConstantEnum;
import com.bittrade.uac.model.pojo.UserAccount;

/**
 * @author: xzc
 * @create: 2019-08-13 14:11
 * @description: 用户账号
 **/
public interface UserAccountService extends IService<UserAccount> {

    /**
     * 更新用户账户身份认证状态
     *
     * @param userId
     * @param auditing
     */
    void submitAuditing(Long userId, ConstantEnum.AuthenticationConstant auditing);

}
