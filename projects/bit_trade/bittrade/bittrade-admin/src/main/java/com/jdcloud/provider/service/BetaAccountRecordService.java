package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.BetaAccountRecordDto;
import com.jdcloud.provider.pojo.BetaAccountRecord;
import com.jdcloud.provider.pojo.vo.BetaAccountRecordVo;

/**
 * <p>
 * 我的beta资产流水表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-24
 */
public interface BetaAccountRecordService extends IService<BetaAccountRecord> {

    Page<BetaAccountRecordVo> betaAccountRecord (Page<BetaAccountRecordVo> page,Integer id, BetaAccountRecordDto dto);

}
