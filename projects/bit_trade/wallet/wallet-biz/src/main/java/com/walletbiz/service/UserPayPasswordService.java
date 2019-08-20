package com.walletbiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.core.common.DTO.ReturnDTO;
import com.walletbiz.pojo.UserPayPassword;

/**
 * <p>
 * 用户支付密码表 服务类
 * </p>
 *
 * @author helen
 * @since 2019-04-10
 */
public interface UserPayPasswordService extends IService<UserPayPassword> {

    UserPayPassword selectByUserId(long userId);

    ReturnDTO<String> attestationPayPassword(Long userId, String payPassword);
}
