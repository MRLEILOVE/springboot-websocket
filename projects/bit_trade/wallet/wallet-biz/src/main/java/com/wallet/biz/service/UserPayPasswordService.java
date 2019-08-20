package com.wallet.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.pojo.UserPayPassword;

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
