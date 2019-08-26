package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.CountryCodeDto;
import com.jdcloud.provider.pojo.CountryCode;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * @Description: 国际区域码服务类
 * @Author: senghor
 * @Date: 2019/5/20 0020 9:36
 */
public interface CountryCodeService extends IService<CountryCode> {

    Page<CountryCode> selectCountryCodePageList(Page<CountryCode> page, CountryCodeDto countryCodeDto);

    Wrapper<String> saveOrUpdate(CountryCode countryCode);

    int deleteByIds(String ids);

    int updateUseTypeAll(String ids, Integer useType);
}
