package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.PersonalAccountDto;
import com.jdcloud.provider.pojo.PersonalAccount;
import com.jdcloud.provider.pojo.vo.PersonalAccountListVo;

import java.math.BigDecimal;

/**
 * <p>
 * 我的资产表 服务类
 * </p>
 *
 * @author helne
 * @since 2019-03-22
 */
public interface PersonalAccountService extends IService<PersonalAccount> {
    /**
     * 更新用户资产
     * @param account
     */
    void updatePersonalAccount(PersonalAccount account);
    /**
     * 更新用户资产_新
     * @param account
     */
    boolean updatePersonalAccountNew(PersonalAccount account);

    void updatePersonalAccountNew(Long userId, Integer cnyBi, BigDecimal registerBitt, Integer serialType, String remark,Integer entrustId);

    Page<PersonalAccountListVo> selectPersonalAccountListPage(Page<PersonalAccountListVo> page, PersonalAccountDto dto);

    void personalAccountAdd(Long userId, Integer cnyBi, BigDecimal registerBitt, Integer serialType, String remark,Integer entrustId);

    void personalAccountAddNew(Long userId, Integer cnyBi, BigDecimal registerBitt, Integer serialType, String remark,Integer entrustId);
}
