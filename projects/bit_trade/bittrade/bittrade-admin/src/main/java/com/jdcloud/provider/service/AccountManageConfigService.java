package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.AccountManageConfigDto;
import com.jdcloud.provider.pojo.AccountManageConfig;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.AccountManageConfigVo;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-20
 */
public interface AccountManageConfigService extends IService<AccountManageConfig> {

    Page<AccountManageConfigVo> getAccountConfig(Page<AccountManageConfigVo> page, AccountManageConfigDto dto);

    Wrapper<String> addAccountConfig(AccountManageConfig accountManageConfig);
}
