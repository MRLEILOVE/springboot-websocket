package com.common.bittrade.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.service.IDefaultTWalletRecordService;
import com.bittrade.pojo.dto.AccountTypeDto;
import com.bittrade.pojo.vo.RecordVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITWalletRecordService extends IDefaultTWalletRecordService {

    /**
     * 币币账户资产记录
     * @param userId 用户id
     * @param dto 请求对象
     * @return 币币账户资产记录列表
     */
    Page<RecordVO> queryBiBiAccountRecord(Long userId, AccountTypeDto dto);
}
