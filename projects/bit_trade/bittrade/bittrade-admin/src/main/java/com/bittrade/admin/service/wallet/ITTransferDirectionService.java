package com.bittrade.admin.service.wallet;

import java.util.List;

import com.bittrade.__default.service.IDefaultTTransferDirectionService;
import com.bittrade.admin.model.vo.wallet.DirectionVo;

/**
 * 
 * @author Administrator
 *
 */
public interface ITTransferDirectionService extends IDefaultTTransferDirectionService {

    /**
     * 页面列表
     */
    List<DirectionVo> findList();
}
