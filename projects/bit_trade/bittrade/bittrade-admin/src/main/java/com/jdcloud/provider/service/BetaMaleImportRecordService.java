package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.BetaMaleDto;
import com.jdcloud.provider.dto.BetaMaleImportRecordDto;
import com.jdcloud.provider.pojo.BetaMaleImportRecord;
import com.jdcloud.provider.pojo.vo.BetaMaleImportRecordVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-10
 */
public interface BetaMaleImportRecordService extends IService<BetaMaleImportRecord> {

    /**
     * 查询列表
     * @param page
     * @param betaMaleImportRecordDto
     * @return
     */
    Page<BetaMaleImportRecordVo> selectBetaMaleImportRecordListPage(Page<BetaMaleImportRecordVo> page, BetaMaleImportRecordDto betaMaleImportRecordDto);
}
