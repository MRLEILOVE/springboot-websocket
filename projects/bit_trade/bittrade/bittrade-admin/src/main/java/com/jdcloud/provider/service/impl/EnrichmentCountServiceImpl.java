package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.EnrichmentCountDto;
import com.jdcloud.provider.pojo.EnrichmentCount;
import com.jdcloud.provider.mapper.EnrichmentCountMapper;
import com.jdcloud.provider.service.EnrichmentCountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 统计币种金额 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-08-05
 */
@Service
public class EnrichmentCountServiceImpl extends ServiceImpl<EnrichmentCountMapper, EnrichmentCount> implements EnrichmentCountService {

    /**
     * 统计币种总额列表
     * @param page
     * @param dto
     * @return
     */
    @Override
    public Page<EnrichmentCount> getEnrichmentCount(Page<EnrichmentCount> page, EnrichmentCountDto dto) {
        page.setRecords(baseMapper.getEnrichmentCount(page,dto));
        return page;
    }
}
