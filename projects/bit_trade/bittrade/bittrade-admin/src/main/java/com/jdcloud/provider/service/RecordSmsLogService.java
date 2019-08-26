package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.RecordSmsLogDto;
import com.jdcloud.provider.pojo.RecordSmsLog;
import com.jdcloud.provider.pojo.vo.RecordSmsLogVo;

/**
 * <p>
 * 记录抢狗短信 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-05-16
 */
public interface RecordSmsLogService extends IService<RecordSmsLog> {

    Page<RecordSmsLogVo> selectRecordSmsLogPageList(Page<RecordSmsLogVo> page, RecordSmsLogDto recordSmsLogDto);

    Page<RecordSmsLogVo> selectNoAdoptSMSList(Page<RecordSmsLogVo> page, RecordSmsLogDto recordSmsLogDto);
}
