package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.ActionDto;
import com.jdcloud.provider.pojo.PersonalCardRecord;
import com.jdcloud.provider.mapper.PersonalCardRecordMapper;
import com.jdcloud.provider.pojo.vo.PersonalCardRecordVo;
import com.jdcloud.provider.service.PersonalCardRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户收款信息表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-14
 */
@Service
public class PersonalCardRecordServiceImpl extends ServiceImpl<PersonalCardRecordMapper, PersonalCardRecord> implements PersonalCardRecordService {

    /**
     *
     * @param page
     * @param actionDto
     * @return
     */
    @Override
    public Page<PersonalCardRecordVo> getrecordList(Page<PersonalCardRecordVo> page, ActionDto actionDto) {
        return page.setRecords(baseMapper.getrecordList(page,actionDto));
    }
}
