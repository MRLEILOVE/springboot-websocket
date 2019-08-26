package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.EnrichmentCountDto;
import com.jdcloud.provider.pojo.EnrichmentCount;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 统计币种金额 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-08-05
 */
public interface EnrichmentCountService extends IService<EnrichmentCount> {

    Page<EnrichmentCount> getEnrichmentCount(Page<EnrichmentCount> page, EnrichmentCountDto dto);
}
