package com.bittrade.uac.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.uac.mapper.UserAccountMapper;
import com.bittrade.uac.model.enums.ConstantEnum;
import com.bittrade.uac.model.pojo.UserAccount;
import com.bittrade.uac.service.UserAccountService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;


/**
 * @author: xzc
 * @create: 2019-08-13 14:11
 * @description:
 **/
@Slf4j
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public void submitAuditing(Long userId, ConstantEnum.AuthenticationConstant auditing) {
        UserAccount account = new UserAccount();
        account.setUserId(userId);
        UserAccount userAccount = userAccountService.getOne(Wrappers.lambdaQuery(account));
        Preconditions.checkArgument(Objects.nonNull(userAccount), "不存在此用户用户:" + userId);
        userAccount.setFhasRealValidate(Integer.parseInt(auditing.getCode()));
        userAccount.setUpdateTime(new Date());
        userAccountService.save(userAccount);
    }
}
