package com.wallet.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wallet.biz.pojo.TAccountConfig;

/**
 * 币种配置表Mapper
 */
public interface TAccountConfigMapper extends BaseMapper<TAccountConfig> {
    /**
     * 查询两个账户共同的币种
     * @param accountId1 账户1id
     * @param accountId2 账户2id
     */
    List<TAccountConfig> commonCurrency(@Param("accountId1") Integer accountId1, @Param("accountId2") Integer accountId2);

    /**
     * 获取该账户下的币种列表
     * @param accountId 账户id
     * @return 币种列表
     */
    List<TAccountConfig> getCurrenciesByAccountId(@Param("accountId") Integer accountId);
}
