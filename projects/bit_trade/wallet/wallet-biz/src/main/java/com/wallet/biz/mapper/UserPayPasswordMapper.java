package com.wallet.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wallet.biz.pojo.UserPayPassword;

/**
 * <p>
 * 用户支付密码表 Mapper 接口
 * </p>
 *
 * @author helen
 * @since 2019-04-10
 */
public interface UserPayPasswordMapper extends BaseMapper<UserPayPassword> {

    UserPayPassword selectByUserId(long userId);
}
