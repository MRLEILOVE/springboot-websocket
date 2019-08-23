package com.bittrade.admin.service.wallet;

import com.bittrade.__default.service.IDefaultTTransferDirectionService;
import com.bittrade.admin.model.vo.wallet.DirectionVo;
import com.bittrade.pojo.dto.TTransferDirectionDTO;
import com.bittrade.pojo.vo.TTransferDirectionVO;
import com.bittrade.pojo.model.TTransferDirection;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITTransferDirectionService extends IDefaultTTransferDirectionService<TTransferDirection, TTransferDirectionDTO, TTransferDirectionVO> {

    /**
     * 页面列表
     */
    List<DirectionVo> findList();
}
