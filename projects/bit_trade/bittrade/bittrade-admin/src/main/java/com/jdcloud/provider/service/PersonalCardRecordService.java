package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.ActionDto;
import com.jdcloud.provider.pojo.PersonalCardRecord;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.PersonalCardRecordVo;

/**
 * <p>
 * 用户收款信息表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-14
 */
public interface PersonalCardRecordService extends IService<PersonalCardRecord> {


    Page<PersonalCardRecordVo> getrecordList(Page<PersonalCardRecordVo> page, ActionDto actionDto);
}
