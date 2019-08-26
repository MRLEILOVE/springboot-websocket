package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.AccountRecordDto;
import com.jdcloud.provider.pojo.PersonalAccountRecord;
import com.jdcloud.provider.pojo.vo.PersonalAccountRecordVo;

/**
 * <p>
 * 我的资产流水表 服务类
 * </p>
 *
 * @author helne
 * @since 2019-03-22
 */
public interface PersonalAccountRecordService extends IService<PersonalAccountRecord> {
    Page<PersonalAccountRecordVo> getAccountList(Page<PersonalAccountRecordVo> page, AccountRecordDto accountRecordDto);

}
