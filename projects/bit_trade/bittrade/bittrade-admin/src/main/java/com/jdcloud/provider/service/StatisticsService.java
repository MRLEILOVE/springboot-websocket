package com.jdcloud.provider.service;

import com.jdcloud.provider.dto.IndexDataDto;
import com.jdcloud.provider.dto.StatisticsDto;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 * 统计service
 * <p>
 *
 * @author yongheng
 * @since 2018/12/19
 */
public interface StatisticsService {

    /**
     * 充提统计<br>
     *
     * @return:
     * @Author: yongheng
     * @Date: 2018/12/19 10:50
     */
    StatisticsDto amountStatistics(Long userId);

    /**
     * 首页数据<br>
     *
     * @return:
     * @Author: yongheng
     * @Date: 2018/12/19 15:23
     */
    IndexDataDto indexDataInit();

    Wrapper<IndexDataDto> userMonitor();

    Wrapper<IndexDataDto> betaMonitor();

    Wrapper<IndexDataDto> cardMonitor();

    Wrapper<IndexDataDto> accountMonitor();
}