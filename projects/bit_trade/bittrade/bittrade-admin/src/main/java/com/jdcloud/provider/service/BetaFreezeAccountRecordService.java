package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.BetaFreezeAccountDto;
import com.jdcloud.provider.pojo.BetaFreezeAccountRecord;
import com.jdcloud.provider.pojo.vo.BetaFreezeAccountRecordVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-08-08
 */
public interface BetaFreezeAccountRecordService extends IService<BetaFreezeAccountRecord> {

    Page<BetaFreezeAccountRecordVo> getBetaAccountRecord(Page<BetaFreezeAccountRecordVo> page, BetaFreezeAccountDto dto);
}
