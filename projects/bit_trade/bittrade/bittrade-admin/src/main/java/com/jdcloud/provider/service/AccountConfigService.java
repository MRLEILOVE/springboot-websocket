package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.AccountConfigDto;
import com.jdcloud.provider.pojo.AccountConfig;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-05-20
 */
public interface AccountConfigService extends IService<AccountConfig> {

    Page<AccountConfig> selectAccountConfigPageList(Page<AccountConfig> page, AccountConfigDto accountConfigDto);

    Wrapper<String> saveOrUpdate(AccountConfig accountConfig);

    int deleteByIds(String ids);
}
