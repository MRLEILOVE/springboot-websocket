package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.HouseHistoryDto;
import com.jdcloud.provider.pojo.HouseHistory;
import com.jdcloud.provider.mapper.HouseHistoryMapper;
import com.jdcloud.provider.service.HouseHistoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 狗窝历史动态表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-20
 */
@Service
public class HouseHistoryServiceImpl extends ServiceImpl<HouseHistoryMapper, HouseHistory> implements HouseHistoryService {


    /**
     * 动态列表
     * @param page
     * @param houseHistoryDto
     * @return
     */
    @Override
    public Page<HouseHistory> getHistoryList(Page<HouseHistory> page, HouseHistoryDto houseHistoryDto) {
        page.setRecords(baseMapper.getHistoryList(page,houseHistoryDto));
        return page;
    }
}
