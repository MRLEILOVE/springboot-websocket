package com.walletbiz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.walletbiz.pojo.TAccountManage;

/**
 * 账户配置Mapper
 */
public interface TAccountManageMapper extends BaseMapper<TAccountManage> {
    /**
     * 查询金额划进的账户
     * @param mainAccountId 资金划转出去的账户id
     * @return 资金划转进去的账户list
     */
    List<TAccountManage> queryAccountDirectionList(@Param("mainAccountId") Integer mainAccountId);
}
