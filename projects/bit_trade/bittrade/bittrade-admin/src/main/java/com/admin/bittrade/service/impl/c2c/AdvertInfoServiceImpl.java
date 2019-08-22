package com.admin.bittrade.service.impl.c2c;

import com.admin.bittrade.mapper.c2c.AdvertInfoMapper;
import com.admin.bittrade.service.c2c.AdvertInfoService;
import com.admin.bittrade.vo.PageParam;
import com.admin.bittrade.vo.c2c.AdvertInfoScreeningParameterVo;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.pojo.model.TAdvertInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xzc
 * @date 2019-08-22 11:06
 * @description
 */
@Service
public class AdvertInfoServiceImpl extends ServiceImpl<AdvertInfoMapper, TAdvertInfo> implements AdvertInfoService {

    @Autowired
    private AdvertInfoService advertInfoService;

    @Autowired
    private AdvertInfoMapper advertInfoMapper;

    @Override
    public IPage<TAdvertInfo> findList(PageParam pageParam, AdvertInfoScreeningParameterVo parameterVo) {
        Map<String, Object> condition = (Map) JSON.toJSON(parameterVo);
        advertInfoMapper.findList(condition);
        return null;
    }
}
