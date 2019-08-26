package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.C2cEnum;
import com.jdcloud.provider.dto.AccountManageConfigDto;
import com.jdcloud.provider.mapper.AccountManageConfigMapper;
import com.jdcloud.provider.pojo.AccountManageConfig;
import com.jdcloud.provider.pojo.vo.AccountManageConfigVo;
import com.jdcloud.provider.service.AccountManageConfigService;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-20
 */
@Service
public class AccountManageConfigServiceImpl extends ServiceImpl<AccountManageConfigMapper, AccountManageConfig> implements AccountManageConfigService {


    @Autowired
    private AccountManageConfigService accountManageConfigService;

    /**
     * 获取账户币种配置
     *
     * @param page
     * @param dto
     * @return
     */
    @Override
    public Page<AccountManageConfigVo> getAccountConfig(Page<AccountManageConfigVo> page, AccountManageConfigDto dto) {
        return page.setRecords(baseMapper.getAccountConfig(page, dto));
    }

    /**
     * 保存账户配置币种
     *
     * @param accountManageConfig
     * @return
     */
    @Override
    public Wrapper<String> addAccountConfig(AccountManageConfig accountManageConfig) {
        // 查询账户 已经配置过这个币种
        EntityWrapper entity = new EntityWrapper();
        entity.eq("account_id", accountManageConfig.getAccountId());
        entity.eq("account_config_id", accountManageConfig.getAccountConfigId());
        int count = baseMapper.selectCount(entity);
        if (count > 0) {
            return WrapMapper.error("币种已经配置过！无需重新配置");
        }
        accountManageConfig.setStatus(C2cEnum.status.NORMAL.getCode());
        boolean bo = accountManageConfigService.insert(accountManageConfig);
        if (!bo) {
            return WrapMapper.error("增加失败");
        }
        return WrapMapper.ok("增加成功");
    }
}
