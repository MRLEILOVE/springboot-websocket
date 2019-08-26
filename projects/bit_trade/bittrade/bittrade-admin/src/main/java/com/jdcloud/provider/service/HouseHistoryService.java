package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.HouseHistoryDto;
import com.jdcloud.provider.pojo.HouseHistory;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 狗窝历史动态表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-20
 */
public interface HouseHistoryService extends IService<HouseHistory> {

    Page<HouseHistory> getHistoryList(Page<HouseHistory> page, HouseHistoryDto houseHistoryDto);
}
