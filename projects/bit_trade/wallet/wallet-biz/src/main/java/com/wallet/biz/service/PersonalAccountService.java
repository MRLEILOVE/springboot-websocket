package com.wallet.biz.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wallet.biz.pojo.CPersonalAccount;
import com.wallet.biz.vo.ConversionVo;
import com.wallet.biz.vo.PersonalAccountVO;

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
