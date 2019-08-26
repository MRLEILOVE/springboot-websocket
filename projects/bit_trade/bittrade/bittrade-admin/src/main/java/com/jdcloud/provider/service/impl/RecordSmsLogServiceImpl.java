package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.RecordSmsLogDto;
import com.jdcloud.provider.mapper.RecordSmsLogMapper;
import com.jdcloud.provider.pojo.RecordSmsLog;
import com.jdcloud.provider.pojo.vo.RecordSmsLogVo;
import com.jdcloud.provider.service.RecordSmsLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 记录抢狗短信 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-05-16
 */
@Service
public class RecordSmsLogServiceImpl extends ServiceImpl<RecordSmsLogMapper, RecordSmsLog> implements RecordSmsLogService {

    @Override
    public Page<RecordSmsLogVo> selectRecordSmsLogPageList(Page<RecordSmsLogVo> page, RecordSmsLogDto recordSmsLogDto) {
        return page.setRecords(baseMapper.selectRecordSmsLogPageList(page, recordSmsLogDto));
    }

    /**
     *
     * @param page
     * @param recordSmsLogDto
     * @return
     */
    @Override
    public Page<RecordSmsLogVo> selectNoAdoptSMSList(Page<RecordSmsLogVo> page, RecordSmsLogDto recordSmsLogDto) {
        return page.setRecords(baseMapper.selectNoAdoptSMSList(page, recordSmsLogDto));
    }
}
