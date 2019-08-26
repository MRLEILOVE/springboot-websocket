package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.BetaAccountImportRecordDto;
import com.jdcloud.provider.pojo.BetaAccountImportRecord;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-08-15
 */
public interface BetaAccountImportRecordService extends IService<BetaAccountImportRecord> {

    /**
     * @Description: 资金导入记录
     * @Author: senghor
     * @Date: 2019/8/16 9:55
     */
    Page<BetaAccountImportRecord> selectBetaAccountImportRecordListPage(Page<Object> page, BetaAccountImportRecordDto betaAccountImportRecordDto);
}
