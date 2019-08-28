package com.bittrade.uac.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.uac.model.pojo.PersonalAccount;


/**
 * @author: xzc
 * @create: 2019-08-13 14:11
 * @description: c2c服务类
 **/
public interface PersonalAccountService extends IService<PersonalAccount> {


    /**
     * 创建交易对类型（
     * 1=CNY/USDT，
     * 2=CNY/BI
     * ）的c2c 两个账户
     *
     * @param userId
     */
    void create(Long userId);
}
