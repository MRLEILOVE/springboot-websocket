package com.walletbiz.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.walletbiz.pojo.CPersonalAccount;
import com.walletbiz.vo.ConversionVo;
import com.walletbiz.vo.PersonalAccountVO;

public interface PersonalAccountService extends IService<CPersonalAccount> {
    /**
     * 用户的法币账户总资金折合
     * @param userId 用户id
     * @return
     */
    ConversionVo totalConversion(Long userId);

    /**
     * 用户的法币账户钱包列表
     * @param userId 用户id
     * @return
     */
    List<PersonalAccountVO> list(Long userId);
}
