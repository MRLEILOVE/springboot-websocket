package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.BetaMaleImportRecordDto;
import com.jdcloud.provider.mapper.BetaMaleImportRecordMapper;
import com.jdcloud.provider.pojo.BetaMaleImportRecord;
import com.jdcloud.provider.pojo.vo.BetaMaleImportRecordVo;
import com.jdcloud.provider.pojo.vo.BetaMaleVo;
import com.jdcloud.provider.service.BetaMaleImportRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-10
 */
@Service
public class BetaMaleImportRecordServiceImpl extends ServiceImpl<BetaMaleImportRecordMapper, BetaMaleImportRecord> implements BetaMaleImportRecordService {

    @Resource
    private BetaMaleImportRecordMapper betaMaleImportRecordMapper;
    /**
     * 查询列表
     *
     * @param page
     * @param betaMaleImportRecordDto
     * @return
     */
    @Override
    public Page<BetaMaleImportRecordVo> selectBetaMaleImportRecordListPage(Page<BetaMaleImportRecordVo> page, BetaMaleImportRecordDto betaMaleImportRecordDto) {
        page.setRecords(betaMaleImportRecordMapper.selectBetaMaleImportRecordListPage(page, betaMaleImportRecordDto));
        return page;
    }
}
