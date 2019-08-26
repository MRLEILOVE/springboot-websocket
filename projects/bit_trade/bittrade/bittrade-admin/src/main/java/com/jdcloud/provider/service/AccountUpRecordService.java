package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.AccountUpRecordDto;
import com.jdcloud.provider.dto.BetaAccountDto;
import com.jdcloud.provider.pojo.AccountUpRecord;
import com.jdcloud.provider.pojo.vo.AccountUpRecordVo;

/**
 * <p>
 * 我的资产流水表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-05-29
 */
public interface AccountUpRecordService extends IService<AccountUpRecord> {
    Page<AccountUpRecordVo> selectAccountUpRecordList(Page<AccountUpRecordVo> page, AccountUpRecordDto dto);
}
