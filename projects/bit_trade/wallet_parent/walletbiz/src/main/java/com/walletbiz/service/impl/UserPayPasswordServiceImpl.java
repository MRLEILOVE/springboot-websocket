package com.walletbiz.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.core.common.DTO.ReturnDTO;
import com.walletbiz.mapper.UserPayPasswordMapper;
import com.walletbiz.pojo.UserPayPassword;
import com.walletbiz.service.UserPayPasswordService;

/**
 * <p>
 * 用户支付密码表 服务实现类
 * </p>
 *
 * @author helen
 * @since 2019-04-10
 */
@Service
public class UserPayPasswordServiceImpl extends ServiceImpl<UserPayPasswordMapper, UserPayPassword> implements UserPayPasswordService {
    @Autowired
    private UserPayPasswordMapper userPayPasswordMapper;
    @Override
    public UserPayPassword selectByUserId(long userId) {
        return userPayPasswordMapper.selectByUserId(userId);
    }

    @Override
    public ReturnDTO<String> attestationPayPassword(Long userId, String payPassword) {
        UserPayPassword payPam = new UserPayPassword();
        payPam.setUserId(userId);
        QueryWrapper<UserPayPassword> qw = new QueryWrapper<>(payPam);
        UserPayPassword payVo = baseMapper.selectOne(qw);
        if (payVo == null) {
//            return ReturnDTO.error("请设置支付密码！");
        }
        String idStr = Long.toString(userId, 36);
        String md5Password = DigestUtils.md5Hex(payPassword + idStr);
        if (!payVo.getPassword().equals(md5Password)) {
            return ReturnDTO.error("支付密码错误！");
        }
        return ReturnDTO.ok();
    }
}
