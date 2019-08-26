package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.CountryCodeDto;
import com.jdcloud.provider.mapper.CountryCodeMapper;
import com.jdcloud.provider.pojo.CountryCode;
import com.jdcloud.provider.service.CountryCodeService;
import com.jdcloud.util.Convert;
import com.jdcloud.util.StringUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-05-17
 */
@Service
public class CountryCodeServiceImpl extends ServiceImpl<CountryCodeMapper, CountryCode> implements CountryCodeService {

    @Override
    public Page<CountryCode> selectCountryCodePageList(Page<CountryCode> page, CountryCodeDto countryCodeDto) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (countryCodeDto.getUseType() != null){
            entityWrapper.eq("use_type", countryCodeDto.getUseType());
        }
        if (!StringUtil.isBlank(countryCodeDto.getKeyword())){
            entityWrapper.like("country_cn_name", countryCodeDto.getKeyword(), SqlLike.DEFAULT).or().
                    like("country_code", countryCodeDto.getKeyword()).or().
                    like("country_area", countryCodeDto.getKeyword());
        }
        List<String> orderBy = new ArrayList<>();
        orderBy.add("useType");
        orderBy.add("country_en_name");
        entityWrapper.orderAsc(orderBy);
        page.setRecords(baseMapper.selectPage(page, entityWrapper));
        return page;
    }

    @Override
    public Wrapper<String> saveOrUpdate(CountryCode countryCode) {
        if (countryCode.getId() == null) {
            countryCode.setCreateTime(new Date());
            baseMapper.insert(countryCode);
            return WrapMapper.ok("新增成功");
        } else {
            countryCode.setUpdateTime(new Date());
            baseMapper.updateById(countryCode);
            return WrapMapper.ok("修改成功");
        }
    }

    @Override
    public int deleteByIds(String idStr) {
        Integer[] ids = ConvertUtil.toIntArray(idStr);
        return baseMapper.deleteByIds(ids);
    }

    @Override
    public int updateUseTypeAll(String idStr, Integer useType) {
        Integer[] ids = ConvertUtil.toIntArray(idStr);
        return baseMapper.updateUseTypeAll(ids, useType);
    }
}
