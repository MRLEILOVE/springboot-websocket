package com.bittrade.uac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.uac.mapper.PersonalAccountMapper;
import com.bittrade.uac.model.pojo.PersonalAccount;
import com.bittrade.uac.service.PersonalAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: xzc
 * @create: 2019-08-13 14:11
 * @description: c2c服务类
 **/
@Slf4j
@Service
public class PersonalAccountServiceImpl extends ServiceImpl<PersonalAccountMapper, PersonalAccount> implements PersonalAccountService {

    @Autowired
    private PersonalAccountService personalAccountService;

    @Override
    public void create(Long userId) {
        Date now = new Date();
        //新增C2C账户 :CNY/USDT
        PersonalAccount personalAccount1 = new PersonalAccount();
        personalAccount1.setUserId(userId);
        personalAccount1.setCreateTime(now);
        personalAccount1.setProductType(1);
        personalAccountService.save(personalAccount1);
        //新增C2C账户 :CNY/BI
        PersonalAccount personalAccount2 = new PersonalAccount();
        personalAccount2.setUserId(userId);
        personalAccount2.setCreateTime(now);
        personalAccount2.setProductType(2);
        personalAccountService.save(personalAccount2);
    }
}
